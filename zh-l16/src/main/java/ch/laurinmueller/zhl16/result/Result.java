package ch.laurinmueller.zhl16.result;

import java.util.ArrayList;

import ch.laurinmueller.zhl16.DiveProfile;
import ch.laurinmueller.zhl16.kompartiment.CompartmentDefinition;

public class Result extends ArrayList<ResultEntry> {

	private double pressureOnSurface;
	private DiveProfile diveProfile;

	private CompartmentDefinition compartmentDefinition;

	public Result(DiveProfile diveProfile, double pressureOnSurface, CompartmentDefinition compartmentDefinition) {
		this.diveProfile = diveProfile;
		this.pressureOnSurface = pressureOnSurface;
		this.compartmentDefinition = compartmentDefinition;
	}

	public ResultEntry getLast() {
		return get(this.size() - 1);
	}

	public double getPressureOnSurface() {
		return pressureOnSurface;
	}

	public DiveProfile getDiveProfile() {
		return diveProfile;
	}

	public CompartmentDefinition getCompartmentDefinition() {
		return compartmentDefinition;
	}
}
