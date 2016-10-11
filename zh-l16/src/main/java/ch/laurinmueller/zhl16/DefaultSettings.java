package ch.laurinmueller.zhl16;

public class DefaultSettings implements CalcSettings {
	/**
	 * Sättigungsdampfdruck von Wasser bei T=37°C
	 */
	private double psH2O = 0.063;

	private double meterAboveSeaLevel = 0.0;

	private double caluculationResolution = 2.0;

	/**
	 * time limit for NoDecoTime-Simulation in Seconds. The alogrithm stops the
	 * calculation when no deco time reaches this time
	 */
	private double timeLimitForNoDeompressionTimeSimulation = 200 * 60;

	/**
	 * defines the timeSteps in Seconds for the no decompression simulation.
	 * Greater numbers makes the algorithm faster, but less accurate
	 */
	private double deltaTForNoDecompressionTimeSimulation = 60;

	private double gfLow = 0.35;

	private double gfHigh = 0.80;

	private double gfForNoDecompressionLimitCalculation = 1.0;

	/**
	 * The deompression level incrementations in meter
	 */
	private double decompressionLevelDelta = 3.0;

	/**
	 * meter per per Second: default 0.1333333 (8 meter per minute)
	 */
	private double ascentrate = 0.13333333;

	/**
	 * minimum decompression stop length in seconds
	 */
	private double minDecoStopLength = 60;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getPsH2O()
	 */
	@Override
	public double getPsH2O() {
		return psH2O;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getMeterAboveSeaLevel()
	 */
	@Override
	public double getMeterAboveSeaLevel() {
		return meterAboveSeaLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getCaluculationResolution()
	 */
	@Override
	public double getCaluculationResolution() {
		return caluculationResolution;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#
	 * getTimeLimitForNoDeompressionTimeSimulation()
	 */
	@Override
	public double getTimeLimitForNoDeompressionTimeSimulation() {
		return timeLimitForNoDeompressionTimeSimulation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#
	 * getDeltaTForNoDecompressionTimeSimulation()
	 */
	@Override
	public double getDeltaTForNoDecompressionTimeSimulation() {
		return deltaTForNoDecompressionTimeSimulation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getGfLow()
	 */
	@Override
	public double getGfLow() {
		return gfLow;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getGfHigh()
	 */
	@Override
	public double getGfHigh() {
		return gfHigh;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getDecompressionLevelDelta()
	 */
	@Override
	public double getDecompressionLevelDelta() {
		return decompressionLevelDelta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getAscentrate()
	 */
	@Override
	public double getAscentrate() {
		return ascentrate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.laurinmueller.zhl16.CalcSettings#getMinDecoStopLength()
	 */
	@Override
	public double getMinDecoStopLength() {
		return minDecoStopLength;
	}

	@Override
	public double getGfForNoDecompressionLimitCalculation() {
		return gfForNoDecompressionLimitCalculation;
	}

}
