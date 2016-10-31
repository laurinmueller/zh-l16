package ch.laurinmueller.zhl16.kompartiment;

import java.util.ArrayList;
import java.util.List;

public class CompartmentDefinitionFactory {
	public enum Algorithm {
		ZH_L16_A, ZH_L16_B, ZH_L16_C;
	}

	public static CompartmentDefinition create(Algorithm algorithm) {
		if (algorithm == Algorithm.ZH_L16_A) {
			return createZH_L16A();
		} else if (algorithm == Algorithm.ZH_L16_B) {
			return createZH_L16B();
		} else if (algorithm == Algorithm.ZH_L16_C) {
			return createZH_L16C();
		}
		return null;
	}

	private static CompartmentDefinition createZH_L16A() {
		List<Compartment> compartements = new ArrayList<>();
		compartements.add(new Compartment("1", 4, 1.2600, 0.5050, 1.5, 1.7435, 0.1911));
		compartements.add(new Compartment("2", 8, 1.0001, 0.6514, 3.0, 1.3838, 0.4295));
		compartements.add(new Compartment("3", 12.5, 0.8618, 0.7222, 4.7, 1.1925, 0.5446));
		compartements.add(new Compartment("4", 18.5, 0.7562, 0.7725, 7.0, 1.0465, 0.6265));
		compartements.add(new Compartment("5", 27, 0.6667, 0.8125, 10.2, 0.9226, 0.6917));
		compartements.add(new Compartment("6", 38.3, 0.5933, 0.8434, 14.5, 0.8211, 0.7420));
		compartements.add(new Compartment("7", 54.3, 0.5282, 0.8693, 20.5, 0.7309, 0.7841));
		compartements.add(new Compartment("8", 77, 0.4701, 0.8910, 29.1, 0.6506, 0.8195));
		compartements.add(new Compartment("9", 109, 0.4187, 0.9092, 41.1, 0.5794, 0.8491));
		compartements.add(new Compartment("10", 146, 0.3798, 0.9222, 55.1, 0.5256, 0.8703));
		compartements.add(new Compartment("11", 187, 0.3497, 0.9319, 70.6, 0.4840, 0.8860));
		compartements.add(new Compartment("12", 239, 0.3223, 0.9403, 90.2, 0.4460, 0.8997));
		compartements.add(new Compartment("13", 305, 0.2971, 0.9477, 115.1, 0.4112, 0.9118));
		compartements.add(new Compartment("14", 390, 0.2737, 0.9544, 147.2, 0.3788, 0.9226));
		compartements.add(new Compartment("15", 498, 0.2523, 0.9602, 187.9, 0.3492, 0.9321));
		compartements.add(new Compartment("16", 635, 0.2327, 0.9653, 239.6, 0.3220, 0.9404));

		return new CompartmentDefinition("ZH-L16 A", compartements);
	}

	private static CompartmentDefinition createZH_L16B() {
		List<Compartment> compartements = new ArrayList<>();
		compartements.add(new Compartment("1", 4, 1.2600, 0.5050, 1.5, 1.7435, 0.1911));
		compartements.add(new Compartment("2", 8, 1.0001, 0.6514, 3.0, 1.3838, 0.4295));
		compartements.add(new Compartment("3", 12.5, 0.8618, 0.7222, 4.7, 1.1925, 0.5446));
		compartements.add(new Compartment("4", 18.5, 0.7562, 0.7725, 7.0, 1.0465, 0.6265));
		compartements.add(new Compartment("5", 27, 0.6667, 0.8125, 10.2, 0.9226, 0.6917));
		compartements.add(new Compartment("6", 38.3, 0.5600, 0.8434, 14.5, 0.8211, 0.7420));
		compartements.add(new Compartment("7", 54.3, 0.4947, 0.8693, 20.5, 0.7309, 0.7841));
		compartements.add(new Compartment("8", 77, 0.4500, 0.8910, 29.1, 0.6506, 0.8195));
		compartements.add(new Compartment("9", 109, 0.4188, 0.9092, 41.1, 0.5794, 0.8491));
		compartements.add(new Compartment("10", 146, 0.3799, 0.9222, 55.1, 0.5256, 0.8703));
		compartements.add(new Compartment("11", 187, 0.3497, 0.9319, 70.6, 0.4840, 0.8860));
		compartements.add(new Compartment("12", 239, 0.3223, 0.9403, 90.2, 0.4460, 0.8997));
		compartements.add(new Compartment("13", 305, 0.2850, 0.9477, 115.1, 0.4112, 0.9118));
		compartements.add(new Compartment("14", 390, 0.2738, 0.9544, 147.2, 0.3788, 0.9226));
		compartements.add(new Compartment("15", 498, 0.2524, 0.9602, 187.9, 0.3492, 0.9321));
		compartements.add(new Compartment("16", 635, 0.2327, 0.9653, 239.6, 0.3220, 0.9404));

		return new CompartmentDefinition("ZH-L16 B", compartements);
	}

	private static CompartmentDefinition createZH_L16C() {
		List<Compartment> compartements = new ArrayList<>();
		compartements.add(new Compartment("1", 4, 1.2600, 0.5050, 1.5, 1.7435, 0.1911));
		compartements.add(new Compartment("2", 8, 1.0001, 0.6514, 3.0, 1.3838, 0.4295));
		compartements.add(new Compartment("3", 12.5, 0.8618, 0.7222, 4.7, 1.1925, 0.5446));
		compartements.add(new Compartment("4", 18.5, 0.7562, 0.7725, 7.0, 1.0465, 0.6265));
		compartements.add(new Compartment("5", 27, 0.6200, 0.8125, 10.2, 0.9226, 0.6917));
		compartements.add(new Compartment("6", 38.3, 0.5034, 0.8434, 14.5, 0.8211, 0.7420));
		compartements.add(new Compartment("7", 54.3, 0.4410, 0.8693, 20.5, 0.7309, 0.7841));
		compartements.add(new Compartment("8", 77, 0.4000, 0.8910, 29.1, 0.6506, 0.8195));
		compartements.add(new Compartment("9", 109, 0.3750, 0.9092, 41.1, 0.5794, 0.8491));
		compartements.add(new Compartment("10", 146, 0.3500, 0.9222, 55.1, 0.5256, 0.8703));
		compartements.add(new Compartment("11", 187, 0.3295, 0.9319, 70.6, 0.4840, 0.8860));
		compartements.add(new Compartment("12", 239, 0.3065, 0.9403, 90.2, 0.4460, 0.8997));
		compartements.add(new Compartment("13", 305, 0.2835, 0.9477, 115.1, 0.4112, 0.9118));
		compartements.add(new Compartment("14", 390, 0.2610, 0.9544, 147.2, 0.3788, 0.9226));
		compartements.add(new Compartment("15", 498, 0.2480, 0.9602, 187.9, 0.3492, 0.9321));
		compartements.add(new Compartment("16", 635, 0.2327, 0.9653, 239.6, 0.3220, 0.9404));

		return new CompartmentDefinition("ZH-L16 C", compartements);
	}

}
