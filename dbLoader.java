import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * dbLoader.java
 * This class is here to load data from database to datapoints
 * This class will provide ArrayList<DataPoint> which were read from the file * 
 */

public class dbLoader {

	public dbLoader(){
		
	}
	
	
	/*	This method returns ArrayList<DataPoint> which contains information read from the database of census
	 * 	@param	stateSelected	HashMap of selected states to consider for example key value pairs can look as follows: <"NY", True>, <"NJ", False>
	 * 	@param	columnNum		integer representing targeted age group. Accepted values 1-18, Value 0 indicates error
	 */
	public ArrayList<DataPoint> loadDataPoints(HashMap<String, Boolean> statesSelected, int columnNum){
		//go through database row by row, each row represents zip code
		//if row state value is matching requested state then consider this string, else go to next record
		//if you consider this string since state is matching requested state then go to requested age group and get its population and divide its population by 100
		//then the number you will get is the number of how many Point objects with this zip code we need to create
		//for each point use the current longitude and latitude. Remember to add 180 to each longitude since US is on the eastern hemisphere and those values are negative.
		//store this Point in the ArrayList of Points
		//after going through whole file return ArrayList of Points

		ArrayList<DataPoint> result = new ArrayList<DataPoint>();
		String zip, state, geography;
		double latitude, longitude;
		int ageGroupPopulation;
		Scanner fileScan = loadCSV("db","CensusDB_ACS_14_5YR.csv");
		fileScan.useDelimiter(","); // setting the delimiter
		fileScan.nextLine();// skipping the first line since it is the header
		while (fileScan.hasNextLine()){
			zip = fileScan.next();
			state = fileScan.next();
			geography = fileScan.next();
			latitude = Double.parseDouble(fileScan.next());
			longitude = Double.parseDouble(fileScan.next())+180;
			for (int i = 0; i < columnNum; i++) {
				fileScan.next();
			}
			ageGroupPopulation = Integer.parseInt(fileScan.next());
			if(statesSelected.containsKey(state) && statesSelected.get(state)){
				for (int i = 0; i < ageGroupPopulation/100; i++) {
					result.add(new DataPoint(zip,latitude,longitude));
				}
			}
			fileScan.nextLine();
		}

		return result;
	}
	private Scanner loadCSV(String subDirectory, String fileName) {
		String path = System.getProperty("user.dir")+ File.separator+subDirectory+File.separator;
		Scanner input = null;
		try {
			 input = new Scanner(new File(path+fileName));
		} catch (FileNotFoundException e) {
			System.err.println("The file is not found!");
		}
		return input;
	}
}