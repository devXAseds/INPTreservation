package com.inpt.reservation;

import java.util.Comparator;

public class DateComparator  implements Comparator<Reservation> {

	
	//Integer.parseInt(t.substring(0, 2))==24 &&(Integer.parseInt(t.substring(3, 5))!=0 ||Integer.parseInt(t.substring(6, 8))
			
			
	int getSec(String t , String t2){
		int sec1 = Integer.parseInt(t.substring(0, 2))*60*60 + Integer.parseInt(t.substring(3, 5))*60 + Integer.parseInt(t.substring(6, 8) )   ; 
		int sec2 = Integer.parseInt(t2.substring(0, 2))*60*60 + Integer.parseInt(t2.substring(3, 5))*60 + Integer.parseInt(t2.substring(6, 8) )   ; 
		return sec1-sec2 ; 
		
	}
	@Override
	public int compare(Reservation o1, Reservation o2) {
		if(!o1.getDate().equals(o2.getDate())) return o1.getDate().compareTo(o2.getDate());
		return getSec(o1.getTime() , o2.getTime()) ;
	}

}
