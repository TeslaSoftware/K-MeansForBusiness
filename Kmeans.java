import java.util.ArrayList;
import java.util.HashMap;

/*
 * Kmeans.java
 * This class will perform step-by-step k-means algorithm and evaluate results as well as provide the centroids
 * 
 */

public class Kmeans {
	Centroid[] centroids;
	int k;
	ArrayList<DataPoint> data;
	
	public Kmeans(int clusters) {
		k = clusters;
		centroids = new Centroid[k];
	}
	
	//This method will get datapoints from dbLoader
	public void loadData(HashMap<String, Boolean> states){
		dbLoader db = new dbLoader();
		data = db.getDataPoints(states);	
	}
	
	//This method runs k-means algorithm and returns the results in form of an array of Strings which includes centroids location
	public String[] runKmeans(){
		
	}

	//This method returns string of statistics, which includes centroids IV/EV values and their evaluation
	public String[] getStatistics(){
		
	}
	
	


}
