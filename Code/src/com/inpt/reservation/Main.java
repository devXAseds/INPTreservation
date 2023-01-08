package com.inpt.reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.TestConnexion;
import javafx.scene.layout.Priority;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args) ; 

	}
	
	 /**
	 *
	 */
	 String userLog ="" ; 
	 ObservableList<Reservation> reservs ; 
	 
	  public boolean checkTime(String t) {
		  	if(t.length()!=8) return false ; 
			if(Integer.parseInt(t.substring(0, 2))>24 || Integer.parseInt(t.substring(0, 2))<0) return false  ; 
			if(Integer.parseInt(t.substring(0, 2))==24 &&(Integer.parseInt(t.substring(3, 5))!=0 ||Integer.parseInt(t.substring(6, 8))!=0 ) ) return false ; 
			if(t.substring(2,3).equals(":") ==false )  return false ; 

			if(Integer.parseInt(t.substring(3, 5))>59 || Integer.parseInt(t.substring(3, 5))<0) return false  ; 

			
	
			if(t.substring(5,6).equals(":") ==false) return false ; 

			if(Integer.parseInt(t.substring(6, 8))>59 || Integer.parseInt(t.substring(6, 8))<0) return false  ;

			return true ; 
		 }
	  
	  ListView<Reservation> listRes ;
	  
	@Override
	    public void start(Stage primaryStage) {
		 
		 	inptRes res = new inptRes() ;  
		 	
		 	
	        primaryStage.setTitle("INPT Réservations");
	        
	        /******************* Connecting Scene ********/
	        
	        StackPane p = new StackPane() ; 
	        VBox v = new VBox() ; 
	        ProgressIndicator PI=new ProgressIndicator(); 
	        Label con = new Label("Connecting ...") ; 
	        v.getChildren().add(PI) ; 
	        v.getChildren().add(con) ; 
	        p.getChildren().add(v) ; 
	        v.setAlignment(Pos.CENTER);
	        Scene conBar = new Scene(p , 450 ,450) ; 
	
	        p.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); "); 
	        
	        
	        //*****************Creation de la premiere scene !!!
	        VBox vbS1 = new VBox() ; 
	        
	        GridPane rootS1 = new GridPane();
	        
	        Label errors = new Label() ; 
	        
	        try {
				TestConnexion.getData(res) ;
			} catch (Exception e) {
				errors.setText("une erreur de connexion a survenu ! ");
				
			} 
	        
	        errors.setTextFill(Color.RED);
	        vbS1.getChildren().add(errors); 
	        vbS1.getChildren().add(rootS1); 
	           
	        
	        Label userl = new Label("username : ") ; 
	        Label passl = new Label("password : ") ; 
	        TextField user = new TextField() ; 
	        user.setPromptText("CIN");
	        PasswordField pass = new PasswordField() ; 
	        pass.setPromptText("password");
	        Button connecter = new Button("Se connecter") ;
	        
	        HBox hb = new HBox() ; 
	        hb.getChildren().add(connecter) ; 
	        //hb.getChildren().add(creer) ; 
	        
	        
	        
	        GridPane.setHalignment(userl, HPos.LEFT);
	        GridPane.setHalignment(passl, HPos.LEFT);
	        
	        GridPane.setHalignment(user, HPos.RIGHT);
	        GridPane.setHalignment(pass, HPos.RIGHT);
	        rootS1.setHgap(25);
	        rootS1.setVgap(15);
	        rootS1.add(userl, 1, 1);
	        rootS1.add(passl, 1, 2);
	        rootS1.add(user, 2, 1);
	        rootS1.add(pass, 2, 2);
	        //ObservableList<String> choices = new ObservableList<String>() ; 
	        ObservableList<String> choices = FXCollections.observableArrayList();
	        choices.add("ASEDS") ; 
	        choices.add("AMOA") ; 
	        choices.add("DATA") ; 
	        choices.add("SMART") ; 
	        choices.add("CLOUD") ; 
	       
	        
	        hb.setPadding(new Insets(15, 12, 15, 12));
	        hb.setSpacing(10);
	        vbS1.setSpacing(15);
	        hb.setAlignment(Pos.CENTER);
	        vbS1.getChildren().add(hb) ;  
	        vbS1.setPadding(new Insets(15, 12, 15, 12));
	        vbS1.setAlignment(Pos.CENTER);
	        rootS1.setAlignment(Pos.CENTER);
	        Scene s = new Scene(vbS1 , 450, 450) ; 
	        
	        
	        //**********************Creation de la deuxieme scene !!!
	        
	        BorderPane root2 = new BorderPane() ; 
	        
	        Label lb = new Label("Admin Control Panel") ;
	        lb.setPadding(new Insets(0, 0, 50, 0 ));
	        lb.setFont(new Font("Impact", 30));
	        Button ajEtu = new Button("Ajouter un étudiant") ;
	        Button ajSal = new Button("Ajouter une salle") ; 
	        
	        
	        Button ajTer = new Button("Ajouter un terrain") ; 
	        
	        Button logoutAdmin = new Button("Se deconnecter") ; 
	        logoutAdmin.setOnAction((e)->{
	        	
	        	primaryStage.setScene(conBar); 
	        	con.setText("Deconnecting...");
    			PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> primaryStage.setScene(s) 
    			);
    			visiblePause.play();
	        }
	        );
	        ajEtu.setPrefWidth(200);
	        ajEtu.setFont(new Font("Helvetica" , 20));
	        ajSal.setPrefWidth(200);
	        ajSal.setFont(new Font("Helvetica" , 20));
	        ajTer.setPrefWidth(200);
	        ajTer.setFont(new Font("Helvetica" , 20));
	        VBox gr = new VBox() ; 
	        gr.getChildren().addAll(ajEtu , ajSal , ajTer) ;         
	        //lb.setAlignment(Pos.CENTER);
	
	   
	        root2.setTop(lb) ; 
	        root2.setCenter(gr) ; 
	        root2.setBottom(logoutAdmin);
	        gr.setSpacing(15);
	        root2.setAlignment(lb, Pos.CENTER);
	        root2.setAlignment(logoutAdmin, Pos.CENTER);
	        root2.setPadding(new Insets(25, 0, 25, 0 ));
	       
	        gr.setAlignment(Pos.BASELINE_CENTER);
	       
	        
	        Scene s2 = new Scene(root2 , 450 , 450 ) ; 
	        
	        //Scene d'ajout d un etudiant 
	        
	  
	        VBox vbEtud = new VBox() ; 
	        Label etudlab = new Label() ; 
	        GridPane rootEtu = new GridPane();
	        vbEtud.getChildren().add(etudlab);
	        Label cin = new Label("Cin d'étudiant: ") ; 
	   
	        Label nom = new Label("Nom:") ; 
	        Label prenom = new Label("Prenom:") ; 
	        Label fil = new Label("Filière:") ;
	        Label pass2 = new Label("password:") ; 
	        Label niv = new Label("niveau:") ; 
	        TextField cinf = new TextField() ; 
	        TextField nomf = new TextField() ; 
	        res.ajFiliere("aseds");
	        res.ajFiliere("amoa");
	        res.ajFiliere("data");
	        res.ajFiliere("smart");
	        res.ajFiliere("sesnum");
	        res.ajFiliere("iccn");
	        res.ajFiliere("cloud");
	        

	        TextField prenomf = new TextField() ; 
	        TextField passf = new TextField() ; 
	        ObservableList<String> filieres = FXCollections.observableArrayList(res.getFilieres());
	        ObservableList<String> nivs = FXCollections.observableArrayList();
	        nivs.add("INE1"); 
	        nivs.add("INE2");
	        nivs.add("INE3");
	        ChoiceBox<String> filbox = new ChoiceBox<String>(filieres) ; 
	        ChoiceBox<String> nivbox = new ChoiceBox<String>(nivs) ; 
	        filbox.setValue(filieres.get(1));
	        nivbox.setValue(nivs.get(0));

	        Button ajout = new Button("Ajouter étudiant") ; 
	        Button backet = new Button("Retour") ; 
	        
	        backet.setOnAction(e-> {
	        	primaryStage.setScene(s2);
	        	nomf.clear();
	        	prenomf.clear();
	        	cinf.clear();
	        	passf.clear();
	        });
	        
	        ajout.setOnAction(e->{
	        	if(nomf.getText()=="" ||prenomf.getText()=="" ||  passf.getText()=="" || cinf.getText()=="") {
	        		etudlab.setText("vous devez remplir tous les champs ! ") ; 
		        	etudlab.setTextFill(Color.RED) ;
	        	}else {
	        		try {
	        			
	        		
	        		res.ajEtud(nomf.getText(), prenomf.getText(), cinf.getText(), passf.getText(), filbox.getValue().toString(), nivbox.getValue().toString());
		        	etudlab.setText("l'etudiant a été bien ajouté ! ") ; 
		        	etudlab.setTextFill(Color.BLACK) ;
	        		}
	        		catch(Exception ex ) {
	        			etudlab.setText(ex.getMessage()) ; 
			        	etudlab.setTextFill(Color.RED) ;
			        	
	        		}
	        	}
	        	 
	        	etudlab.setVisible(true);
	        	PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> etudlab.setVisible(false)
    			);
    			visiblePause.play();
	        });
	        
	        
	        
	        HBox hb2 = new HBox() ; 
	        HBox hb3 = new HBox() ; 
	        
	        hb2.getChildren().add(ajout) ; 
	        hb2.getChildren().add(backet) ; 
	   
	        
	        vbEtud.getChildren().add(rootEtu) ; 
	        vbEtud.getChildren().add(hb2) ; 
	        vbEtud.setAlignment(Pos.CENTER);
	        
	        GridPane.setHalignment(cin, HPos.LEFT);
	        
	        GridPane.setHalignment(nom, HPos.LEFT);
	        GridPane.setHalignment(prenom, HPos.LEFT);
	        GridPane.setHalignment(fil, HPos.LEFT);
	        GridPane.setHalignment(niv, HPos.LEFT);
	        GridPane.setHalignment(pass2, HPos.LEFT);
	        
	        GridPane.setHalignment(cinf, HPos.RIGHT);
	 
	        GridPane.setHalignment(nomf, HPos.RIGHT) ; 
	        GridPane.setHalignment(prenomf, HPos.RIGHT);
	        GridPane.setHalignment(filbox, HPos.RIGHT);
	        GridPane.setHalignment(nivbox, HPos.RIGHT);
	        GridPane.setHalignment(passf, HPos.RIGHT) ; 
	        
	        rootEtu.setHgap(25);
	        rootEtu.setVgap(15);
	        
	        rootEtu.add(cin ,1,1);

	        rootEtu.add(nom, 1, 2);
	        rootEtu.add(prenom, 1, 3);
	        rootEtu.add(fil, 1, 4);
	        rootEtu.add(niv, 1, 5);
	        rootEtu.add(pass2, 1, 6);
	       
	        
	        
	        rootEtu.add(cinf, 2, 1);
	
	        rootEtu.add(nomf, 2, 2);
	        rootEtu.add(prenomf, 2, 3);
	        rootEtu.add(filbox, 2, 4);
	        rootEtu.add(nivbox, 2, 5);
	        rootEtu.add(passf, 2, 6);
	      
	        rootEtu.setAlignment(Pos.BASELINE_CENTER);
	        hb2.setAlignment(Pos.CENTER);
	        //vb1.setPadding(new Insets(15, 12,100, 12));
	        vbEtud.setSpacing(25) ; 
	        hb2.setSpacing(10);
	        
	        

	        
	        Scene ScEtud = new Scene(vbEtud , 450 , 450 ); 
	        
	        
	        
	        //vbS1.setStyle("-fx-background-color : linear-gradient(#BFDBF7 , #E1E5F2 ); ");
	        vbS1.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); "); 
	        root2.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); "); 
	        vbEtud.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); "); 
	        ajEtu.setOnAction(e-> primaryStage.setScene(ScEtud) );
	        
	        
	        // Scene pour l ajout des salles
	        
	        Label idSalle = new Label("Id salle : "); 
	        Label FiliereSal = new Label("Filiere concernee : ") ; 
	        Label sallab = new Label() ;
	        sallab.setFont(new Font(15));
	        
	        TextField idSalf = new TextField() ; 
	        TextField FiliereSalf = new TextField() ; 
	        
	        Button ajSalle = new Button("Ajouter") ; 
	        Button backSal = new Button("Retour") ; 
	        ajSalle.setOnAction(e-> {
	        	if(idSalf.getText()=="" || FiliereSalf.getText()=="") {
	        		sallab.setText("vous devez remplir tous les champs ! ");
		        	sallab.setTextFill(Color.RED) ; 
	        	}else {
	        		try {
	        			res.ajSalle(idSalf.getText(), FiliereSalf.getText());
			        	sallab.setText("la salle a été bien ajouté ! ");
			        	sallab.setTextFill(Color.BLACK) ; 
	        		}catch(Exception ex )
	        		{
	        			sallab.setText(ex.getMessage());
			        	sallab.setTextFill(Color.RED) ;
			        	
	        		}
	        		
	        	}
	        
	        	sallab.setVisible(true);
	        	PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> sallab.setVisible(false)
    			);
    			visiblePause.play();
	        	
	        });
	        
	        backSal.setOnAction(e-> {
	        	primaryStage.setScene(s2);
	        	idSalf.clear(); 
	        	FiliereSalf.clear(); 
	        	
	        });
	        
	        VBox vbSalle = new VBox(); 
	      
	        
	        GridPane gdSalle = new GridPane()  ; 
	        vbSalle.getChildren().add(sallab) ; 
	        vbSalle.getChildren().add(gdSalle) ; 
	        vbSalle.setSpacing(15);
	        vbSalle.setPadding(new Insets(25,0,0,0));
	        vbSalle.setAlignment(Pos.BASELINE_CENTER);
	        
	        GridPane.setHalignment(idSalle, HPos.LEFT);
	        GridPane.setHalignment(FiliereSal, HPos.LEFT);
	        GridPane.setHalignment(idSalf, HPos.RIGHT);
	        GridPane.setHalignment(FiliereSalf, HPos.RIGHT);
	        
	        gdSalle.setStyle("-fx-font-family: Helvetica ;" );
	        
	        gdSalle.add(idSalle, 1,1);
	        gdSalle.add(FiliereSal, 1,2);
	        gdSalle.add(ajSalle, 1,3);
	        gdSalle.add(idSalf, 2,1);
	        gdSalle.add(FiliereSalf, 2,2);
	        gdSalle.add(backSal, 2,3);
	        vbSalle.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); ") ; 
	        
	        gdSalle.setVgap(15);
	        gdSalle.setHgap(10);
	        gdSalle.setAlignment(Pos.CENTER);
	        Scene SalleS = new Scene(vbSalle , 450 , 450 ) ; 
	        
	        ajSal.setOnAction(e-> primaryStage.setScene(SalleS));
	        
	        
	        // Scene pour l ajout des Terrains
	        
	         
	        
	        
	        Label idTer = new Label("Id terrain : "); 
	        Label sport = new Label("Sport : ") ; 
	        Label terlab = new  Label() ; 
	        
	        TextField idTerf = new TextField() ; 
	        TextField sportf = new TextField() ; 
	     
	        
	        Button ajTerrain = new Button("Ajouter") ; 
	        Button backTer = new Button("Retour") ; 
	        
	        ajTerrain.setOnAction(e->{
	        	
	        	if(idTer.getText()=="" || sportf.getText()=="") {
	        		terlab.setText("vous devez remplir tous les champs !") ; 
		        	terlab.setTextFill(Color.RED) ;
	        	}else {
	        		
	        		try {
						res.ajTer(idTerf.getText(), sportf.getText());
						terlab.setText("le terrain a été bien ajouté !") ; 
			        	terlab.setTextFill(Color.BLACK) ;
					} catch (Exception e1) {
						terlab.setText(e1.getMessage()) ; 
			        	terlab.setTextFill(Color.RED) ;
			        	
					}
		        	
		        	
	        	}
	        	
	        	terlab.setVisible(true);
	        	PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> terlab.setVisible(false)
    			);
    			visiblePause.play();
	        	
	        });
	        
	        backTer.setOnAction(e-> {
	        	primaryStage.setScene(s2) ;
	        	idTerf.clear() ; 
	        	sportf.clear() ; 
	        	
	        	});
	        
	        VBox vbTer = new VBox() ;
	        vbTer.getChildren().add(terlab); 
	        GridPane gdTer = new GridPane()  ; 
	        vbTer.getChildren().add(gdTer) ; 
	        vbTer.setPadding(new Insets(25,0,0,0));
	        vbTer.setSpacing(15);
	        vbTer.setAlignment(Pos.BASELINE_CENTER);
	        GridPane.setHalignment(idTer, HPos.LEFT);
	        GridPane.setHalignment(sport, HPos.LEFT);
	 
	        GridPane.setHalignment(idTerf, HPos.RIGHT);
	        GridPane.setHalignment(sportf, HPos.RIGHT) ; 
	  
	        
	        gdTer.setStyle("-fx-font-family: Helvetica ;" );
	        
	        gdTer.add(idTer, 1,1);
	        gdTer.add(sport, 1,2);
	      
	        gdTer.add(ajTerrain, 1,4);
	        gdTer.add(idTerf, 2,1);
	        gdTer.add(sportf, 2,2);
	        
	        gdTer.add(backTer, 2,4);
	        
	        vbTer.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); ") ; 
	        
	        gdTer.setVgap(15);
	        gdTer.setHgap(10);
	        gdTer.setAlignment(Pos.CENTER);
	        Scene TerrainS = new Scene(vbTer , 450 , 450 ) ; 
	        
	        ajTer.setOnAction(e-> primaryStage.setScene(TerrainS));
	        
	        /*************Scene pour utilisateur etudiant *******************/
	        
	        VBox vEtud = new VBox() ; 
	        Label namelab = new Label() ; 
	        namelab.setFont(new Font(20) );
	        namelab.setTextFill(Color.BLUE);
	        Button btnAjRes = new Button("Ajouter une reservation") ; 
	        Button btnAfRes = new Button("Afficher l'historique") ; 
	        Button btnSaveHist = new Button("Sauvegarder l'historique") ; 
	        Button logoutEtud = new Button("Se deconnecter") ; 
	        
	       
	        vEtud.getChildren().add(btnAjRes);
	        vEtud.getChildren().add(btnAfRes);
	        vEtud.getChildren().add(btnSaveHist);
	        
	        btnSaveHist.setPrefWidth(200);
	        btnSaveHist.setFont(new Font( 15));
	        
	        VBox vbSave = new VBox() ; 
	        vbSave.setAlignment(Pos.CENTER);
	        vbSave.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); ") ; 
	        Label savelab = new Label() ; 
	        TextField savefi = new TextField() ; 
	        
	        savefi.setMaxWidth(200);
	        savefi.setPromptText("nom de fichier sans extention");
	        Button sauvgader = new Button("sauvgader") ;
	        Button backSav = new Button("Retour") ;
	        vbSave.getChildren().addAll(savelab , savefi , sauvgader ,backSav) ; 
	        vbSave.setSpacing(15);
	        Scene sSave = new Scene(vbSave,450 ,450) ;
	        btnSaveHist.setOnAction(e->primaryStage.setScene(sSave));
	        sauvgader.setOnAction(e->{
	        	if(savefi.getText()=="") {
	        		savelab.setTextFill(Color.RED);
					savelab.setText("entrer un nom de fichier ! ");
	        	}else {
	        		try {
						res.saveData(savefi.getText(),userLog);
						savelab.setTextFill(Color.BLACK);
						savelab.setText("Votre historique a été bien sauvegardé !");
					} catch (IOException e1) {
						savelab.setTextFill(Color.RED);
						savelab.setText("une erreur est survenu ! ");
						
	    				
					} 
	        	}
	        	
	        	savelab.setVisible(true); 
    			PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> savelab.setVisible(false)
    			);
    			visiblePause.play();
	        });
	        
	        
	        btnAjRes.setPrefWidth(200);
	        btnAjRes.setFont(new Font( 15));
	        
	        
	        btnAfRes.setPrefWidth(200);
	        btnAfRes.setFont(new Font( 15));
	        vEtud.setSpacing(15);
	        
	        
	        BorderPane bEtud = new BorderPane() ; 
	        bEtud.setTop(namelab);
	        bEtud.setCenter(vEtud);
	        bEtud.setBottom(logoutEtud);
	        logoutEtud.setOnAction((e)->{
	        	primaryStage.setScene(conBar); 
    			con.setText("Deconnecting...");
    			PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> primaryStage.setScene(s)
    			);
    			visiblePause.play();
	        });
	        
	        vEtud.setAlignment(Pos.CENTER);
	   
	      
	        bEtud.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); ") ;
	        Scene sEtud = new Scene(bEtud , 450 , 450 );
	        bEtud.setPadding(new Insets(25,0,25,0)) ; 
	        backSav.setOnAction(e-> primaryStage.setScene(sEtud));
	        
	        BorderPane.setAlignment(logoutEtud, Pos.CENTER);
	        BorderPane.setAlignment(namelab, Pos.CENTER);
	        
	        /********************Scene l ajout d une resevation ***************/ 
	        
	        
	    
	        GridPane resPane = new GridPane() ; 
	        
	        
	        ObservableList<String> locals = FXCollections.observableArrayList();
	        locals.add("Terrain de sport") ; 
	        locals.add("Salle de revision") ; 
	       
	        Label idlocall = new Label("id local :")  ;
	        Label typel = new Label("typde de local :") ; 
	        Label durl = new Label("Duree(min) :") ; 
	        Label datel = new Label("Date :") ; 
	        
	        Label timel = new Label("Time :") ; 
	        
	        TextField idlocal = new TextField() ; 
	        ChoiceBox type = new ChoiceBox(locals) ; 
	        type.setValue(locals.get(0));
	        TextField dur = new TextField() ;
	        DatePicker date = new DatePicker(LocalDate.now());
	        TextField time = new TextField() ;
	        time.setPromptText("HH:MM:SS");
	        Button ajtRes = new Button("Ajouter") ; 
	        Button backRes = new Button("Retour");
	        GridPane.setHalignment(idlocall , HPos.LEFT);
	        GridPane.setHalignment(typel , HPos.LEFT);
	        GridPane.setHalignment(durl , HPos.LEFT);
	        GridPane.setHalignment(datel , HPos.LEFT);
	        GridPane.setHalignment(timel , HPos.LEFT);
	        
	        GridPane.setHalignment(idlocal , HPos.RIGHT);
	        GridPane.setHalignment(type , HPos.RIGHT);
	        GridPane.setHalignment(dur , HPos.RIGHT);
	        GridPane.setHalignment(date , HPos.RIGHT);
	        GridPane.setHalignment(time , HPos.RIGHT);
	        
	        Label ajResl = new Label() ; 
	        
	        VBox vRes = new VBox() ; 
	        vRes.getChildren().add(ajResl) ; 
	        vRes.getChildren().add(resPane) ; 
	        vRes.setStyle("-fx-background-color : linear-gradient( #84D2F6 ,#91E5F6  ); ") ;
	        vRes.setAlignment(Pos.CENTER);
	        ajResl.setTextFill(Color.RED);
	 
	        resPane.add(idlocall, 1, 2);
	        resPane.add(typel, 1, 3);
	        resPane.add(durl, 1, 4);
	        resPane.add(datel, 1, 5);
	        resPane.add(timel, 1, 6);
	        resPane.add(ajtRes, 1, 9);
	        resPane.add(idlocal, 2, 2);
	        resPane.add(type, 2, 3);
	        resPane.add(dur, 2, 4);
	        resPane.add(date, 2, 5);
	        resPane.add(time, 2, 6);
	        resPane.add(backRes, 2, 9);
	        
	        resPane.setHgap(10);
	        resPane.setVgap(10);
	        resPane.setAlignment(Pos.CENTER);
	        
	        Scene sRes = new Scene(vRes , 450 , 450 ) ;
	        
	        ajtRes.setOnAction((e)->{
	        	if(idlocall.getText()=="" || durl.getText()=="" || time.getText()=="") {
	        		ajResl.setText("vous devez remplir tous les champs !");
	        		
	        	}
	        	else {
	        		if(checkTime(time.getText())==false) {
	        			ajResl.setText("Veuillez respecter la forme de time indiquée !");
	        		}
	        		else {
	        			if(res.checkSal(idlocal.getText())==false && res.checkTer(idlocal.getText())==false ) {
	        				ajResl.setTextFill(Color.RED);
	        				ajResl.setText("Ce local est introuvable !");
	    	        		
	        			}
	        			else {
	        				
	        			try {
	        				res.ajRes(java.sql.Date.valueOf(date.getValue()), Integer.parseInt(dur.getText()), idlocal.getText(), userLog, type.getValue().toString(), time.getText());
	        				ajResl.setTextFill(Color.BLACK);
	        				ajResl.setText("Votre reservation a été bien ajoutée !");
	        			}catch(Exception ex ) {
	        				ajResl.setTextFill(Color.RED);
	        				ajResl.setText(ex.getMessage());
	        				
	    	        		
	        			}
	        				}
	        			
	        		}
	        		
	        		
	        			
	        		
	        	}
	        	
	        	ajResl.setVisible(true); 
    			PauseTransition visiblePause = new PauseTransition(
    			        Duration.seconds(2)
    			);
    			visiblePause.setOnFinished(
    			        event -> ajResl.setVisible(false)
    			);
    			visiblePause.play();
	        });
	        btnAjRes.setOnAction((e)-> primaryStage.setScene(sRes));
	        
	        backRes.setOnAction((e)->{
	        	primaryStage.setScene(sEtud); 
	        	dur.clear();
	        	time.clear();
	        	idlocal.clear();
	        });
	        
	        
	        /********************** Afficher les reservations ************************/ 
	       
	        
	        
	       BorderPane brHist = new BorderPane() ; 
	        
	        /* 
	        ObservableList<Reservation> reservs = FXCollections.observableArrayList(res.getReservations());
	        
	        
	        ListView<Reservation> listRes = new ListView<Reservation>(reservs) ;
	        stHist.getChildren().add(listRes);
	       */ 
	        
	
	        
	        Scene sHist = new Scene(brHist , 450 , 450) ; 
	        
	        Button backHist = new Button("Retour") ;
	        
	        Button sortbut = new Button("Trier") ;
	        
	         listRes = new ListView<Reservation>() ;
	         
	         ObservableList<String> sorts = FXCollections.observableArrayList();
	         sorts.add("id") ; 
	         sorts.add("date") ; 
	         sorts.add("duree") ; 
	         HBox hbHist = new HBox() ; 
	         HBox sortHist = new HBox() ; 
	         Label sortlab = new Label("trié par:  ") ; 
	         sortlab.setFont(new Font(15));
	         ChoiceBox sortbox = new ChoiceBox(sorts) ;
	         sortbox.setValue(sorts.get(0));
	         hbHist.getChildren().addAll( sortlab , sortbox,sortbut ) ; 
	         
	         hbHist.setAlignment(Pos.CENTER_RIGHT);
	         HBox.setHgrow(hbHist, Priority.ALWAYS);
	         sortHist.getChildren().addAll(backHist, hbHist);
	         sortHist.setPadding(new Insets(2));
	         brHist.setCenter(listRes);
	         brHist.setBottom(sortHist);
	         
	        btnAfRes.setOnAction(e-> { 
	        	primaryStage.setScene(sHist);
	        	
	        	//listRes.getItems().clear();
	        	reservs = FXCollections.observableArrayList(res.getResEtud(userLog));
	        	listRes.setItems(reservs); 
		        
		       
		        
	        });
	        
	        sortbut.setOnAction(e->{
	        	if(sortbox.getValue().toString()=="id") {
	        		reservs.sort(new IdComparator()) ; 
	        		listRes.setItems(reservs); 
	        	}else {
	        		if(sortbox.getValue().toString()=="duree"){
	        			reservs.sort(new DureeComparator()) ; 
		        		listRes.setItems(reservs); 
		        		}else {
		        			reservs.sort(new DateComparator()) ; 
			        		listRes.setItems(reservs);
		        		}
	        	}
	        });
	        
	        backHist.setOnAction(e-> primaryStage.setScene(sEtud));
	        
	        
	        connecter.setOnAction(e -> {
	         	userLog = user.getText() ;
	        
	        	if(user.getText()=="" || pass.getText()=="") {
	        			errors.setText("vous devez entrer votre username et password ! ");
	        			errors.setVisible(true); 
	        			PauseTransition visiblePause = new PauseTransition(
	        			        Duration.seconds(2)
	        			);
	        			visiblePause.setOnFinished(
	        			        event -> errors.setVisible(false)
	        			);
	        			visiblePause.play();
	        			
	        		}
	        	else {
	        		primaryStage.setScene(conBar); 
        			con.setText("Connecting...");
        			PauseTransition visiblePause = new PauseTransition(
        			        Duration.seconds(2)
        			);
        			
        			visiblePause.play();
        			
	        		if(user.getText().equals(res.getAdmin()) && pass.getText().equals(res.getAdminPass())) {
	        		
	        			visiblePause.setOnFinished(
	        			        event -> primaryStage.setScene(s2)
	        			);
	        		}
	        		else {
	        			if(res.checkData(user.getText(), pass.getText())) {
	        			
		        			visiblePause.setOnFinished(
		        			        event -> {
		        			        	primaryStage.setScene(sEtud) ; 
		        			        	namelab.setText("Bonjour "+res.getName(userLog)) ; 
		        			        }
		        			);
		        			
	        			}
	        			else {
	        				PauseTransition vPause = new PauseTransition(
		        			        Duration.seconds(2)
		        			);
		        			vPause.setOnFinished(
		        			        event -> errors.setVisible(false)
		        			);
		        			
	        				visiblePause.setOnFinished(
	            			        event -> {
	            			        	primaryStage.setScene(s);
	            			        	errors.setText("les données que vous avez entré sont incorrects ");
	        		        			errors.setVisible(true); 
	        		        			vPause.play();
	            			        }
	            			);
	        				
	        				
	        				
	        			}
	        			
	        			
	        		}
	        		
	        		
	        		
	        	}
	        		
	        		
	        	 
	        		user.clear();
		        	pass.clear() ; 
	        			
	        
	        }) ; 
	        
	        
	      
	        
	       
	      
	        primaryStage.setScene(s);
	        primaryStage.show();
	    }
	 

}
