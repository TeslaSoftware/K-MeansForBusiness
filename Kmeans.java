import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
 * Kmeans.java
 * This class will perform step-by-step k-means algorithm and evaluate results as well as provide the centroids
 * 
 */

public class Kmeans {
	Centroid[] centroids;
	int k;
	ArrayList<DataPoint> data;
	boolean convergance = false;
	
	public Kmeans(int clusters) {
		k = clusters;
		centroids = new Centroid[k];
		for(int i=0; i <k; i++) centroids[i] =  new Centroid(); //initialize centroids
		data = new ArrayList<DataPoint>();
	}
	
	//This method will get datapoints from dbLoader
	public void loadData(HashMap<String, Boolean> states, int targetGroup){
		dbLoader db = new dbLoader();
		//data = db.loadDataPoints(states, targetGroup);	
		
		//testPoints - temporary solution until db loader works
		data.add(new DataPoint("12345", 3,5));
		data.add(new DataPoint("12345", 1,5));
		data.add(new DataPoint("12345", 1,4));
		data.add(new DataPoint("12345", 5,1));
		data.add(new DataPoint("12345", 5,2));
		data.add(new DataPoint("12345", 5,3));
	}
	
	//This method runs k-means algorithm and returns the results in form of an array of Strings which includes centroids location
	public void runKmeans(){
		//1. pick centroids at random
		selectRanCentroids();
		//set labels to each point
		intializeDataLabels();
		do{
			//2. cluster data using Euclidean distance
			calculateDistances();
			//3. recalculate centroids
			recalculateCentorids();
			
		}while(!convergance);
		//4. repeat steps 2 & 3 until convergance
			
	}
	
	//This method returns centroids calculated
	public Centroid[] getCentroids(){ return centroids; }

	//This method returns string of statistics, which includes centroids IV/EV values and their evaluation
	public String[] getStatistics(){
		String [] result = new String[k+3];
		//result[0] stores value IV
		double IV = getIV();
		result[0] = "Intercluster Variability (IV) = " + IV;
		//result[1] stores value EV
		double EV = getEV();
		result[1] = "Extracluster Variability (EV) = " + EV;
		//result[2] stores value IV/EV
		if(EV !=0) result[2] = "IV/EV = " + (IV/EV); 
		result[1] = "Extracluster Variability (EV) = " + getEV();
		//result[i+3] stores the value of centroid i
				
		//go through whole list of centroids to get their values
		for(int i =0; i < k; i++){
			result[i+3] = "Centroid #"+ i + " hax value x=" + centroids[i].getX() + " and value y=" + centroids[i].getY() + ". It will reach approximately " + (countCentroidMembers(i)*100) + " people.";
		}
		
		
		return result;
	}
	
	//This methods selects centroids at random
	private void selectRanCentroids(){
		double minX, minY, maxX, maxY;
		//set values of min and max to first element in the list
		minX = data.get(0).getX();
		maxX = minX;
		minY = data.get(0).getY();
		maxY = minY;
		
		//go throught the list of points and find min and max for x and y, skip first one since was used to initialize minX, maxX, minY and maxY
		for(int i =1; i < data.size(); i++){
			DataPoint currentPoint = data.get(i);
			if(currentPoint.getX() < minX) minX = currentPoint.getX();  //check if current x is the smallest
			else if(currentPoint.getX() > maxX) maxX = currentPoint.getX(); //if not then maybe it is the largest
			if(currentPoint.getY() < minY) minY = currentPoint.getX(); //check if current y is the smallest
			else if(currentPoint.getY() > maxX) maxX = currentPoint.getX(); //if not the smallest then maybe it is the largest		
		}
		//for each centroid select random value between minX and maxX to set value of x for each centroid. Do the same for value y
		setCentroidsInitialValues(minX, minY, maxX, maxY);		
	}
	
	//This method calculate distances and changes the label if change has occur for given DataPoint
	private void calculateDistances(){
		convergance = true; //if no changes will occur then algorithm converges
		//calculate distance for each point to each centroid and check if the closest centroid is the same as the one assigned to this point. If not then change it 
		//go through list of data points
		for(int i =0; i < data.size(); i++){
			DataPoint curPoint = data.get(i);
			double curDistanceToCentroid = distanceToCentroid(curPoint,curPoint.getLabel());
			//check for each centroid if it has shorter distance to given data point
			for(int cen = 0; cen < centroids.length; cen++ ){
				double calcDitanceToCen = distanceToCentroid(curPoint,cen); //calculated distance to currently analyzed centroid
				//if is smaller then change labels
				if(calcDitanceToCen < curDistanceToCentroid)  {
					curPoint.setLabel(cen);
					convergance = false; //if change occurred then algorithm does not converge in this iteration
				}
			}			
		}
	}
	
	//This method recalculates centroids in each cluster
	private void recalculateCentorids(){
		//add all the values of x for each data point that belongs to given centroid, do the same for values of y
		double sumXvalues[] = new double[k]; //e.g. value sumXvalues[2] is sum of all x values of data points that belong to centroid #2
		double sumYvalues[] = new double[k];
		int countCentroidMembers[] = new int[k]; //this variable keeps track how many data points are assigned to given centroid
		//go through whole array list and sum values of X for each datapoint that is assigned to given centroid. Do the same for y values
		for(int i = 0; i < data.size(); i++){
			int currentLabel = data.get(i).getLabel();
			sumXvalues[currentLabel] +=  data.get(i).getX();
			sumYvalues[currentLabel] +=  data.get(i).getY();
			countCentroidMembers[data.get(i).getLabel()]++; //increment number of datapoints assigned to given centroid
		}
		//set new values for each centroid
		for(int centrIdx = 0; centrIdx < k; centrIdx ++){
			if(countCentroidMembers[centrIdx] != 0){
				centroids[centrIdx].setX(sumXvalues[centrIdx]/countCentroidMembers[centrIdx]);
				centroids[centrIdx].setY(sumYvalues[centrIdx]/countCentroidMembers[centrIdx]);
			}			
		}
	}
	
	//assign centroids random starting value in the range of min X and max X and for Y in range of min Y and max Y
	private void setCentroidsInitialValues(double minX, double minY, double maxX, double maxY){
		double intervalX = maxX - minX;
		double intervalY = maxY - minY;
		Random ranX = new Random();
		Random ranY = new Random();
		//iterate over list of centroids and assign them random value in the range givenin parameters
		for(int i = 0; i < centroids.length; i++){
			centroids[i].setX(minX + ranX.nextDouble() * intervalX );
			centroids[i].setY(minY + ranY.nextDouble() * intervalY );
		}
	}
	
	private void intializeDataLabels(){
		//go through whole data and set labels roughly evenly
		int currentLabel = 0;
		for(int i = 0; i < data.size(); i++) {
			data.get(i).setLabel(currentLabel);
			currentLabel++; //next data point will get next label
			if(currentLabel >= k) currentLabel = 0; //wrap around
		}
	}
	
	private double distanceToCentroid(DataPoint curPoint, int centroidNum){
		return calculateDistance(curPoint.getX(), curPoint.getY(), centroids[centroidNum].getX(), centroids[centroidNum].getY());
	}
	
	//This method calculates Intercluster Variability (IV) 
	public double getIV(){
		double result =0;
		//for each cluster clust sum the distances from points that belong to this cluster to cluster centroid and sum the results
		for(int clust = 0; clust < k; clust++){
			for(int idx = 0; idx < data.size(); idx++){
				if(data.get(idx).getLabel() == clust) result += distanceToCentroid(data.get(idx), clust);
			}
			
		}
		return result;		
	}
	
	//This method calculates Extracluster Variability (EV) 
	public double getEV(){
		double result = 0;
		//EV = (1/n) sigma(i) sigma(j) dirac C(xi)≠C(xj))d(xi,xj)
		//sum distances of elements from different clusters
		for(int i = 0; i < data.size(); i ++) {
			for(int j = 0; j < data.size(); j++){
				if(data.get(i).getLabel() != data.get(j).getLabel()) 
					result +=calculateDistance(data.get(i).getX(), data.get(i).getY(), data.get(j).getX(), data.get(j).getY());
			}
		}
		//divide result by number of elements
		return result/data.size();

	}
	
	private double calculateDistance(double x1, double y1, double x2, double y2){
		//calculate interval distance for each x and y between two points
		double intervalX = Math.abs(x1 - x2);
		double intervalY = Math.abs(y1 - y2);
		//use Euclidean distance to calculate distance returned
		double result = Math.sqrt(intervalX*intervalX + intervalY*intervalY ) ;// a^2 + b^2 = c^2 => square root of (a^2 + b^2) = c, which is our distance
		return result;
	}
	
	private int countCentroidMembers(int cent){
		int result = 0;
		for(int i=0; i < data.size(); i++){
			if(data.get(i).getLabel() == cent ) result++;
		}
		return result;
	}
}
