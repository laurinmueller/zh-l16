package ch.laurinmueller.zhl16;

public enum Water {
	SALT(1.025), FRESH(1.0);

	private double weightPerLiter;

	Water(double weightPerLiter) {
		this.weightPerLiter = weightPerLiter;
	}

	public double getWeightPerLiter() {
		return weightPerLiter;
	}
}
