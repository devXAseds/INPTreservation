package com.inpt.reservation;

import java.sql.Time;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.LinkedList;

import model.TestConnexion;

public class inptRes {
	
	private final static String admin="admin" ;
	private static String adminPass="azer12" ;
	private LinkedList<Etudiant> etudiants = new LinkedList<Etudiant>() ; 
	private LinkedList<SalleRevision> salles = new LinkedList<SalleRevision>() ;
	private LinkedList<TerrainSport> terrains = new LinkedList<TerrainSport>() ;
	private LinkedList<Reservation> reservations = new LinkedList<Reservation>() ;
	private LinkedList<String> filieres = new LinkedList<String>() ; 
	
	
	public static String getAdminPass() {
		return adminPass;
	}
	
	public LinkedList<String> getFilieres(){
		return filieres ; 
	}
	public static void setAdminPass(String adminPass) {
		inptRes.adminPass = adminPass;
	}
	public static String getAdmin() {
		return admin;
	}
	public LinkedList<Etudiant> getEtudiants() {
		return etudiants;
	}

	public LinkedList<SalleRevision> getSalles() {
		return salles;
	}

	public LinkedList<TerrainSport> getTerrains() {
		return terrains;
	}

	public LinkedList<Reservation> getReservations() {
		return reservations;
	}

	public void ajEtud(String n , String p , String cn  , String pass , String fil , String niv) throws Exception
	{
		for (int i =0 ; i < etudiants.size() ; i++) {
			if(etudiants.get(i).getCin().equals(cn)) throw new Exception("etudiant déja existant ! ") ; 
		}
		Etudiant e = new Etudiant(n , p , cn  , fil ,niv ,pass) ; 
		this.etudiants.add(e) ; 
		TestConnexion.ajEtud(e) ; 
	}
	
	public void saveData(String s , String cin) throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream(s+".ser") ; 
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this.getResEtud(cin)) ; 
		out.close();
		fileOut.close();
		
	}
	
	public void ajTer(String id , String sp ) throws Exception
	{
		for (int i =0 ; i < terrains.size() ; i++) {
			if(terrains.get(i).getId().equals(id)) throw new Exception("terrain déja existe ! ") ; 
		}
		TerrainSport t = new TerrainSport(id , sp ) ; 
		this.terrains.add(t) ; 
		TestConnexion.ajTer(t) ; 
	}
	
	public void ajSalle(String id , String f) throws Exception
	{
		for (int i =0 ; i < salles.size() ; i++) {
			if(salles.get(i).getId().equals(id)) throw new Exception("salle déja existe ! ") ; 
		}
		SalleRevision s = new SalleRevision(id ,f ) ; 
		this.salles.add(s);
		TestConnexion.ajSal(s) ; 
	}
	
	public boolean checkData(String u , String p) {
		for(int i =0 ; i<this.etudiants.size() ; i++) {
			if(etudiants.get(i).getCin().equals(u) && etudiants.get(i).getPass().equals(p) ) return true ; 
			
		}
		
		return false ; 
	}
	
	public String getFil(String id ) {
		for (int i =0 ; i < salles.size() ; i++) {
			if(salles.get(i).getId().equals(id)) return salles.get(i).getFiliere();
			}
	return null ; 
	}
	
	public String getEtudFil(String cin ) {
		for (int i =0 ; i < etudiants.size() ; i++) {
			if(etudiants.get(i).getCin().equals(cin)) return etudiants.get(i).getFiliere();
			}
	return null ; 
	}
	
	public void ajRes(int id ,Date d , int dur , String idloc , String cin , String type , String t ) throws Exception{
		
		Reservation r = new Reservation(id,d , dur , idloc , cin , type , t ) ; 
		this.reservations.add(r) ;
	}
	
	public void ajRes(Date d , int dur , String id , String cin , String type , String t ) throws Exception
	{
		for(int i =0 ; i<this.reservations.size() ; i++) {
			if(reservations.get(i).getIdLocal().equals(id) && reservations.get(i).getDate().equals(d) && reservations.get(i).getTime().equals(t)  && reservations.get(i).getType().equals(type) ) throw new localNotDispoExcep() ; 
		}
		
	
		if (dur>Local.getDureeMax()) {
				throw new DureeMaxExcep() ; 
			}
		if(type=="Salle de revision" && getFil(id).equals(getEtudFil(cin))==false )  {
	
			throw new FiliereNotCompatibleExc();
			
		}
	
		Reservation r = new Reservation(d , dur , id , cin , type , t ) ; 
		this.reservations.add(r) ; 
		TestConnexion.ajRes(r) ; 
	}
	
	public boolean checkTer(String id ) {
		for (int i =0 ; i < terrains.size() ; i++) {
			if(terrains.get(i).getId().equals(id)) return true ; 
		}
		
		return false ; 
	}
	
	public String getName(String cin )
	{
		for (int i =0 ; i < etudiants.size() ; i++) {
			if(etudiants.get(i).getCin().equals(cin)) return etudiants.get(i).getNom() + " "+etudiants.get(i).getPrenom() ;
			}
	return null ; 
	}
	
	public boolean checkSal(String id ) {
		for (int i =0 ; i < salles.size() ; i++) {
			if(salles.get(i).getId().equals(id)) return true ; 
		}
		
		return false ; 
	}
	
	 LinkedList<Reservation>  getResEtud(String id)
	{
		 LinkedList<Reservation> res = new LinkedList<Reservation>();
		 
		 for (int i =0 ; i < reservations.size() ; i++) {
				if(reservations.get(i).getCinEtud().equals(id)) res.add(reservations.get(i)) ; 
				}
		 
		 return res ; 
	}

	
	
	public void ajFiliere(String fil) {
		this.filieres.add(fil);
	}
	

	
	

}
