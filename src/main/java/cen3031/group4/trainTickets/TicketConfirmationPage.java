package cen3031.group4.trainTickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicketConfirmationPage {
	
	GridPane layoutTicket;
	Stage screen;
	Button bookTicketButton;
	TextField name;
    ComboBox startPointDropDown;
    ComboBox destinationPointDropDown;
    CheckBox breakfast;
    CheckBox lunch;
    CheckBox dinner;
    ComboBox seatDropDown;
    Scene ticketConfirmationScene;
    Scene mainScene;
    Scene ticketScene;
    GridPane layoutTicketConfirmation;
    
    Label nameConfirmation;
    Label startPointConfirmation;
    Label destinationPointConfirmation;
    Label mealConfirmation;
    Label seatConfirmation;
    Label priceConfirmation;
    Label daysToTravel;
    Label datePurchasedConfirmation;
    Label ticketConfirmationLabel;
    
    Button returnToMainButton3;
    Button returnToTicketPage;

	TicketConfirmationPage(GridPane layoutTicket, Stage screen, Button bookTicketButton, TextField name,
            ComboBox startPointDropDown, ComboBox destinationPointDropDown, CheckBox breakfast, CheckBox lunch, CheckBox dinner,
            ComboBox seatDropDown, Scene ticketConfirmationScene, Scene mainScene, Scene ticketScene){
		this.layoutTicket = layoutTicket;
		this.screen = screen;
		this.bookTicketButton = bookTicketButton;
		this.name = name;
		this.startPointDropDown = startPointDropDown;
		this.destinationPointDropDown = destinationPointDropDown;
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
		this.seatDropDown = seatDropDown;
		this.ticketConfirmationScene = ticketConfirmationScene;
		this.mainScene = mainScene;
		this.ticketScene = ticketScene;		
	}
	
	public void createTicketConfirmationPage() {
       
		createLayout();
      
		createLabels();
 
		setAlignmentForLabels();
         
		createButtons();
 
		addToLayout();
       
		bookTicketEvent();
       
    }
	
	public void createLayout() {
		layoutTicketConfirmation = new GridPane();
        
        layoutTicketConfirmation.setId("ticketpane");
        layoutTicketConfirmation.setPadding(new Insets(5, 5, 5, 5));
        layoutTicketConfirmation.setVgap(20);
        layoutTicketConfirmation.setHgap(20);
	}
	
	public void createLabels() {
		//declaring labels 
	       	ticketConfirmationLabel = new Label("Ticket Confirmation" + "\n");
	        ticketConfirmationLabel.setId("title");
	        ticketConfirmationLabel.setAlignment(Pos.CENTER);
	        GridPane.setHalignment(ticketConfirmationLabel, HPos.CENTER);
	        
	        nameConfirmation = new Label();
	        startPointConfirmation = new Label();
	        destinationPointConfirmation = new Label();
	        mealConfirmation = new Label();
	        seatConfirmation = new Label();
	        priceConfirmation = new Label();
	        daysToTravel = new Label();
	        datePurchasedConfirmation = new Label();
	}
	
	public void setAlignmentForLabels() {
		GridPane.setHalignment(nameConfirmation, HPos.LEFT);
        GridPane.setHalignment(startPointConfirmation, HPos.LEFT);
        GridPane.setHalignment(destinationPointConfirmation, HPos.LEFT);
        GridPane.setHalignment(mealConfirmation, HPos.LEFT);
        GridPane.setHalignment(seatConfirmation, HPos.LEFT);
        GridPane.setHalignment(priceConfirmation, HPos.LEFT);
        GridPane.setHalignment(daysToTravel, HPos.LEFT);
        GridPane.setHalignment(datePurchasedConfirmation, HPos.LEFT);
	}
	
	public void createButtons() {
		returnToMainButton3 = new Button("Confirm and Return to Main Screen");
        GridPane.setHalignment(returnToMainButton3, HPos.LEFT);
        returnToMainButton3.setOnAction(e -> {
        	screen.setScene(mainScene);
        	//TODO DECREMENT TRAIN CAPACITY WHEN USER RETURNS TO MAIN SCREEN
        });
        
        returnToTicketPage = new Button("Go Back to Ticket Page");
        GridPane.setHalignment(returnToTicketPage, HPos.LEFT);
        returnToTicketPage.setOnAction(e -> screen.setScene(ticketScene));
	}
	
	public void addToLayout() {
		 //adding labels and buttons to layout
        layoutTicketConfirmation.add(ticketConfirmationLabel, 0, 0);
        layoutTicketConfirmation.add(nameConfirmation, 0, 1);
        layoutTicketConfirmation.add(startPointConfirmation, 0, 2);
        layoutTicketConfirmation.add(destinationPointConfirmation, 0, 3);
        layoutTicketConfirmation.add(mealConfirmation, 0, 4);
        layoutTicketConfirmation.add(seatConfirmation, 0, 5);
        layoutTicketConfirmation.add(priceConfirmation, 0, 6);
        layoutTicketConfirmation.add(daysToTravel, 0, 7);
        layoutTicketConfirmation.add(datePurchasedConfirmation, 0, 8);
        layoutTicketConfirmation.add(returnToMainButton3, 0, 9);
        layoutTicketConfirmation.add(returnToTicketPage, 1, 9);
               
        layoutTicketConfirmation.setAlignment(Pos.CENTER);
         
        ticketConfirmationScene = new Scene(layoutTicketConfirmation, 1366, 845);
	}
	
	public void bookTicketEvent() {
		 //gets values selected, adds them to labels and displays them on the ticket confirmation page
        bookTicketButton.setOnAction(e -> {
            
            nameConfirmation.setText("Ticket confirmed for    " + name.getText().toUpperCase());
            startPointConfirmation.setText("Starting point:    " + startPointDropDown.getValue().toString().toUpperCase());
            destinationPointConfirmation.setText("Destination point:    " + destinationPointDropDown.getValue().toString().toUpperCase());
            mealConfirmation.setText("Meal options:   " );
            
            if(breakfast.isSelected()) {
                mealConfirmation.setText(mealConfirmation.getText() + " " + breakfast.getText().toUpperCase());
            }
            if (lunch.isSelected()) {
                mealConfirmation.setText(mealConfirmation.getText() + " " + lunch.getText().toUpperCase());
            }
            if(dinner.isSelected()) {
                mealConfirmation.setText(mealConfirmation.getText() + " " + dinner.getText().toUpperCase());
            }
            if(!(breakfast.isSelected()) && !(lunch.isSelected()) && !(dinner.isSelected())) {
                mealConfirmation.setText(mealConfirmation.getText() + " NONE");
            }
            
            seatConfirmation.setText("Seat type:    " + seatDropDown.getValue().toString().toUpperCase());
            double totalPrice = CalculateTicketPrice(breakfast, lunch, dinner, seatDropDown);
            priceConfirmation.setText("Price:    $" + totalPrice);
            
            daysToTravel.setText("Travel Time:    " + TicketPage.selectedTrain.getDays() + " days");
            
            DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDateTime now = LocalDateTime.now();
  
            datePurchasedConfirmation.setText("Ticket purchase date:    " + date.format(now));
            
            screen.setScene(ticketConfirmationScene);
            ticketConfirmationScene.getStylesheets().addAll(this.getClass().getResource("ticketpage.css").toExternalForm());
                
        }); 
	}
	
	 public double CalculateTicketPrice(CheckBox breakfastSelection, CheckBox lunchSelection, CheckBox dinnerSelection,
	    		ComboBox seatDropDown) {
	    	double totalPrice = 14.99; //base price
	    	double pricePerKm = 0.20;
	    	
	    	//calculate standard or express train cost
	    	if(TicketPage.selectedTrain.getIsExpress() == 1) {
	    		totalPrice += 29.99;
	    	}
	    	else {
	    		totalPrice += 19.99;
	    	}
	    	
	    	//calculate distance to travel cost
	    	totalPrice += TicketPage.selectedTrain.getDistance() * pricePerKm;
	    	
	    	//calculate meals cost
	        if(breakfastSelection.isSelected()) {
	            totalPrice += 14.50;
	        }
	        if (lunchSelection.isSelected()) {
	        	totalPrice += 18.85;
	        }
	        if(dinnerSelection.isSelected()) {
	        	totalPrice += 24.99;
	        }
	    	
	    	//calculate selected seat cost
	    	if(seatDropDown.getValue().toString() == "Hard Seat") {
	    		totalPrice += 7.99;
	    	}
	    	else if(seatDropDown.getValue().toString() == "Soft Seat") {
	    		totalPrice += 12.99;
	    	}
	    	else if(seatDropDown.getValue().toString() == "Hard Sleeper") {
	    		totalPrice += 17.99;
	    	}
	    	else {
	    		totalPrice += 22.99;
	    	}
	    	
	    	totalPrice = Math.round(totalPrice * 100.0) / 100.0;
	    	return totalPrice;
	    }
}
