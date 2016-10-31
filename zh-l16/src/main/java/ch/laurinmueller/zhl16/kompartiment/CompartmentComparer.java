package ch.laurinmueller.zhl16.kompartiment;

import java.util.Comparator;

public class CompartmentComparer implements Comparator<Compartment> {

	@Override
	public int compare(Compartment o1, Compartment o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
