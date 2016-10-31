package ch.laurinmueller.zhl16;

public interface CalculatorListener {
	/**
	 * called when the calculator has done one step further in the calculation
	 * process
	 * 
	 * @param actualPointOfTimeInProfile
	 */
	public void calculatorEvent(double actualPointOfTimeInProfile);

}
