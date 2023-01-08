package com.inpt.reservation;

import java.util.Objects;

public class Etudiant  {
	
	
	 String nom , prenom , cin , filiere , niv  , pass ; 
	
	public Etudiant(String n , String p , String cin, String fil , String niv , String pass) {
		this.cin = cin ;
		this.prenom = p ; 
		this.nom = n ; 
		this.pass = pass ; 
		this.filiere = fil ; 
		this.niv = niv ; 

	}
	
	
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getNiv() {
		return niv;
	}

	public void setNiv(String niv) {
		this.niv = niv;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return Objects.equals(cin, other.cin);
	}
	@Override 
	public String toString() {
		return cin +" " + nom +" " + prenom +" " + filiere +" " + niv +" " + pass ; 
	}
	
	
	
	
}
