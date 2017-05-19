package ch.laurinmueller.zhl16;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.laurinmueller.zhl16.exception.InvalidGasmixException;

public class Gasmix implements Serializable {
	private static final long serialVersionUID = 1L;

	public static List<Gasmix> PREDIFINED_MIXES = new ArrayList<>();
	public static Gasmix AIR;
	public static Gasmix NITROX_32;
	public static Gasmix NITROX_40;
	public static Gasmix TRIOX_21_35;
	public static Gasmix OXYGEN_100;
	static {
		try {
			Gasmix.AIR = new Gasmix(0.21, 0.79, 0, "Air");
			PREDIFINED_MIXES.add(AIR);
			Gasmix.NITROX_32 = new Gasmix(0.32, 0.68, 0, "Nitrox 32");
			PREDIFINED_MIXES.add(NITROX_32);
			Gasmix.NITROX_40 = new Gasmix(0.40, 0.60, 0, "Nitrox 40");
			PREDIFINED_MIXES.add(NITROX_40);
			Gasmix.TRIOX_21_35 = new Gasmix(0.21, 0.44, 0.35, "Triox 21/35");
			PREDIFINED_MIXES.add(TRIOX_21_35);
			Gasmix.OXYGEN_100 = new Gasmix(1.0, 0, 0, "Oxygen Pure");
			PREDIFINED_MIXES.add(OXYGEN_100);
		} catch (InvalidGasmixException e) {
			throw new RuntimeException(e);
		}
	}

	private final double o2;
	private final double n2;
	private final double he;
	private final String name;

	public Gasmix(double o2, double n2, double he, String name) throws InvalidGasmixException {
		validate(o2, n2, he);
		this.o2 = o2;
		this.n2 = n2;
		this.he = he;
		this.name = name;
	}

	private void validate(double o2, double n2, double he) throws InvalidGasmixException {
		double gasTotal = o2 + n2 + he;
		if (gasTotal > 1.0001 || gasTotal < 0.989999) {
			throw new InvalidGasmixException(o2, n2, he,
					"Gasmix invalid, 99% or 100% expected in total. But was O2(" + (o2 * 100) + ") + N2(" + (n2 * 100)
							+ ") + He(" + (he * 100) + ") = Total (" + (gasTotal * 100) + ")");
		}
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

	@Override
	public String toString() {
		String gasmixString = getAsShortString();
		return name != null ? name + ", " + gasmixString : gasmixString;
	}

	public String getAsShortString() {
		return "O2: " + (o2 * 100) + " %, N2: " + (n2 * 100) + " %, He: " + (he * 100) + " %";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(he);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(n2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(o2);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gasmix other = (Gasmix) obj;
		if (Double.doubleToLongBits(he) != Double.doubleToLongBits(other.he))
			return false;
		if (Double.doubleToLongBits(n2) != Double.doubleToLongBits(other.n2))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(o2) != Double.doubleToLongBits(other.o2))
			return false;
		return true;
	}

}
