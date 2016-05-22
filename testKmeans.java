import java.util.HashMap;


public class testKmeans {

	public static void main(String[] args) {
		int k = 3;
		
		Kmeans km = new Kmeans(k);
		HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
		km.loadData(hm, 1);
		km.runKmeans();
		String [] results = km.getStatistics();
		for(int i=0; i < results.length ; i++)
			System.out.println(results[i]);
		
		
		
	}
}
