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
	 * @param	zip		zip code of a given data point
	 * @param	lat		latitude of the zip code
	 * @param 	lng 	longitude of the zip code 
	 */
	public DataPoint(String zip, double lat, double lng)
	{
		zipCode = zip;
		y = lat;	//map latitude to x-coordinate
		x = lng;	//map longtitude to y-coordinate
	}
	
	//accessors
	public double getX() { return x; }
	public double getY() { return y; }
	public String getZipCode() { return zipCode; }
	public void setLabel(int l) { label = l; }
	public int getLabel() { return label; }

	public String toString(){
		return zipCode+" "+ x + " " + y;
	}

}
