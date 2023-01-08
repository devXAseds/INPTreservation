package com.inpt.reservation;

public abstract class Local {
	protected String idlocal ; 
	private static int DureeMax=120 ; 
	
	Local(String id )
	{
		this.idlocal = id ; 
		
	}
	
	

	public String getId() {
		return idlocal ;
	}
	
	static public int getDureeMax() {
		return DureeMax ;
	}
	static public void setDureeMax(int d) {
		Local.DureeMax=d ; 
	}

	public void setId(String id) {
		this.idlocal = id ;
	}
	
	
	
	
	
}
