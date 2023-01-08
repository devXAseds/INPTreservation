package com.inpt.reservation;

import java.sql.Time;
import java.io.Serializable;
import java.sql.Date;

public class Reservation implements Serializable {
	private int id ; 
	static private int nbrTotal = 0 ; 
	private Date date ; 
	private int duree ; 
	private String idLocal ; 
	private String cinEtud ; 
	private String type ; 
	private String time ; 
	
	
	Reservation(Date d , int dur , String id , String cin , String type , String t ){
	
		this.id=++Reservation.nbrTotal ; 
		this.date = d ; 
		this.duree = dur ; 
		this.idLocal = id ; 
		this.cinEtud = cin ; 
		this.type = type ; 
		this.time = t ; 
	}
	
	Reservation(int id , Date d , int dur , String idloc , String cin , String type , String t ){
		
		this.id=id ; 
		this.date = d ; 
		this.duree = dur ; 
		this.idLocal = idloc ; 
		this.cinEtud = cin ; 
		this.type = type ; 
		this.time = t ; 
		++Reservation.nbrTotal ; 
	}


	public int getId() {
		return id;
	}


	public Date getDate() {
		return date;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public String getIdLocal() {
		return idLocal;
	}


	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}


	public String getCinEtud() {
		return cinEtud;
	}


	public void setCinEtud(String cinEtud) {
		this.cinEtud = cinEtud;
	}


	public String getType() {
		return type;
	}
	public String getTime() {
		return time ; 
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override 
	public String toString() {    //Date d , int dur , String id , String cin , String type , String t
		
		return "id: " + id + " id local : " + idLocal + " type: " + type + " date: " +date.toString()+" "+time+" duree: "+duree +" min" ; 
		
	}
	
	
	
}
