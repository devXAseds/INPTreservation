package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.inpt.reservation.Etudiant;
import com.inpt.reservation.Reservation;
import com.inpt.reservation.SalleRevision;
import com.inpt.reservation.TerrainSport;
import com.inpt.reservation.inptRes;

public class TestConnexion {
	
	
	public static void getData(inptRes res) throws ClassNotFoundException , SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String url = "jdbc:mysql://localhost:3306/inptres";
		Connection connexion=null;
		
			connexion = DriverManager.getConnection(url,"root","azer12");
			Statement st = connexion.createStatement();
			String requeteEtud = "select * from etudiant";
			PreparedStatement pSt= connexion.prepareStatement(requeteEtud);
			ResultSet resultat =pSt.executeQuery();
			while(resultat.next()) {
				try {
					res.ajEtud(resultat.getString("nom"), resultat.getString("prenom"), resultat.getString("cin"), resultat.getString("pass"), resultat.getString("filiere"), resultat.getString("niv"));
				} catch (Exception e) {
					//on vas appeler la fonction getData une seule fois lors du demarage de lapplication et la liste des etudiant va etre null 
					//alors on aura jamais cette exception pour cette fonction 
				}
				
			}
			
			String requeteSal = "select * from salle";
			pSt= connexion.prepareStatement(requeteSal);
			resultat =pSt.executeQuery();
			while(resultat.next()) {
				try {
					res.ajSalle(resultat.getString("idsal"), resultat.getString("filiere")) ; 
					
							} catch (Exception e) {
					//
				}
				
			}
			
			String requeteTer = "select * from terrain";
			pSt= connexion.prepareStatement(requeteTer);
			resultat =pSt.executeQuery();
			while(resultat.next()) {
				try {
					res.ajTer(resultat.getString("idter"), resultat.getString("sport")) ; 
					
							} catch (Exception e) {
				//
				}
				
			}
			
			String requeteRes = "select * from reservation";
			pSt= connexion.prepareStatement(requeteRes);
			resultat =pSt.executeQuery();
			while(resultat.next()) { //int id ,Date d , int dur , String idloc , String cin , String type , String t
				try {
					res.ajRes(resultat.getInt("id"), resultat.getDate("dat") ,resultat.getInt("duree"), resultat.getString("idlocal") , resultat.getString("cin") , resultat.getString("type") ,resultat.getString("tim")  ) ; 
					
							} catch (Exception e) {
				//
				}
				
			}
			
			
			
			
		
	}
	
	public static void ajEtud(Etudiant e ) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String url = "jdbc:mysql://localhost:3306/inptres";
		 
		Connection connexion = DriverManager.getConnection(url,"root","azer12");
		Statement st = connexion.createStatement();
		String requete= "insert into etudiant values ('"+e.getCin()+"','" +e.getNom()+"','" + e.getPrenom()+"','" + e.getPass()+"','" + e.getNiv()+"','" + e.getFiliere()+"');" ;  
		st.executeUpdate(requete);
	}
	
	public static void ajSal(SalleRevision s) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String url = "jdbc:mysql://localhost:3306/inptres";
		 
		Connection connexion = DriverManager.getConnection(url,"root","azer12");
		Statement st = connexion.createStatement();
		String requete= "insert into salle values ('"+s.getId()+"','"+s.getFiliere()+"');";
		st.executeUpdate(requete);
	}
	public static void ajTer(TerrainSport t) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String url = "jdbc:mysql://localhost:3306/inptres";
		 
		Connection connexion = DriverManager.getConnection(url,"root","azer12");
		Statement st = connexion.createStatement();
		String requete= "insert into terrain values ('"+t.getId()+"','"+t.getSport()+"');";
		st.executeUpdate(requete);
	}
	public static  void ajRes(Reservation r) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		String url = "jdbc:mysql://localhost:3306/inptres";
		 
		Connection connexion = DriverManager.getConnection(url,"root","azer12");
		Statement st = connexion.createStatement();
		String requete= "insert into reservation values ("+r.getId()+",'"+r.getIdLocal()+"','"+r.getCinEtud()+"','"+r.getType()+"','"+r.getDate()+"','"+r.getTime()+"',"+r.getDuree()+");";
		st.executeUpdate(requete);
	}
	public static void main(String[] args) throws ClassNotFoundException , SQLException {  
		
		
		
		
		
		
		
		
		/*
		// Chargement du pilote
		//Class.forName("com.mysql.jdbc.Driver"); 
		Class.forName("com.mysql.cj.jdbc.Driver");  
		
		//Etablissement de la connexion 
		//url -> protocole:sous-protocole://adrese:port/base
		//utiliser la m�thode getConnection(url, login, motDePasse) 
		//de la classe DriverManager 
		String url = "jdbc:mysql://localhost:3306/inptres";
		Connection connexion=null;
		
		try {
			connexion = DriverManager.getConnection(url,"root","azer12");
			//D�claration du statement
			Statement st = connexion.createStatement();
			//Ex�cution de la requ�te
			 * 
			 * 
			 */
		/*	
			
			String requete = "select * from city where countrycode = 'MAR'";
			ResultSet resultat = st.executeQuery(requete);
			//Exploitation des r�sultats
			while(resultat.next()) {
				//Possibilit� 1 : Utiliser les noms des colonnes
				System.out.println(resultat.getString("name")+" "+resultat.getInt("population"));
				//Possibilit� 2 : Utiliser les indices des colonnes
				System.out.println(resultat.getString(2)+" "+resultat.getInt(5));
				//Possibilit� 3 : R�cuperer l'ensemble des valeurs comme chaines de caract�res
				System.out.println(resultat.getString(2)+" "+resultat.getString(5));
			}
		
			*/
			/*
			String ville="Casablanca";
			String requete1= "update city set population = "
					+ "population + 10000 where name='"+ville+"'";
			int nbre = st.executeUpdate(requete1);
			System.out.println(nbre+" lignes mises � jour");
			
			*/
			
			/*
			
			String requete = "select * from city where countrycode = 'MAR'";
			ResultSet resultat = st.executeQuery(requete);
			ResultSetMetaData metaData = resultat.getMetaData();
			for(int i=1;i<=metaData.getColumnCount();i++) {
				System.out.print(metaData.getColumnLabel(i)+"\t");
			}
			System.out.println();
			while(resultat.next()) {
				for(int i=1;i<=metaData.getColumnCount();i++) {
					System.out.print(resultat.getString(i)+"\t");
				}
				System.out.println();
			}
			*/
			
			
			/*
			
			//String requete = "select * from city where countrycode=?";
			String requete = "select * from etudiant";
			PreparedStatement pSt= connexion.prepareStatement(requete);
			//pSt.setString(1, "MAR");
			ResultSet resultat =pSt.executeQuery();
			while(resultat.next()) {
				System.out.println(resultat.getString("cin")+" "
						+resultat.getString("nom") + " " +resultat.getString("prenom"));
			}
			
			String requeteIns = "insert into etudiant values ('wa108' , 'ayman' ,'neffar' , 'azer18', 'INE1' , 'aseds') ; ";
			PreparedStatement pStins= connexion.prepareStatement(requeteIns);
			//pSt.setString(1, "MAR");
			pStins.executeUpdate();
			*/
			
			/*
			String requete = "update city set population = "
					+ "population + ? where name= ?";
			PreparedStatement pSt= connexion.prepareStatement(requete);
			pSt.setInt(1, 10000);
			pSt.setString(2, "Casablanca");
			pSt.executeUpdate();
			
			*/
				/*		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Probl�me de connexion");
		}  
		
		
		
		

		
		
		System.out.println("ok"); 
		
		*/
	}

}
