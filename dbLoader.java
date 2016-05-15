import java.util.ArrayList;

/*
 * dbLoader.java
 * This class is here to load data from database to datapoints
 * This class will provide ArrayList<DataPoint> which were read from the file * 
 */

public class dbLoader {

	public dbLoader(){
		
	}
	
	//This method returns ArrayList<DataPoint> which contains information read from the database of census
	public ArrayList<DataPoint> getDataPoints(String state){
		//go through database and take each zipcode and divide its population by 100
		//then the number you will get is the number of how many points with this zip code we need to create
	}
}
