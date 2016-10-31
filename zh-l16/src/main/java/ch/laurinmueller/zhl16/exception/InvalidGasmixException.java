package ch.laurinmueller.zhl16.exception;

public class InvalidGasmixException extends Exception {
	private final double o2;
	private final double n2;
	private final double he;

	public InvalidGasmixException(double o2, double n2, double he, String message) {
		super(message);
		this.o2 = o2;
		this.n2 = n2;
		this.he = he;
	}

	public double getO2() {
		return o2;
	}

	public double getN2() {
		return n2;
	}

	public double getHe() {
		return he;
	}

}
