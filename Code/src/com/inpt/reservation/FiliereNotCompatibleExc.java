package com.inpt.reservation;

public class FiliereNotCompatibleExc extends Exception {

	FiliereNotCompatibleExc(){
		super("vous pouvez pas reserver cette salle ! ") ; 
	}
}
