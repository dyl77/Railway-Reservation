package cen3031.group4.trainTickets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    
    static TrainDB db = new TrainDB();
    Stage window;
    Scene mainScene;
    
    public static void main(String[] args) {
        
        try{
            db.createTables();
            db.printTable();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        launch(args);
    }
    
    
    @Override
    public void start(Stage screen) throws Exception {
 
        window = screen;
        screen.setTitle("Railway Reservation");
        screen.getIcons().add(new Image(this.getClass().getResourceAsStream("trainIcon.png")));
        
        Pages pages = new Pages(screen, db, mainScene, window);
        // main page
        pages.mainPage();

        // ticket page
        pages.ticketPage();
        
        //ticket confirmation page
        pages.ticketConfirmationPage();
        
        
       
        // admin page
        pages.adminPage();
   
        pages.backOfficePage();
        
        // display main scene
        pages.displayMainScene();
        
    }
   
}
