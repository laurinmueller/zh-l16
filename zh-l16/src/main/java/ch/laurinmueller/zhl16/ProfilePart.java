package ch.laurinmueller.zhl16;

import java.io.Serializable;

public class ProfilePart implements Serializable {
	private static final long serialVersionUID = 1L;

	private final double depth;
	private final double deltaT;
	private final Gasmix gasmix;

	public ProfilePart(double depth, double deltaT, Gasmix gasmix) {
		this.depth = depth;
		this.deltaT = deltaT;
		this.gasmix = gasmix;
	}

	/**
	 * seconds
	 * 
	 * @return
	 */
	public double getDeltaT() {
		return deltaT;
	}

	/**
	 * used gasmix since last ProfilePart
	 * 
	 * @return
	 */
	public Gasmix getGasmix() {
		return gasmix;
	}

	/**
	 * depth in metres
	 * 
	 * @return
	 */
	public double getDepth() {
		return depth;
	}

}
