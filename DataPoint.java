/*
 * DataPoint.java
 * This class stores datapoints for each zipcode.
 * 
 */
public class DataPoint {
	private String zipCode;
	private double x;
	private double y;
	private int label; //data label - to which cluster this point belongs to
	
	/*
	 * @param zip	zip code of a given data point
	 */
	public DataPoint(String zip)
	{
		zipCode = zip;
	}
	
	//
	public void convertToGeoLocation(){
		//TO-DO: read from file of geo-locations and map this zip code with x and y values.
		//since longtitude on eastern hemisphere has negative value we need to add 180 to each x point. We will subtract it from the final result's x value
		
	}
	
	//accessors
	public double getX() { return x; }
	public double getY() { return y; }
	public String getZipCode() { return zipCode; }

}
