package cen3031.group4.trainTickets;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Pages {

	Stage screen;
	Stage window;
	Scene mainScene, ticketScene, adminScene, ticketConfirmationScene, backOfficeScene;
	static TrainDB db;
	Train selectedTrain;
	ArrayList<String> destinationPoints;
	ArrayList<String> seatOptions;
	TicketPage ticketpage;
	AdminPage adminPage;
	BackOffice backOffice;
	
	Pages(Stage screen, TrainDB db, Scene mainScene, Stage window){
		this.screen = screen;
		Pages.db = db;
		this.mainScene = mainScene;
		this.window = window;
		db = new TrainDB();
		destinationPoints = new ArrayList<String>();
		seatOptions = new ArrayList<String>();
		ticketpage = null;
	}
	
	
	
	public void mainPage() {
    	/* ------------- main page ------------- */
        var welcomeLabel = new Label("Welcome to Railway Reservation:");
        var welcomeLabel2 = new Label("A Train Ticketing System");
        
        //main screen buttons
        Button bookTicketButton = new Button("Book a Ticket Now");
        bookTicketButton.setOnAction(e -> screen.setScene(ticketScene));
        
        Button adminButton = new Button("Sign in as an Administrator");
        adminButton.setOnAction(e -> screen.setScene(adminScene)); //for now
        
        Button exitButton = new Button("Exit Program");
        exitButton.setOnAction(e -> window.close());
        
        //main screen layout
        VBox layoutMain = new VBox(10);
        layoutMain.setId("mainPane");
        layoutMain.getChildren()
            .addAll(welcomeLabel, welcomeLabel2, bookTicketButton, adminButton, exitButton);
        layoutMain.setAlignment(Pos.CENTER);
        
        //set main scene layout, size, and css style
        mainScene = new Scene(layoutMain, 1365, 845);
        mainScene.getStylesheets().addAll(this.getClass().getResource("mainmenu.css").toExternalForm());
    }
    
    public void ticketPage() {
    	 /* ------------- ticket page ------------- */
        GridPane layoutTicket = new GridPane();
        layoutTicket.setId("ticketpane");
        layoutTicket.setPadding(new Insets(5, 5, 5, 5));
        layoutTicket.setVgap(20);
        layoutTicket.setHgap(20);
        layoutTicket.setAlignment(Pos.CENTER);
        
        ticketpage = new TicketPage(layoutTicket, screen, mainScene, selectedTrain, destinationPoints, seatOptions, db);
        ticketpage.createTicketPage();
        
        ticketScene = new Scene(layoutTicket, 1366, 845);
        ticketScene.getStylesheets().addAll(this.getClass().getResource("ticketpage.css").toExternalForm());
    }
    
    public void ticketConfirmationPage() {
    	ticketpage.makeTicketConfirmationPage(ticketConfirmationScene, mainScene, ticketScene);
    }
    
    public void adminPage() {
    	/* ------------- Administration page ------------- */
        //var adminLabel = new Label("Welcome to Administration page");
        
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10,50,50,50));
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5,5,5,5));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setId("adminpane");
        
        //backOfficePage();
        
        //adminPage = new AdminPage(gridPane, screen, mainScene, backOfficeScene);
        adminPage = new AdminPage(gridPane, screen, mainScene);
        adminPage.createAdminPage();
        
        
        adminScene = new Scene(gridPane, 1366, 845);
        adminScene.getStylesheets().addAll(this.getClass().getResource("adminpage.css").toExternalForm());
    }
    
    
    public void backOfficePage() {
    	
    	adminPage.makeBackOfficePage(backOfficeScene, screen, mainScene);
    	//adminPage.lol();
    	
    	/*GridPane backPane = new GridPane();
    	backPane.setId("backpane");
    	backPane.setPadding(new Insets(5,5,5,5));
    	backPane.setVgap(20);
    	backPane.setHgap(20);
    	backPane.setAlignment(Pos.CENTER);

        GridPane TrainInfoPane = new GridPane();
        TrainInfoPane.setId("trainpane");
        TrainInfoPane.setPadding(new Insets(5,5,5,5));
        TrainInfoPane.setVgap(20);
        TrainInfoPane.setHgap(20);
        TrainInfoPane.setAlignment(Pos.CENTER);
        
        backOffice = new BackOffice(backPane,screen,TrainInfoPane);
        backOffice.createBackOffice();
        
        backOfficeScene = new Scene(backPane,1366, 845);
        //this.backOfficeScene = new Scene(backPane,1366, 845);
        backOfficeScene.getStylesheets().addAll(this.getClass().getResource("adminpage.css").toExternalForm());
        */
        
        /*backOffice = new BackOffice(backPane,screen,TrainInfoPane);
        backOffice.createBackOffice(); */
    }
    
    
    public void displayMainScene() {
		// display main scene 
        window.setScene(mainScene);
        window.show();
	}
}
