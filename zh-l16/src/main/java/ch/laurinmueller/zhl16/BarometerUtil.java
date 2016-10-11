package ch.laurinmueller.zhl16;

public class BarometerUtil {
	/**
	 * pressure in Bar
	 * 
	 * @param meterAboveSealevel
	 * @return
	 */
	public static double calcPressureOnSurface(double meterAboveSealevel) {
		return 1.01325 * Math.exp(-meterAboveSealevel / 7990.0);
	}
}
