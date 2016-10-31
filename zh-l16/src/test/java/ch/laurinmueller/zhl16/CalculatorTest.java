package ch.laurinmueller.zhl16;

import org.junit.Assert;
import org.junit.Test;

import ch.laurinmueller.zhl16.exception.DecoLimitViolationException;
import ch.laurinmueller.zhl16.exception.InvalidGasmixException;
import ch.laurinmueller.zhl16.kompartiment.Compartment;
import ch.laurinmueller.zhl16.kompartiment.CompartmentDefinition;
import ch.laurinmueller.zhl16.kompartiment.CompartmentDefinitionFactory;
import ch.laurinmueller.zhl16.kompartiment.CompartmentDefinitionFactory.Algorithm;
import ch.laurinmueller.zhl16.result.Result;

public class CalculatorTest {

	@Test
	public void testCalcPressureOnSurfaceAboveSealevel1000Meter() {
		double pressure = BarometerUtil.calcPressureOnSurface(1000);
		Assert.assertEquals(0.8912, pressure, 0.01);
	}

	@Test
	public void testCalcPressureOnSurfaceAboveSealevelZugspitze() {
		double pressure = BarometerUtil.calcPressureOnSurface(2962);
		Assert.assertEquals(0.6928, pressure, 0.01);
	}

	@Test
	public void testCalcPressureOnSurfaceOnSeaLevel() {
		double pressure = BarometerUtil.calcPressureOnSurface(0);
		Assert.assertEquals(1.01325, pressure, 0.01);
	}

	@Test
	public void testCalculation() throws DecoLimitViolationException {
		long start = System.currentTimeMillis();
		DiveProfile profile = new DiveProfile();
		ProfilePart part = new ProfilePart(10, 300, Gasmix.AIR);
		profile.addPart(part);
		ProfilePart part2 = new ProfilePart(30, 300, Gasmix.AIR);
		profile.addPart(part2);
		ProfilePart part3 = new ProfilePart(20, 3000, Gasmix.AIR);
		profile.addPart(part3);
		// ProfilePart part4 = new ProfilePart(6, 300, Gasmix.AIR);
		// profile.addPart(part4);
		CompartmentDefinition compartments = CompartmentDefinitionFactory.create(Algorithm.ZH_L16_C);
		Calculator calculator = new Calculator(profile, compartments, Water.FRESH, new DefaultSettings());
		Result result = calculator.getResult();

		for (Compartment compartment : compartments.getCompartments()) {
			System.out.println(
					compartment.getName() + ": Pig final: " + result.getLast().getSnapshot(compartment).getPig());
			System.out.println(
					compartment.getName() + ": PigN2 final: " + result.getLast().getSnapshot(compartment).getPigN2());
			System.out.println(
					compartment.getName() + ": PigHe final: " + result.getLast().getSnapshot(compartment).getPigHe());

		}
		System.out.println("Pressure on Surface: " + result.getPressureOnSurface());
		System.out.println("Determining Compartement: "
				+ result.getLast().getDeterminingCompartementEntry().getKey().getName() + " pAmbientTolerated: "
				+ result.getLast().getDeterminingCompartementEntry().getValue().getpAmbientTolerated());

		System.out.println("Determining Compartement with GF: "
				+ result.getLast().getDeterminingCompartementWithGFEntry().getKey().getName()
				+ " pAmbientToleratedWithGF: "
				+ result.getLast().getDeterminingCompartementWithGFEntry().getValue().getpAmbientToleratedWithGF());
		System.out.println("No DecoTime(s): " + result.getLast().getNoDecompressionTime());
		System.out.println("No DecoTime(min): " + result.getLast().getNoDecompressionTime() / 60.0);
		System.out.println("Total Dive Time: " + result.getDiveProfile().getDiveProfileDuration() + "(s), "
				+ result.getDiveProfile().getDiveProfileDuration() / 60 + "(min)");

		System.out.println("Test duration(ms): " + (System.currentTimeMillis() - start));
	}

	@Test
	public void testCalculationMultiDecoReferenz() throws DecoLimitViolationException, InvalidGasmixException {
		long start = System.currentTimeMillis();
		Gasmix gasmix = new Gasmix(0.21, 0.39, 0.4, "triox");
		DiveProfile profile = new DiveProfile();
		ProfilePart part = new ProfilePart(60, 180, Gasmix.AIR);
		profile.addPart(part);
		ProfilePart part2 = new ProfilePart(30, 180, Gasmix.AIR);
		profile.addPart(part2);
		ProfilePart part3 = new ProfilePart(30, 3600, Gasmix.AIR);
		profile.addPart(part3);
		CompartmentDefinition compartments = CompartmentDefinitionFactory.create(Algorithm.ZH_L16_C);
		Calculator calculator = new Calculator(profile, compartments, Water.FRESH, new DefaultSettings());
		Result result = calculator.getResult();

		for (Compartment compartment : compartments.getCompartments()) {
			System.out.println(
					compartment.getName() + ": Pig final: " + result.getLast().getSnapshot(compartment).getPig());
			System.out.println(
					compartment.getName() + ": PigN2 final: " + result.getLast().getSnapshot(compartment).getPigN2());
			System.out.println(
					compartment.getName() + ": PigHe final: " + result.getLast().getSnapshot(compartment).getPigHe());

		}
		System.out.println("Pressure on Surface: " + result.getPressureOnSurface());
		System.out.println("Determining Compartement: "
				+ result.getLast().getDeterminingCompartementEntry().getKey().getName() + " pAmbientTolerated: "
				+ result.getLast().getDeterminingCompartementEntry().getValue().getpAmbientTolerated());

		System.out.println("Determining Compartement with GF: "
				+ result.getLast().getDeterminingCompartementWithGFEntry().getKey().getName()
				+ " pAmbientToleratedWithGF: "
				+ result.getLast().getDeterminingCompartementWithGFEntry().getValue().getpAmbientToleratedWithGF());
		System.out.println("No DecoTime(s): " + result.getLast().getNoDecompressionTime());
		System.out.println("No DecoTime(min): " + result.getLast().getNoDecompressionTime() / 60.0);
		System.out.println("Total Dive Time: " + result.getDiveProfile().getDiveProfileDuration() + "(s), "
				+ result.getDiveProfile().getDiveProfileDuration() / 60 + "(min)");

		System.out.println("Test duration(ms): " + (System.currentTimeMillis() - start));
	}

}
