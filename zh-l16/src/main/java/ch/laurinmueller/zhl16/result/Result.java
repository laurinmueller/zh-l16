package ch.laurinmueller.zhl16.result;

import java.util.ArrayList;

import ch.laurinmueller.zhl16.DiveProfile;

public class Result extends ArrayList<ResultEntry> {

	private double pressureOnSurface;
	private DiveProfile diveProfile;

	public Result(DiveProfile diveProfile, double pressureOnSurface) {
		this.diveProfile = diveProfile;
		this.pressureOnSurface = pressureOnSurface;
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
}
