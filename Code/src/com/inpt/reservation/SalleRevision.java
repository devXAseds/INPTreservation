package com.inpt.reservation;

public class SalleRevision extends Local {
	
	

	private String filiere ; 
	
	
	SalleRevision(String id , String f ) {
			super(id);
			this.filiere = f ; 
		}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}


	 
	 
	 
	 
	
}
 