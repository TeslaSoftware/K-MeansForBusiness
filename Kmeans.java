import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.event.*;

/*
 * Kmeans.java
 * This class will perform step-by-step k-means algorithm and evaluate results as well as provide the centroids
 * 
 */

public class Kmeans implements ActionListener{
	Centroid[] centroids;
	int k;
	ArrayList<DataPoint> data;
	boolean convergance = false;
	
	//^^fix
	public Kmeans(int clusters) {
		k = clusters;
		centroids = new Centroid[k];
		data = new ArrayList<DataPoint>();
	}
	
	 //responds to the user clicking on a menu item
    public void actionPerformed(ActionEvent event) {
        String menuName = event.getActionCommand();//records the menu item the user clicks on
        
        //series of if else statements to decide what happens when the user clicks on a specific menu item
        if (menuName.equals("Load Data")) {
            loadData();//^^add proper variables
        } else if (menuName.equals("Run Algorithm")) {
           runKmeans();
    	}
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
		String [] result;
		
		//TO-DO
		
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
			if(currentLabel > k) currentLabel = 0; //wrap around
		}
	}
	
	private double distanceToCentroid(DataPoint curPoint, int centroidNum){
		//calculate interval distance for each x and y between data point and given centroid
		double intervalX = Math.abs(curPoint.getX() - centroids[centroidNum].getX()); //absolute value of ((dataPoint X value) - (centroid X value))
		double intervalY = Math.abs(curPoint.getX() - centroids[centroidNum].getX()); //absolute value of ((dataPoint Y) - (centroid Y))
		//use Euclidean distance to calculate distance returned
		double result = Math.sqrt(intervalX*intervalX + intervalY*intervalY ) ;// a^2 + b^2 = c^2 => square root of (a^2 + b^2) = c, which is our distance
		return result;
	}
}
