package cen3031.group4.trainTickets;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class TrainDB {
	
	public void createTables() throws SQLException, FileNotFoundException, ClassNotFoundException {
		ArrayList<Train> trainList = TrainReadin.ReadIn();
	
		SimpleDataSource.init();
		Connection conn = SimpleDataSource.getConnection();
		
	    Statement statement = SimpleDataSource.getConnection().createStatement();
	    System.out.println("Checking database for table");
	    
	    DatabaseMetaData databaseMetadata = SimpleDataSource.getConnection().getMetaData();
	    ResultSet resultSet = databaseMetadata.getTables(null, "App" , "Trains", null);
	    
	    //Checks if table exists in database, if not creates table from train objects pulled from inventory.csv
	    if (!resultSet.next()) {
	       System.out.println("TABLE ALREADY EXISTS");
	    } else {
		    	try {
				System.out.println("Creating Trains Table... \n");
				
				String sqlCreate = "CREATE TABLE " + "Trains"
				        + "  (trainID           INTEGER,"
				        + "   starting          CHAR(50),"
				        + "   destination       CHAR(50),"
				        + "   express           INTEGER,"
				        + "   distance          INTEGER,"
				        + "   capacity          INTEGER,"
				        + "   days              INTEGER,"
				        + "   softSeat          INTEGER,"
				        + "   hardSeat          INTEGER,"
				        + "   softSleeper       INTEGER,"
				        + "   hardSleeper       INTEGER,"
				        + "   breakfast         INTEGER,"
				        + "   lunch             INTEGER,"
				        + "   dinner            INTEGER,"
				        + "   price             DOUBLE)";
				
				statement.execute(sqlCreate);
				
				String sqlInsert = "INSERT INTO Trains VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
				for(int i = 0; i < trainList.size(); ++i) {
						pstmt.setInt(1,trainList.get(i).getID());
						pstmt.setString(2, trainList.get(i).getFrom());
						pstmt.setString(3, trainList.get(i).getTo());
						pstmt.setInt(4, trainList.get(i).getIsExpress());
						pstmt.setInt(5, trainList.get(i).getDistance());
						pstmt.setInt(6, trainList.get(i).getCapacity());
						pstmt.setInt(7, trainList.get(i).getDays());
						pstmt.setInt(8, trainList.get(i).getSoftSeat());
						pstmt.setInt(9, trainList.get(i).getHardSeat());
						pstmt.setInt(10, trainList.get(i).getSoftSleeper());
						pstmt.setInt(11, trainList.get(i).getHardSleeper());
						pstmt.setInt(12, trainList.get(i).getBreakfast());
						pstmt.setInt(13, trainList.get(i).getLunch());
						pstmt.setInt(14, trainList.get(i).getDinner());
						pstmt.setDouble(15, 0.0);
						pstmt.executeUpdate();
				}
				
			}finally{
				conn.close();
			}
	    	
	    	}
	    
		}
	
	//Used for debugging only
	public void debugQuery(String sqlQuery) {
		Connection conn;
		try {
			conn = SimpleDataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sqlQuery);
			ResultSetMetaData rsm = result.getMetaData();
			int cols = rsm.getColumnCount();
			while (result.next()) {
				for (int i = 1; i <= cols; i++) {
					switch (i){
						case 1:
							System.out.print("TrainID: ");
							break;
						case 2:
							System.out.print("From: ");
							break;
						case 3:
							System.out.print("Destination: ");
							break;
						case 4:
							System.out.print("Is Express: ");
							break;
						case 5:
							System.out.print("Distance: ");
							break;
						case 6:
							System.out.print("Capacity: ");
							break;
						case 7:
							System.out.print("Days: ");
							break;
						case 8:
							System.out.print("Soft Seat: ");
							break;
						case 9:
							System.out.print("Hard Seat: ");
							break;
						case 10:
							System.out.print("Soft Sleeper: ");
							break;
						case 11:
							System.out.print("Hard Sleeper: ");
							break;
						case 12:
							System.out.print("Breakfast: ");
							break;
						case 13:
							System.out.print("Lunch: ");
							break;
						case 14:
							System.out.print("Dinner: ");
							break;
						case 15:
							System.out.print("Price: ");
							break;
						
					}
		            System.out.print(result.getString(i) + " ");
				}
		        System.out.println("");
			}
			
			System.out.println("======================================================================================================================================================" +
					"=====================================================================================================================");
		} catch (SQLException e) {
			System.out.println("Unable to query for such information.");
		}
		
	}
	
	public void printTable() {
		Connection conn;
		String sqlQuery = ("SELECT * FROM Trains");
		try {
			conn = SimpleDataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sqlQuery);
			ResultSetMetaData rsm = result.getMetaData();
			int cols = rsm.getColumnCount();
			while (result.next()) {
				for (int i = 1; i <= cols; i++) {
					switch (i){
						case 1:
							System.out.print("TrainID: ");
							break;
						case 2:
							System.out.print("From: ");
							break;
						case 3:
							System.out.print("Destination: ");
							break;
						case 4:
							System.out.print("Is Express: ");
							break;
						case 5:
							System.out.print("Distance: ");
							break;
						case 6:
							System.out.print("Capacity: ");
							break;
						case 7:
							System.out.print("Days: ");
							break;
						case 8:
							System.out.print("Soft Seat: ");
							break;
						case 9:
							System.out.print("Hard Seat: ");
							break;
						case 10:
							System.out.print("Soft Sleeper: ");
							break;
						case 11:
							System.out.print("Hard Sleeper: ");
							break;
						case 12:
							System.out.print("Breakfast: ");
							break;
						case 13:
							System.out.print("Lunch: ");
							break;
						case 14:
							System.out.print("Dinner: ");
							break;
						case 15:
							System.out.print("Price: ");
							break;
						
					}
		            System.out.print(result.getString(i) + " ");
				}
		        System.out.println("");
			}
			
			System.out.println("======================================================================================================================================================" +
			"=====================================================================================================================");
		} catch (SQLException e) {
			System.out.println("Unable to query for such information.");
		}
	}
	
	//Returns train object list based on specific query entered
	public ArrayList<Train> selectQuery(String sqlQuery) {
		Connection conn;
		ArrayList<Train> trainList = new ArrayList<Train>();
		
		try {
			conn = SimpleDataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sqlQuery);
			ResultSetMetaData rsm = result.getMetaData();
			int cols = rsm.getColumnCount();
			
			
			while (result.next()) {
				Train foundTrain = new Train();
				for (int i = 1; i <= cols; i++) {
					switch (i){
						case 1:
							foundTrain.setID(result.getInt(i));
							break;
						case 2:
							foundTrain.setFrom(result.getString(i));
							break;
						case 3:
							foundTrain.setTo(result.getString(i));
							break;
						case 4:
							foundTrain.setIsExpress(result.getInt(i));
							break;
						case 5:
							foundTrain.setDistance(result.getInt(i));
							break;
						case 6:
							foundTrain.setCapacity(result.getInt(i));
							break;
						case 7:
							foundTrain.setDays(result.getInt(i));
							break;
						case 8:
							foundTrain.setSoftSeat(result.getInt(i));
							break;
						case 9:
							foundTrain.setHardSeat(result.getInt(i));
							break;
						case 10:
							foundTrain.setSoftSleeper(result.getInt(i));
							break;
						case 11:
							foundTrain.setHardSleeper(result.getInt(i));
							break;
						case 12:
							foundTrain.setBreakfast(result.getInt(i));
							break;
						case 13:
							foundTrain.setLunch(result.getInt(i));
							break;
						case 14:
							foundTrain.setDinner(result.getInt(i));
							break;
						case 15:
							foundTrain.setPrice(result.getDouble(i));
							break;
						
					}
				}
		       trainList.add(foundTrain); 
			}
		} catch (SQLException e) {
			System.out.println("Unable to query for such information.");
		}
		
		return trainList;
	}
	
	//For use in back office to update train information
	public void updateQuery(Train editTrain, ArrayList<String> updatedInfo) {
		Connection conn;
		
		String sqlUpdate = "UPDATE Trains SET capacity = " + Integer.parseInt(updatedInfo.get(3)) +
				"         , breakfast = " + Integer.parseInt(updatedInfo.get(4)) +
				"         , lunch = " + Integer.parseInt(updatedInfo.get(5)) +
				"         , dinner = " + Integer.parseInt(updatedInfo.get(6)) +
				"         , hardSeat = " + Integer.parseInt(updatedInfo.get(7)) +
				"         , softSeat = " + Integer.parseInt(updatedInfo.get(8)) +
				"         , hardSleeper = " + Integer.parseInt(updatedInfo.get(9)) +
				"         , softSleeper = " + Integer.parseInt(updatedInfo.get(10)) +
				"        WHERE trainID=" + editTrain.getID();
		
		try {
			conn = SimpleDataSource.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlUpdate);
			
		}catch(SQLException e) {
			//e.printStackTrace();
			System.out.println("Unable to update train information");
		}
		
	}
}

