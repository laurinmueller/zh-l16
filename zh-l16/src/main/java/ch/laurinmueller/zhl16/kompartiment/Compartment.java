package ch.laurinmueller.zhl16.kompartiment;

public class Compartment {
	private String name;

	private double halfTimeValueN2;
	private double aN2;
	private double bN2;

	private double halfTimeValueHe;
	private double aHe;
	private double bHe;

	public Compartment(String name, double halfTimeValueN2, double aN2, double bN2, double halfTimeValueHe, double aHe,
			double bHe) {
		this.name = name;
		this.halfTimeValueHe = halfTimeValueHe * 60.0;
		this.halfTimeValueN2 = halfTimeValueN2 * 60.0;
		this.aHe = aHe;
		this.aN2 = aN2;
		this.bHe = bHe;
		this.bN2 = bN2;
	}

	/**
	 * N2 halftime-value in Seconds
	 * 
	 * @return
	 */
	public double getHalfTimeValueN2() {
		return halfTimeValueN2;
	}

	/**
	 * N2 halftime-value in Seconds
	 * 
	 * @param halfTimeValueN2
	 */
	public void setHalfTimeValueN2(double halfTimeValueN2) {
		this.halfTimeValueN2 = halfTimeValueN2;
	}

	public double getaN2() {
		return aN2;
	}

	public void setaN2(double aN2) {
		this.aN2 = aN2;
	}

	public double getbN2() {
		return bN2;
	}

	public void setbN2(double bN2) {
		this.bN2 = bN2;
	}

	/**
	 * He halftime-value in Seconds
	 * 
	 * @return
	 */
	public double getHalfTimeValueHe() {
		return halfTimeValueHe;
	}

	/**
	 * He halftime-value in Seconds
	 * 
	 * @param halfTimeValueHe
	 */
	public void setHalfTimeValueHe(double halfTimeValueHe) {
		this.halfTimeValueHe = halfTimeValueHe;
	}

	public double getaHe() {
		return aHe;
	}

	public void setaHe(double aHe) {
		this.aHe = aHe;
	}

	public double getbHe() {
		return bHe;
	}

	public void setbHe(double bHe) {
		this.bHe = bHe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
