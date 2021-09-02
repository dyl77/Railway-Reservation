package cen3031.group4.trainTickets;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class TicketPage {
	GridPane layoutTicket;
	Stage screen;
	Scene mainScene;
	static Train selectedTrain;
	ArrayList<String> destinationPoints;
	ArrayList<String> seatOptions;
	TrainDB db;
	
	Button bookTicketButton;
	TextField name;
    ComboBox startPointDD;
    ComboBox destinationPointDD;
    CheckBox breakfastCheckBox;
    CheckBox lunchCheckBox;
    CheckBox dinnerCheckBox;
    ComboBox seatDD;
	
	TicketPage(GridPane layoutTicket, Stage screen, Scene mainScene, Train selectedTrain, ArrayList<String> destinationPoints, ArrayList<String> seatOptions,  TrainDB db){
		this.layoutTicket = layoutTicket;
		this.screen = screen;
		this.mainScene = mainScene;
		this.selectedTrain = selectedTrain;
		this.destinationPoints = destinationPoints;
		this.seatOptions = seatOptions;
		this.db = db;
	}
	
	public void updateDropDown(ComboBox<String> destinationPointDropDown) {
    	destinationPointDropDown.setItems(FXCollections.observableArrayList(destinationPoints));
    }
    

    public void updateSeatOptions(ComboBox<String> seatDropDown, ArrayList<String> seatOptions) {
    	seatDropDown.setItems(FXCollections.observableArrayList(seatOptions));
    }

	
	public void createTicketPage() {
	        
		   createTitle();
	       
	       createPassengerName();

	       createStartingPoint();

	       createDestinationPoint();

	       createStartPointEvent();

	       createMealOptions();
	  
	       createSeat();
	       
	       createDestinationPointEvent();

	       createButtons();

	}
	 
	 public void createTitle() {
		 //title
	       var ticketLabel = new Label("Welcome to Ticket Booking");
	       ticketLabel.setId("title");
	       ticketLabel.setAlignment(Pos.CENTER);
	       layoutTicket.add(ticketLabel, 0, 0, 3, 1);
	 }
	 
	 public void createPassengerName() {
	    	//text box for passenger to enter their name
		       var nameLabel = new Label("Enter name:");
		       name = new TextField();
		       layoutTicket.add(nameLabel, 0, 1);
		       layoutTicket.add(name, 1, 1);
	    }
	 
	 public void createStartingPoint() {
		//drop down for starting point
	       var startPointLabel = new Label("Select Starting Point:");

	       String startPoints[] = {"Please Choose" ,"Wagsville", "Gujranwala", "Flipperton", "New Wingsford", "Chesterdale",
	            	"Waddlesborough", "Bread Ponds City"};
	       @SuppressWarnings("unchecked")
		   ComboBox startPointDropDown = new ComboBox(FXCollections.observableArrayList(startPoints));
	       startPointDD = startPointDropDown;
	       startPointDD.setPromptText("Select Start");
	       startPointDD.getSelectionModel().selectFirst();
	       layoutTicket.add(startPointLabel, 0, 2);
	       layoutTicket.add(startPointDD, 0, 3); 
	 }
	 
	@SuppressWarnings("unchecked")
	
	public void createDestinationPoint() {
		//drop down for destination point
	       var destinationPointLabel = new Label("Select Destination Point:");
	       
	       
		   ComboBox destinationPointDropDown = new ComboBox(FXCollections.observableArrayList(destinationPoints));
	       destinationPointDD = destinationPointDropDown;
	       destinationPointDD.setItems(FXCollections.observableArrayList(destinationPoints));
	       destinationPointDD.setPromptText("Select Destination");
	       destinationPointDD.getSelectionModel().selectFirst();
	   
	       layoutTicket.add(destinationPointLabel, 1, 2);
	       layoutTicket.add(destinationPointDD, 1, 3);
	 }
	 
	@SuppressWarnings("unchecked")
	
	public void createStartPointEvent() {
		 startPointDD.setOnAction(new EventHandler<ActionEvent>() {
	    	   
	    	   @Override public void handle(ActionEvent e) {
	    		   destinationPoints.clear();
		    	   String startingSelection = startPointDD.getSelectionModel().getSelectedItem().toString();
		    	   ArrayList<Train> destinationTrains = db.selectQuery("SELECT * FROM Trains WHERE starting='" + startingSelection + "'");
		    	   for(int i = 0; i < destinationTrains.size(); ++i) {
		    		   	if(destinationTrains.get(i).getIsExpress() == 1) {
		    		   		destinationPoints.add(destinationTrains.get(i).getID() + " " + destinationTrains.get(i).getTo() + "(Express)");
		    		   	}else {
		    		   		destinationPoints.add(destinationTrains.get(i).getID() + " " + destinationTrains.get(i).getTo() + "(Standard)");
		    		   		
		    		   	}
				    	 
				      }
		    	   updateDropDown(destinationPointDD);
	    	   }
	       });
	 }
	 
	 public void createMealOptions() {
		//meal options check box
	       var mealsLabel = new Label("Select Meal Options:");
	       layoutTicket.add(mealsLabel, 0, 4);
	       
	       breakfastCheckBox = new CheckBox("Breakfast");
	       lunchCheckBox = new CheckBox("Lunch");
	       dinnerCheckBox = new CheckBox("Dinner");
	       
	       breakfastCheckBox.setSelected(false);
	       lunchCheckBox.setSelected(false);
	       dinnerCheckBox.setSelected(false);
	       
	       layoutTicket.add(breakfastCheckBox, 0, 5);
	       layoutTicket.add(lunchCheckBox, 0, 6);
	       layoutTicket.add(dinnerCheckBox, 0, 7);
	 }
	 
	@SuppressWarnings("unchecked")
	
	public void createSeat() {
		 var seatLabel = new Label("Select Seat:");
	       
	       ComboBox<String> seatDropDown = new ComboBox<String>(FXCollections.observableArrayList());
	       seatDD = seatDropDown;
	       seatDD.setItems(FXCollections.observableArrayList(seatOptions));
	       seatDD.setPromptText("Seat Type");
	       seatDD.getSelectionModel().selectFirst();
	       layoutTicket.add(seatLabel, 1, 4);
	       layoutTicket.add(seatDD, 1, 5);
	 }
	 
	 @SuppressWarnings("unchecked")
	 
	public void createDestinationPointEvent() {
		 destinationPointDD.setOnAction(new EventHandler<ActionEvent>() {
	    	   
	    	   @Override public void handle(ActionEvent e) {
	    		   seatOptions.clear();
	    		   updateSeatOptions(seatDD, seatOptions);
	    		   
	    		   //Error handling for when a passenger changes their startpoint the second time
	    		   String destinationSelection = null;
	    		   int selectedTrainID;
		    	   try {
		    		   destinationSelection = destinationPointDD.getSelectionModel().getSelectedItem().toString();
		    		   selectedTrainID = 0;
		    	   }catch(NullPointerException a) {
		    		   System.out.println("Handling");
		    	   }
		    	   
		    	   //Resets check boxes in case the selected meals aren't offered on new selection
		    	   breakfastCheckBox.setSelected(false);
		    	   lunchCheckBox.setSelected(false);
		    	   dinnerCheckBox.setSelected(false);
		    	   
		    	   if(destinationSelection != null) {
		    		   selectedTrainID = Integer.parseInt(destinationSelection.substring(0,3));
		    	   }else {
		    		   selectedTrainID = 0;
		    	   }
		    	   
		    	   ArrayList<Train> selectedTrainList = db.selectQuery("SELECT * FROM Trains WHERE trainID=" + selectedTrainID);
		    	   
		    	   if(!selectedTrainList.isEmpty()) {
		    		   selectedTrain = selectedTrainList.get(0);
		    	   }
		    	   
		    	   if(selectedTrain.getBreakfast() == 0) {
		    		   breakfastCheckBox.setDisable(true);
		    	   }else {
		    		   breakfastCheckBox.setDisable(false);
		    	   }
		    	   if(selectedTrain.getLunch() == 0) {
		    		   lunchCheckBox.setDisable(true);
		    	   }else {
		    		   lunchCheckBox.setDisable(false);
		    	   }
		    	   if(selectedTrain.getDinner() == 0) {
		    		   dinnerCheckBox.setDisable(true);
		    	   }else {
		    		   dinnerCheckBox.setDisable(false);
		    	   }
		    	   
		    	   if(selectedTrain.getSoftSeat() != 0) {
		    		   seatOptions.add("Soft Seat");
		    	   }
		    	   
		    	   if(selectedTrain.getHardSeat() != 0) {
		    		   seatOptions.add("Hard Seat");
		    	   }
		    	   
		    	   if(selectedTrain.getSoftSleeper() != 0) {
		    		   seatOptions.add("Soft Sleeper");
		    	   }
		    	   
		    	   if(selectedTrain.getHardSleeper() != 0) {
		    		   seatOptions.add("Hard Sleeper");
		    	   }
		    	   updateSeatOptions(seatDD, seatOptions);
	    	   }
	       });
	 }
	 
	 public void createButtons() {
		// return to main button
	       Button returnToMainButton = new Button("Go back to Main Screen");
	       returnToMainButton.setOnAction(e -> screen.setScene(mainScene));
	       layoutTicket.add(returnToMainButton, 0, 8);
	       
	       // book ticket button
	       bookTicketButton = new Button("Book Ticket");
	       layoutTicket.add(bookTicketButton, 1, 8);
	 }
	 
	 
	 public void makeTicketConfirmationPage(Scene ticketConfirmationScene, Scene mainScene, Scene ticketScene) {
		 TicketConfirmationPage ticketConfirmationPage = new TicketConfirmationPage(layoutTicket, screen, bookTicketButton, name,
	               startPointDD, destinationPointDD, breakfastCheckBox, lunchCheckBox, dinnerCheckBox,
	               seatDD, ticketConfirmationScene, mainScene, ticketScene);
	       ticketConfirmationPage.createTicketConfirmationPage();
	 }
}
