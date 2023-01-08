package com.inpt.reservation;

public class localNotDispoExcep extends Exception  {
	
	localNotDispoExcep(){
		super("Ce local est deja reservée pour cette date ! ");
		
	}

}
