package ch.laurinmueller.zhl16;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiveProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<ProfilePart> profileParts = new ArrayList<>();

	public List<ProfilePart> getProfileParts() {
		return profileParts;
	}

	public DiveProfile() {
		profileParts.add(new ProfilePart(0.0, 0.0, Gasmix.AIR));
	}

	public void addPart(ProfilePart part) {
		this.profileParts.add(part);
	}

	public void removePart(ProfilePart part) {
		this.profileParts.remove(part);
	}

	public void clear() {
		ProfilePart first = profileParts.get(0);
		profileParts.clear();
		profileParts.add(first);
	}

	public double getDepth(double timeInSecondsAfterStart) {
		if (timeInSecondsAfterStart == 0) {
			return 0;
		}
		ProfilePart last = null;
		double timePast = 0;
		for (ProfilePart part : profileParts) {
			if (timePast + part.getDeltaT() >= timeInSecondsAfterStart) {
				return (((part.getDepth() - last.getDepth()) / part.getDeltaT()) * (timeInSecondsAfterStart - timePast))
						+ last.getDepth();
			}
			last = part;
			timePast += part.getDeltaT();
		}
		throw new IllegalArgumentException("time range exceeded");
	}

	public Gasmix getGasmix(double timeInSecondsAfterStart) {

		double timePast = 0;
		for (ProfilePart part : profileParts) {
			if (timePast + part.getDeltaT() >= timeInSecondsAfterStart) {
				return part.getGasmix();
			}
			timePast += part.getDeltaT();
		}
		throw new IllegalArgumentException("time range exceeded");
	}

	public double getDiveProfileDuration() {
		double duration = 0;
		for (ProfilePart part : profileParts) {
			duration += part.getDeltaT();
		}
		return duration;
	}

	public DiveProfile getCopy() {
		try (ByteArrayOutputStream byteOutputSteam = new ByteArrayOutputStream()) {
			try (ObjectOutputStream out = new ObjectOutputStream(byteOutputSteam)) {
				out.writeObject(this);
				try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
						byteOutputSteam.toByteArray())) {
					try (ObjectInputStream in = new ObjectInputStream(byteArrayInputStream)) {
						return (DiveProfile) in.readObject();
					}
				}
			}

		} catch (Exception exception) {
			throw new RuntimeException("error while getting copy of the diveProfile", exception);
		}
	}

	public double getMaxDepth() {
		double maxDepth = 0;
		for (ProfilePart part : profileParts) {
			if (part.getDepth() > maxDepth) {
				maxDepth = part.getDepth();
			}
		}
		return maxDepth;
	}

}
