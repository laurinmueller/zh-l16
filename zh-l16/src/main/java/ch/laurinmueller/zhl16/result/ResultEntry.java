package ch.laurinmueller.zhl16.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.laurinmueller.zhl16.kompartiment.Compartment;
import ch.laurinmueller.zhl16.kompartiment.CompartmentComparer;

public class ResultEntry {
	private double deltaT;

	Map<Compartment, Snapshot> compartementSnapshot = new HashMap<>();

	public List<Compartment> getCompartments() {
		List<Compartment> arrayList = new ArrayList<>(compartementSnapshot.keySet());
		Collections.sort(arrayList, new CompartmentComparer());
		return arrayList;
	}

	private Result result;

	public Snapshot getSnapshot(Compartment compartment) {
		return compartementSnapshot.get(compartment);
	}

	public void addSnapshot(Compartment compartment, Snapshot snapshot) {
		compartementSnapshot.put(compartment, snapshot);
	}

	/**
	 * no decompression time in Seconds at the specific time of the dive. If it
	 * is a deco dive, this method return at this time of dive
	 * {@link Double.NaN}
	 * 
	 * @return
	 */
	public double getNoDecompressionTime() {
		Entry<Compartment, Snapshot> entryWithLowestNoDecompressionTime = null;
		for (Entry<Compartment, Snapshot> entry : compartementSnapshot.entrySet()) {
			if (Double.isNaN(entry.getValue().getNoDecompressionTime())) {
				return Double.NaN;
			}
			if (entryWithLowestNoDecompressionTime == null) {
				entryWithLowestNoDecompressionTime = entry;
			} else if (entryWithLowestNoDecompressionTime.getValue().getNoDecompressionTime() > entry.getValue()
					.getNoDecompressionTime()) {
				entryWithLowestNoDecompressionTime = entry;
			}
		}
		return entryWithLowestNoDecompressionTime.getValue().getNoDecompressionTime();
	}

	/**
	 * This method returns the past Time since the start of the dive in Seconds
	 * 
	 * @return
	 */
	public double getDeltaT() {
		return deltaT;
	}

	public void setDeltaT(double deltaT) {
		this.deltaT = deltaT;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * gets the compartment which determines the minimum Depth
	 * 
	 * @return
	 */
	public Entry<Compartment, Snapshot> getDeterminingCompartementEntry() {
		Entry<Compartment, Snapshot> determiningEntry = null;
		for (Entry<Compartment, Snapshot> entry : compartementSnapshot.entrySet()) {
			if (determiningEntry == null) {
				determiningEntry = entry;
			} else if (determiningEntry.getValue().getpAmbientTolerated() < entry.getValue().getpAmbientTolerated()) {
				determiningEntry = entry;
			}
		}
		return determiningEntry;

	}

	/**
	 * gets the compartment which determines the minimum Depth with considartion
	 * of the gradient-factors
	 * 
	 * @return
	 */
	public Entry<Compartment, Snapshot> getDeterminingCompartementWithGFEntry() {
		Entry<Compartment, Snapshot> determiningEntry = null;
		for (Entry<Compartment, Snapshot> entry : compartementSnapshot.entrySet()) {
			if (determiningEntry == null) {
				determiningEntry = entry;
			} else if (determiningEntry.getValue().getpAmbientToleratedWithGF() < entry.getValue()
					.getpAmbientToleratedWithGF()) {
				determiningEntry = entry;
			}
		}
		return determiningEntry;

	}
}
