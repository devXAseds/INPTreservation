package com.inpt.reservation;

import java.util.Comparator;

public class DureeComparator  implements Comparator<Reservation>{

	@Override
	public int compare(Reservation o1, Reservation o2) {
		
		return o1.getDuree() - o2.getDuree();
	}

	
}
