package com.inpt.reservation;

import java.util.Comparator;

public class IdComparator implements Comparator<Reservation>  {


	@Override
	public int compare(Reservation o1, Reservation o2) {
		
		 return o1.getId() - o2.getId() ; 
		 }

	
}
