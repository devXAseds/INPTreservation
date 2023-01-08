package com.inpt.reservation;

import java.util.Comparator;

public class IdlocComparator implements Comparator<Reservation> {

	@Override
	public int compare(Reservation o1, Reservation o2) {
		
		return o1.getIdLocal().compareTo(o2.getIdLocal());
	}

}
