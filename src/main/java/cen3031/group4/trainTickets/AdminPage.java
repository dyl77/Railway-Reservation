package cen3031.group4.trainTickets;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AdminPage {
	GridPane layoutAdmin;
	Stage screen;
	Scene mainScene;
	String username = "1";
	String password = "1";
	String checkUser, checkPw;
	String id;
	String cancel;
	Button Login;
	final TextField txtUserName;
	final PasswordField pf;
	final Label Message;
	
	AdminPage(GridPane layoutAdmin, Stage screen, Scene mainScene){
		this.layoutAdmin = layoutAdmin;
		this.screen = screen;
		this.mainScene = mainScene;
		txtUserName = new TextField();
		pf = new PasswordField();
		Message = new Label();
	}

	public void createAdminPage() {
		createTitle();
		
		createLogin();
		
		createButtons();
		
	}
	
	public void createTitle() {
		var adminLabel = new Label("Welcome to Administration");
	       adminLabel.setId("title");
	       adminLabel.setAlignment(Pos.CENTER);
	       layoutAdmin.add(adminLabel, 0, 0);
	}
	
	@SuppressWarnings("unchecked")
	public void createLogin() {
		Label Username = new Label("Username= 1");
        
        Label Password = new Label("Password= 1");
        
        Login = new Button("Login");
        Login.setId("btnLogin");
        
        layoutAdmin.add(Username, 0, 1);
        layoutAdmin.add(txtUserName, 1, 1);
        layoutAdmin.add(Password, 0, 2);
        layoutAdmin.add(pf, 1, 2);
        layoutAdmin.add(Login, 2, 2);
        layoutAdmin.add(Message, 1, 3);
        
        GridPane Adminpane = new GridPane();
        Adminpane.setId("pane");
        Adminpane.setPadding(new Insets(5,5,5,5));
        Adminpane.setVgap(20);
        Adminpane.setHgap(20);
        Adminpane.setAlignment(Pos.CENTER);
        
	}
	
	
	public void createButtons() {	
		
		Button returnToMainButton2 = new Button("Go back to main screen");
        returnToMainButton2.setOnAction(e -> screen.setScene(mainScene));
        layoutAdmin.add(returnToMainButton2, 6, 6);
        
	}

	public void makeBackOfficePage(Scene backOfficeScene, Stage screen, Scene mainScene2) {
		BackOffice backOffice = new BackOffice(backOfficeScene, screen, Login, checkUser, checkPw, txtUserName, 
				pf, username, password, Message, mainScene);
		backOffice.createBackOffice();
	}
}
