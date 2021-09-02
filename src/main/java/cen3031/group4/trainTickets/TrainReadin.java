package cen3031.group4.trainTickets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainReadin {
	
	static String fileDir="src/main/java/cen3031/group4/trainTickets/Inventory.csv";
	
	//Creates train objects from each line of inventory sheet. Used for initial SQL table creation
	public static ArrayList<Train> ReadIn() throws FileNotFoundException{
		
		ArrayList <Train> trains = new ArrayList<Train>();

		String stringLine;
		Scanner sc = new Scanner(new File(fileDir)); 
		while(sc.hasNextLine()) {
			stringLine = sc.nextLine();
			String[] stringArray = stringLine.split(",");
			
			Train newTrain = new Train(); 
			newTrain.setID(Integer.parseInt(stringArray[0]));
			newTrain.setFrom(stringArray[1]);
			newTrain.setTo(stringArray[2]);
			newTrain.setIsExpress(Integer.parseInt(stringArray[3]));
			newTrain.setDistance(Integer.parseInt(stringArray[4]));
			newTrain.setCapacity(Integer.parseInt(stringArray[5]));
			newTrain.setDays(Integer.parseInt(stringArray[6]));
			newTrain.setSoftSeat(Integer.parseInt(stringArray[7]));
			newTrain.setHardSeat(Integer.parseInt(stringArray[8]));
			newTrain.setSoftSleeper(Integer.parseInt(stringArray[9]));
			newTrain.setHardSleeper(Integer.parseInt(stringArray[10]));
			newTrain.setBreakfast(Integer.parseInt(stringArray[11]));
			newTrain.setLunch(Integer.parseInt(stringArray[12]));
			newTrain.setDinner(Integer.parseInt(stringArray[13]));
			trains.add(newTrain);
			
		}
 
		sc.close();  //closes the scanner  
		return trains;
		}  
}
