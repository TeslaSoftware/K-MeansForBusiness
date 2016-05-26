import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class testKmeans {

	public static void main(String[] args) {
		int k = 3;
		
		Kmeans km = new Kmeans(k);
		ArrayList<DataPoint> dataPoints;
		HashMap<String,Boolean> statesSelected = new HashMap<>();
		statesSelected.put("NY",true);
		statesSelected.put("NJ",true);
		statesSelected.put("CA",false);
		statesSelected.put("CT",true);
		km.loadData(statesSelected, 1);
		km.runKmeans();
		String [] results = km.getStatistics();
		for(int i=0; i < results.length ; i++)
			System.out.println(results[i]);
		double [] minEVIV = km.calculateOptimumK();
		for(int i=2; i < minEVIV.length ; i++)
			System.out.println("IV/EV for k=" + i  +" is " + minEVIV[i]);
		System.out.println("Optimum k for min IV/EV is: " + km.getOptimumK());
		
		double [] elbowResults = km.calculateElbowMethod();
		for(int i=2; i < elbowResults.length ; i++)
			System.out.println("SSE for k=" + i  +" is " + elbowResults[i]);
		System.out.println("Optimum k using elbow method is: " + km.getOptimumK());
		
		System.out.println("The end.");

	}
}
