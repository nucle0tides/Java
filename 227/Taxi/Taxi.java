/* 
  A simple model of a Taxi done for a CS 227 mini assignment. Full points received. 
*/ 

package mini1;

public class Taxi {
	
	//Instance Variables
	private double baseFare; 
	private double perMileRate; 
	private double totalMiles; 
	private double meterTotal; 
	private double totalCash; 
	private double currentRate; 
	private double averageIncome; 
	
	//Constructor 
	public Taxi(double givenBaseFare, double givenPerMileRate) {
		baseFare = givenBaseFare; 
		perMileRate = givenPerMileRate; 
		//initialize the amount of driven miles to 0
		totalMiles = 0;
		//initalize amount of money on the meter to 0
		meterTotal = 0; 
		//initialize amount of case earned to 0
		totalCash = 0; 
		//initialize the amount of miles per drive to 0 
		currentRate = 0; 
		averageIncome = 0; 
	}


	//Methods
	public void drive(double miles){
		//driven miles initalized at 0, when drive() is called, it's 0 + given miles. or x + given miles when called. 
		totalMiles = totalMiles + miles; 
		meterTotal = meterTotal + currentRate * miles; 
	 
	}
	
	public void endRide(){
		totalCash = totalCash + meterTotal; 
		
		meterTotal = 0;
		currentRate = 0;
	}
	
	public double getAverageIncomePerMile(){
		return averageIncome = totalCash / totalMiles; 
	}
	
	public double getCurrentRate(){
		//should initally be 0 and update to baseFare once startRide() is called. 
		return currentRate; 
	}
	
	public double getMeter(){
		return meterTotal; 
	}
	
	public double getTotalCash(){
		return totalCash; 
	}
	
	public double getTotalMiles(){
		return totalMiles;  
	}
	
	public void startRide(){
		meterTotal = baseFare;
		currentRate = perMileRate;
	}
}
