package ch.laurinmueller.zhl16.kompartiment;

import java.util.List;

public class CompartmentDefinition {

	private final String name;
	private final List<Compartment> entries;

	public CompartmentDefinition(String name, List<Compartment> entries) {
		this.name = name;
		this.entries = entries;
	}

	public String getName() {
		return name;
	}

	public List<Compartment> getEntries() {
		return entries;
	}

	public int getNumberOfCompartment() {
		if (entries != null) {
			return entries.size();
		}
		return 0;
	}

	public Compartment getCompartementAt(int index) {
		return entries.get(index);
	}

	public List<Compartment> getCompartments() {
		return entries;
	}

}
