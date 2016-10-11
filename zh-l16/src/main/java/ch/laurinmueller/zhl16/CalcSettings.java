package ch.laurinmueller.zhl16;

public interface CalcSettings {

	double getPsH2O();

	double getMeterAboveSeaLevel();

	double getCaluculationResolution();

	double getTimeLimitForNoDeompressionTimeSimulation();

	double getDeltaTForNoDecompressionTimeSimulation();

	double getGfLow();

	double getGfHigh();

	double getDecompressionLevelDelta();

	double getAscentrate();

	double getMinDecoStopLength();

	double getGfForNoDecompressionLimitCalculation();

}