package com.inpt.reservation;

public class DureeMaxExcep extends Exception {
	DureeMaxExcep(){
		super("la duree de reservation ne peut pax exceder "+Local.getDureeMax() + "minutes") ; 
	}
}
