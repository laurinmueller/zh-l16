package ch.laurinmueller.zhl16;

import java.util.ArrayList;
import java.util.List;

import ch.laurinmueller.zhl16.exception.DecoLimitViolationException;
import ch.laurinmueller.zhl16.kompartiment.Compartment;
import ch.laurinmueller.zhl16.kompartiment.CompartmentDefinition;
import ch.laurinmueller.zhl16.result.Result;
import ch.laurinmueller.zhl16.result.ResultEntry;
import ch.laurinmueller.zhl16.result.Snapshot;

public class Calculator {
	private CompartmentDefinition compartmentDefinition;
	private DiveProfile resultDiveProfile;
	private Double gfDeepestStop = null;
	private Double actualStopDepth = null;
	private Water water;
	private double pressureOnSurface;
	private CalcSettings settings;
	private List<CalculatorListener> listeners = new ArrayList<>();

	public Calculator(DiveProfile diveProfile, CompartmentDefinition compartmentDefinition, Water water,
			CalcSettings settings) {
		this.settings = settings;
		this.water = water;
		this.resultDiveProfile = diveProfile.getCopy();
		this.compartmentDefinition = compartmentDefinition;
		this.pressureOnSurface = BarometerUtil.calcPressureOnSurface(settings.getMeterAboveSeaLevel());
	}

	public Result getResult() throws DecoLimitViolationException {
		Result result = new Result(resultDiveProfile, pressureOnSurface, compartmentDefinition);

		double pigN2T0 = (pressureOnSurface - settings.getPsH2O()) * Gasmix.AIR.getN2();
		double pigHeT0 = (pressureOnSurface - settings.getPsH2O()) * Gasmix.AIR.getHe();
		// diving
		for (double t = 0; t <= resultDiveProfile.getDiveProfileDuration(); t += settings.getCaluculationResolution()) {
			ResultEntry entry = new ResultEntry();
			entry.setResult(result);
			entry.setDeltaT(t);
			double ambientPressure = toAmbientPressure(resultDiveProfile.getDepth(t));
			for (Compartment compartment : compartmentDefinition.getCompartments()) {
				Snapshot snapshot = new Snapshot();
				if (t == 0) {
					snapshot.setPig(pigN2T0 + pigHeT0);
					snapshot.setPigHe(pigHeT0);
					snapshot.setPigN2(pigN2T0);

				} else {
					pigN2T0 = result.getLast().getSnapshot(compartment).getPigN2();
					pigHeT0 = result.getLast().getSnapshot(compartment).getPigHe();
					double pigN2 = getPigN2(pigN2T0, settings.getCaluculationResolution(), compartment, ambientPressure,
							resultDiveProfile.getGasmix(t));
					double pigHe = getPigHe(pigHeT0, settings.getCaluculationResolution(), compartment, ambientPressure,
							resultDiveProfile.getGasmix(t));
					snapshot.setPig(pigN2 + pigHe);
					snapshot.setPigHe(pigHe);
					snapshot.setPigN2(pigN2);
				}
				double pAmbientTolerated = getPAmbientTolerated(compartment, snapshot.getPigN2(), snapshot.getPigHe());
				snapshot.setpAmbientTolerated(pAmbientTolerated);
				double pAmbientToleratedWithGradients = getPAmbientToleratedWithGradients(compartment,
						snapshot.getPigN2(), snapshot.getPigHe(), getActualGF());
				snapshot.setpAmbientToleratedWithGF(pAmbientToleratedWithGradients);
				if (pAmbientTolerated > ambientPressure) {
					throw new DecoLimitViolationException(t, "The Dive profile violates the deompression-limits");
				}
				double noDecompressionTime = getNoDecompressionTime(compartment, snapshot.getPigN2(),
						snapshot.getPigHe(), toAmbientPressure(resultDiveProfile.getDepth(t)),
						resultDiveProfile.getGasmix(t));
				snapshot.setNoDecompressionTime(noDecompressionTime);
				entry.addSnapshot(compartment, snapshot);
			}

			result.add(entry);
			if (t + settings.getCaluculationResolution() > resultDiveProfile.getDiveProfileDuration()
					&& resultDiveProfile.getDepth(resultDiveProfile.getDiveProfileDuration()) != 0) {

				// Lets do the decompression
				double pAmbientTolerated = result.getLast().getDeterminingCompartementWithGFEntry().getValue()
						.getpAmbientToleratedWithGF();
				if (gfDeepestStop == null) {
					double stopDepth = getNextStop(pAmbientTolerated);
					ProfilePart decoPartAcent = new ProfilePart(stopDepth,
							getAscentTime(stopDepth, resultDiveProfile.getDepth(t)), resultDiveProfile.getGasmix(t));
					this.resultDiveProfile.addPart(decoPartAcent);
					if (stopDepth > 0.1) {
						ProfilePart partMinTimeOnDecoLevel = new ProfilePart(stopDepth, settings.getMinDecoStopLength(),
								resultDiveProfile.getGasmix(t));
						this.resultDiveProfile.addPart(partMinTimeOnDecoLevel);
					}
					gfDeepestStop = resultDiveProfile.getMaxDepth();
					actualStopDepth = stopDepth;
					System.out.println("Adding new Stop: " + stopDepth);
				} else {

					double stopDepth = getNextStop(pAmbientTolerated);
					if (stopDepth < actualStopDepth) {
						ProfilePart decoPart = new ProfilePart(stopDepth,
								getAscentTime(stopDepth, resultDiveProfile.getDepth(t)),
								resultDiveProfile.getGasmix(t));
						this.resultDiveProfile.addPart(decoPart);
						System.out.println("Adding new Stop: " + stopDepth);
					}
					if (stopDepth > 0) {
						ProfilePart partMinTimeOnDecoLevel = new ProfilePart(stopDepth, settings.getMinDecoStopLength(),
								resultDiveProfile.getGasmix(t));
						this.resultDiveProfile.addPart(partMinTimeOnDecoLevel);
					}
					actualStopDepth = stopDepth;
				}
			}
			notify(t);
		}

		return result;
	}

	private double getAscentTime(double stopDepth, double actualDepth) {
		return (actualDepth - stopDepth) / settings.getAscentrate();
	}

	private double getNextStop(double pMaxToleratedGradient) {
		int actualDepth = 0;
		while (toAmbientPressure(actualDepth) < pMaxToleratedGradient) {
			actualDepth += settings.getDecompressionLevelDelta();
		}
		return actualDepth;
	}

	private double getNoDecompressionTime(Compartment compartment, double pigN2T0, double pigHeT0,
			double ambientPressure, Gasmix gasmix) {
		double timeLimitForSimulation = settings.getTimeLimitForNoDeompressionTimeSimulation();
		for (double timeSpentOnSameLevel = 0; timeSpentOnSameLevel <= timeLimitForSimulation; timeSpentOnSameLevel += settings
				.getDeltaTForNoDecompressionTimeSimulation()) {
			double pigN2 = getPigN2(pigN2T0, settings.getDeltaTForNoDecompressionTimeSimulation(), compartment,
					ambientPressure, gasmix);
			double pigHe = getPigHe(pigHeT0, settings.getDeltaTForNoDecompressionTimeSimulation(), compartment,
					ambientPressure, gasmix);
			double pAmbientTolerated = getPAmbientToleratedWithGradients(compartment, pigN2, pigHe,
					settings.getGfForNoDecompressionLimitCalculation());
			if (pAmbientTolerated > this.pressureOnSurface) {
				if (timeSpentOnSameLevel == 0) {
					// it is a deco-Dive
					return Double.NaN;
				} else {
					return timeSpentOnSameLevel;
				}
			}
			pigHeT0 = pigHe;
			pigN2T0 = pigN2;
		}
		return timeLimitForSimulation;
	}

	private double getPAmbientTolerated(Compartment compartment, double pigN2, double pigHe) {
		double a = getAValue(pigN2, pigHe, compartment);
		double b = getBValue(pigN2, pigHe, compartment);
		double pAmbientTolerated = ((pigN2 + pigHe) - a) * b;
		return pAmbientTolerated;
	}

	private double getActualGF() {

		if (this.gfDeepestStop == null) {
			return settings.getGfLow();
		} else {
			return settings.getGfHigh()
					- (((settings.getGfHigh() - settings.getGfLow()) / (gfDeepestStop)) * actualStopDepth);
		}

	}

	private double getPAmbientToleratedWithGradients(Compartment compartment, double pigN2, double pigHe, double gf) {
		double a = getAValue(pigN2, pigHe, compartment);
		double b = getBValue(pigN2, pigHe, compartment);
		a = a * gf;
		b = b / (gf - gf * b + b);

		double pAmbientTolerated = ((pigN2 + pigHe) - a) * b;
		return pAmbientTolerated;
	}

	private double getPigN2(double pigN2T0, double timeOnDepth, Compartment compartment, double ambientPressure,
			Gasmix gasmix) {
		double piigN2 = (ambientPressure - settings.getPsH2O()) * gasmix.getN2();
		double pigN2 = pigN2T0
				+ (piigN2 - pigN2T0) * (1 - Math.pow(2, -1.0 * timeOnDepth / compartment.getHalfTimeValueN2()));
		return pigN2;
	}

	private double getPigHe(double pigHeT0, double timeOnDepth, Compartment compartment, double ambientPressure,
			Gasmix gasmix) {
		double piigHe = (ambientPressure - settings.getPsH2O()) * gasmix.getHe();

		double pigHe = pigHeT0
				+ (piigHe - pigHeT0) * (1 - Math.pow(2, -1.0 * timeOnDepth / compartment.getHalfTimeValueHe()));
		return pigHe;
	}

	private double getBValue(double pigN2, double pigHe, Compartment compartment) {
		return ((compartment.getbN2() * pigN2) + (compartment.getbHe() * pigHe)) / (pigHe + pigN2);
	}

	private double getAValue(double pigN2, double pigHe, Compartment compartment) {
		return ((compartment.getaN2() * pigN2) + (compartment.getaHe() * pigHe)) / (pigHe + pigN2);
	}

	private double toAmbientPressure(double depth) {
		return this.pressureOnSurface + (depth * water.getWeightPerLiter() / 10.0);
	}

	public void addCalculatorListener(CalculatorListener listener) {
		this.listeners.add(listener);
	}

	private void notify(double actualPointOfTimeInProfile) {
		for (CalculatorListener listener : listeners) {
			listener.calculatorEvent(actualPointOfTimeInProfile);
		}
	}

}
