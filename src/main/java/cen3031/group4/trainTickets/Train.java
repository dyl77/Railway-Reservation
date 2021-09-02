package cen3031.group4.trainTickets;

public class Train
{

	private String destination, starting;
	private int distance;
	private int trainID,express, breakfast, lunch, dinner, capacity, days, softSeat, hardSeat, hardSleeper, softSleeper;
	private double price;
	
	
	Train()
	{
		this.trainID=-999;
		this.starting = "NULL";
		this.destination = "NULL";
		this.express= 0;
		this.distance=-1;
		this.capacity = 0;
		this.days = 0;
		this.softSeat = 0;
		this.hardSeat = 0;
		this.softSleeper = 0;
		this.hardSleeper = 0;
		this.breakfast= 0;
		this.lunch= 0;
		this.dinner= 0;
		this.price=0.00;
	}
	
	public String toString() {
		
		return this.getID() + " " + this.getFrom() + " " + this.getTo() + " " + this.getIsExpress() + " " + this.getDistance() + " " + this.getCapacity() + " " +
		this.getDays() + " " + this.getSoftSeat() + " " + this.getHardSeat() + " " + this.getSoftSleeper() + " " + this.getHardSleeper() + " " + this.getBreakfast() + " " +
		this.getLunch() + " " + this.getDinner() + " " + this.getPrice();
	}
	
	//-------------Getters----------------
	
	public int getDistance()
	{
		return this.distance;
	}
	
	public int getID()
	{
		return this.trainID;
	}
	
	public int getIsExpress()
	{
		return this.express;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public int getDinner() {
		return dinner;
	}	
	
	public int getLunch() {
		return lunch;
	}
	
	public int getBreakfast() {
		return breakfast;
	}	
	
	public String getTo() {
		return destination;
	}	
	
	public String getFrom() {
		return starting;
	}
	
	public int getCapacity() {
		return capacity;
	}	
	
	public int getDays() {
		return days;
	}	
	
	public int getHardSeat() {
		return hardSeat;
	}
	
	public int getSoftSleeper() {
		return softSleeper;
	}
	
	public int getHardSleeper() {
		return hardSleeper;
	}
	
	public int getSoftSeat() {
		return softSeat;
	}
	
	//-------------Setters----------------
	
	public void setDistance(int route)
	{
		this.distance=route;
	}
	
	public void setID(int trainID)
	{
		this.trainID=trainID;
	}
	
	public void setIsExpress(int express)
	{
		this.express=express;
	}

	public void setDinner(int dinner) {
		this.dinner = dinner;
	}

	public void setLunch(int lunch) {
		this.lunch = lunch;
	}

	public void setBreakfast(int breakfast) {
		this.breakfast = breakfast;
	}

	public void setTo(String destination) {
		this.destination = destination;
	}

	public void setFrom(String start) {
		this.starting = start;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public void setHardSeat(int hardSeat) {
		this.hardSeat = hardSeat;
	}

	public void setSoftSleeper(int softSleeper) {
		this.softSleeper = softSleeper;
	}

	public void setHardSleeper(int hardSleeper) {
		this.hardSleeper = hardSleeper;
	}

	public void setSoftSeat(int softSeat) {
		this.softSeat = softSeat;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
