package ch.laurinmueller.zhl16;

import org.junit.Assert;
import org.junit.Test;

public class ProfilePartTest {
	@Test
	public void getDepthSimple() {
		DiveProfile profile = new DiveProfile();
		ProfilePart part = new ProfilePart(10, 10, Gasmix.AIR);
		profile.addPart(part);
		Assert.assertEquals(5.0, profile.getDepth(5), 0.00001);
	}

	@Test
	public void getDepthAdvanced() {
		DiveProfile profile = new DiveProfile();
		ProfilePart part = new ProfilePart(10, 10, Gasmix.AIR);
		profile.addPart(part);
		ProfilePart part2 = new ProfilePart(20, 50, Gasmix.AIR);
		profile.addPart(part2);
		Assert.assertEquals(20, profile.getDepth(60), 0.00001);
		Assert.assertEquals(12.5, profile.getDepth(22.5), 0.00001);
	}

	@Test
	public void getDiveDuration() {
		DiveProfile profile = new DiveProfile();
		ProfilePart part = new ProfilePart(10, 10, Gasmix.AIR);
		profile.addPart(part);
		ProfilePart part2 = new ProfilePart(20, 50, Gasmix.AIR);
		profile.addPart(part2);
		Assert.assertEquals(60, profile.getDiveProfileDuration(), 0.00001);
	}

	@Test
	public void getGasmix() {
		DiveProfile profile = new DiveProfile();
		ProfilePart part = new ProfilePart(10, 10, Gasmix.NITROX_32);
		profile.addPart(part);
		ProfilePart part2 = new ProfilePart(20, 50, Gasmix.NITROX_40);
		profile.addPart(part2);
		Assert.assertEquals(Gasmix.AIR, profile.getGasmix(0));
		Assert.assertEquals(Gasmix.NITROX_32, profile.getGasmix(9));
		Assert.assertEquals(Gasmix.NITROX_40, profile.getGasmix(59.0));
		Assert.assertEquals(Gasmix.NITROX_40, profile.getGasmix(60));
	}
}
