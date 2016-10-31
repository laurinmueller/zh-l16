package ch.laurinmueller.zhl16.exception;

public class DecoLimitViolationException extends Exception {

	private double deltaT;

	public DecoLimitViolationException(double deltaT, String message) {
		super(message);
		this.deltaT = deltaT;
	}

	/**
	 * returns the time passed(in Seconds) after the beginning of the Dive when
	 * the Violation occurs;
	 * 
	 * @return
	 */
	public double getDeltaT() {
		return this.deltaT;
	}

}
