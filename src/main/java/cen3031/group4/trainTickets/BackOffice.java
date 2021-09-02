package cen3031.group4.trainTickets;

import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class BackOffice {

	GridPane backPane;
	Stage screen;
	GridPane TrainInfoPane;
	String id;
	String cancel;
	Scene trainInfoScene, backOfficeScene ,mainScene;
	Button Login;
	String checkUser, checkPw;
	final TextField txtUserName;
	final PasswordField pf;
	final Label Message;
	String username;
	String password;
	TextArea outputarea;
	Button returnToMainButton3;
	ArrayList<Train> destinationTrains;
	ArrayList<Button> goToTrain;
	VBox vboxForButtons;
	
	
	;
	
	
	BackOffice(Scene backOfficeScene, Stage screen, Button Login, String checkUser, String checkPw, final TextField txtUserName, 
			final PasswordField pf, String username, String password, final Label Message, Scene mainScene) {
		this.screen = screen;
		this.backOfficeScene = backOfficeScene;
		this.Login = Login;
		this.checkPw = checkPw;
		this.checkUser = checkUser;
		this.txtUserName = txtUserName;
		this.pf = pf;
		this.username = username;
		this.password = password;
		this.Message = Message;
		this.mainScene = mainScene;
		
	}

	public void createBackOffice() {
		backPane = new GridPane();
    	backPane.setId("backpane");
    	backPane.setPadding(new Insets(5,5,5,5));
    	backPane.setVgap(20);
    	backPane.setHgap(20);
    	backPane.setAlignment(Pos.CENTER);

        TrainInfoPane = new GridPane();
        TrainInfoPane.setId("trainpane");
        TrainInfoPane.setPadding(new Insets(5,5,5,5));
        TrainInfoPane.setVgap(20);
        TrainInfoPane.setHgap(20);
        TrainInfoPane.setAlignment(Pos.CENTER);
        
		createTrainDisplay();
		loginEvent();
		
	}
	
	@SuppressWarnings("unchecked")
	public void loginEvent() {
		
		backOfficeScene = new Scene(backPane,1366, 845);
	    
	     
		Login.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				checkUser = txtUserName.getText().toString();
				checkPw = pf.getText().toString();
             if(checkUser.equals(username) && checkPw.equals(password)){
              screen.setScene(backOfficeScene);
              backOfficeScene.getStylesheets().addAll(this.getClass().getResource("backOffice.css").toExternalForm());
             }
             else{
              Message.setText("Either your password or username is wrong");
              Message.setTextFill(Color.RED);
             }
             txtUserName.setText("");
             pf.setText("");
				
			}
            });
		
		 
	        
	}
	
	public void createTrainDisplay() {
		outputarea = new TextArea();
	     
        returnToMainButton3 = new Button("Go back to main screen");
        
        destinationTrains = Pages.db.selectQuery("SELECT * FROM Trains"); 
        
        vboxForButtons = new VBox();
        
        vboxForButtons.setAlignment(Pos.CENTER_RIGHT);
        vboxForButtons.setSpacing(4);
        outputarea.setEditable(false);
        
        goToTrain = new ArrayList<>();
        
        for(int i=0;i<destinationTrains.size();i++)
        {
  
       	 
       	 outputarea.appendText("Train ID: "+Integer.toString(destinationTrains.get(i).getID())+"\n--------------\n");
       	 goToTrain.add(new Button("Edit Train "+ String.valueOf(101+i)));
       	 goToTrain.get(i).setId(Integer.toString(destinationTrains.get(i).getID()));
       	 vboxForButtons.getChildren().addAll(goToTrain.get(i));
       	 outputarea.appendText("Starting: "+ destinationTrains.get(i).getFrom()+"\n");
       	 outputarea.appendText("Destination: "+destinationTrains.get(i).getTo()+"\n");
        
       	 if(destinationTrains.get(i).getIsExpress()==1)
       	 {
       		 outputarea.appendText("Is Express: True\n");
       	 }
       	 else
       	 {
       		 outputarea.appendText("Is Express: False\n");
       	 }
       
       	 outputarea.appendText("Distance: "+destinationTrains.get(i).getDistance()+" km\n");
       	 outputarea.appendText("Capacity: "+destinationTrains.get(i).getCapacity()+"\n");
       	 outputarea.appendText("Number of Days: "+destinationTrains.get(i).getDays()+"\n========================================\n");
        
       	 
       	 goToTrain.get(i).setOnAction(e ->{
       		 id =((Node) e.getSource()).getId();
       		 int trainId = 0;
       		 cancel=id;
       		 createInfoPage(id,trainInfoScene,TrainInfoPane,trainId,screen);
       	 });
       	 
       	returnToMainButton3.setOnAction(e->{
    		screen.setScene(mainScene);
        });

       
        }
        
        backPane.add(outputarea, 0, 1);
        backPane.add(vboxForButtons, 1, 1);
        backPane.add(returnToMainButton3, 0, 2);
        
	}
	
	public void createInfoPage(String id,Scene trainInfoScene,GridPane TrainInfoPane, int trainId,Stage screen)
    {
        trainId = Integer.parseInt(id) - 101;
        trainInfoScene=new Scene(TrainInfoPane,1365, 845);
        trainInfoScene(TrainInfoPane,screen,trainId);
        screen.setScene(trainInfoScene);
        trainInfoScene.getStylesheets().addAll(this.getClass().getResource("adminpage.css").toExternalForm());
    }
	
	public void trainInfoScene(GridPane TrainInfoPane, Stage screen,int trainId)
    {
    	
    	ArrayList<Train> destinationTrains = Pages.db.selectQuery("SELECT * FROM Trains");
    	
    	Button returnToAdminPage = new Button("Go back to Train Selection");
    	 
    	 
    	Button submitChanges = new Button("Submit Changes");
    	
    	Button cancelChanges = new Button("Reset Changes");
    	 
    	 
    	 ArrayList<TextField> trainInfo = new ArrayList<TextField>();
         
    	 ArrayList<Label> trainLabels= new ArrayList<Label>();
    	
    
    	trainLabels.add(new Label("Train ID: "));
    	TextField thisTrainId = new TextField(Integer.toString(destinationTrains.get(trainId).getID()));
    	thisTrainId.setEditable(false);
    	thisTrainId.setDisable(true);
    	trainInfo.add(thisTrainId);
    	
    	trainLabels.add(new Label("Starting: "));
    	TextField thisStart = new TextField((destinationTrains.get(trainId).getFrom()));
    	thisStart.setEditable(false);
    	thisStart.setDisable(true);
    	trainInfo.add(thisStart);
    	
    	trainLabels.add(new Label("Destination: "));
    	TextField thisDestination = new TextField((destinationTrains.get(trainId).getTo()));
    	thisDestination.setEditable(false);
    	thisDestination.setDisable(true);
    	trainInfo.add(thisDestination);
    	
    	trainLabels.add(new Label("Capacity: "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getCapacity())));
    	
    	trainLabels.add(new Label("Breakfast offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getBreakfast())));
    	
    	trainLabels.add(new Label("Lunch offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getLunch())));
    	
    	trainLabels.add(new Label("Dinner offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getDinner())));
    	
    	trainLabels.add(new Label("Hard Seats offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getHardSeat())));
    	
    	trainLabels.add(new Label("Soft Seats offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getSoftSeat())));
    	
    	trainLabels.add(new Label("Hard Sleepers offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getHardSleeper())));
    	
    	trainLabels.add(new Label("Soft Sleepers offered? (0/1): "));
    	trainInfo.add(new TextField(Integer.toString(destinationTrains.get(trainId).getSoftSleeper())));
    
    	
    		

    	for(int i=0;i<trainInfo.size();i++)
    	{
    		TrainInfoPane.add(trainLabels.get(i), 1, i);
    		TrainInfoPane.add(trainInfo.get(i), 2, i);
    	}
    	
    	
    	
    	TrainInfoPane.add(submitChanges, 2, 11);
    	TrainInfoPane.add(cancelChanges, 1, 11);
    	
    	
    	
    	submitChanges.setOnAction(e->{
    		
    		ArrayList<String> updatedInfo = new ArrayList<String>();
    		
    		for(int i=0;i<trainInfo.size();i++)
    		{
    			updatedInfo.add(trainInfo.get(i).getText());
    		}
    		
    		Pages.db.updateQuery(destinationTrains.get(trainId), updatedInfo);
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.getDialogPane().getStylesheets().addAll(this.getClass().getResource("alert.css").toExternalForm());
    		alert.setTitle("Train Change Confirmation");
    		alert.setHeaderText("Train: " + destinationTrains.get(trainId).getID() + "'s information has been updated");
    		thisTrainId.clear();
    		thisStart.clear();
    		thisDestination.clear();
    		trainLabels.clear();
    		alert.setContentText("Would you like to return to main menu?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    		    Parent root = TrainInfoPane.getScene().getRoot();
        		TrainInfoPane.getScene().setRoot(new Region());
        		screen.setScene(mainScene);
    		} else {
    			Parent root = TrainInfoPane.getScene().getRoot();
        		TrainInfoPane.getScene().setRoot(new Region());
    		    screen.setScene(backOfficeScene);
    		}
    	});
    	
    	cancelChanges.setOnAction( e->{
    		System.out.println("Reset the scene");
    		thisTrainId.clear();
    		thisStart.clear();
    		thisDestination.clear();
    		trainLabels.clear();
    		Parent root = TrainInfoPane.getScene().getRoot();
    		TrainInfoPane.getScene().setRoot(new Region());
    		screen.setScene(backOfficeScene);
    		createInfoPage(cancel,trainInfoScene,TrainInfoPane,trainId,screen);
    		
    	});
    	
    	
    	

    	TrainInfoPane.add(returnToAdminPage, 3, 11);
    	returnToAdminPage.setOnAction(e -> {
    		thisTrainId.clear();
    		thisStart.clear();
    		thisDestination.clear();
    		trainLabels.clear();
    		Parent root = TrainInfoPane.getScene().getRoot();
    		TrainInfoPane.getScene().setRoot(new Region());
    		screen.setScene(backOfficeScene);
    	});
    	
    	returnToMainButton3.setOnAction(e->{
    		thisTrainId.clear();
    		thisStart.clear();
    		thisDestination.clear();
    		trainLabels.clear();
    		screen.setScene(mainScene);
        });
    	
    	
    }
	
}
