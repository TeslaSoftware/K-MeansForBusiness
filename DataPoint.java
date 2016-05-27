/*
 * DataPoint.java
 * This class stores datapoints for each zipcode.
 * 
 */
public class DataPoint {
	private String zipCode;
	private double x;
	private double y;
	private int population;
	private int label; //data label - to which cluster this point belongs to
	
	/*
	 * @param	zip		zip code of a given data point
	 * @param	lat		latitude of the zip code
	 * @param 	lng 	longitude of the zip code 
	 * @param 	pop		population for this zipcode of targeted group
	 */
	public DataPoint(String zip, double lat, double lng, int pop)
	{
		zipCode = zip;
		y = lat;	//map latitude to x-coordinate
		x = lng;	//map longtitude to y-coordinate
		population = pop;
	}
	
	//accessors
	public double getX() { return x; }
	public double getY() { return y; }
	public String getZipCode() { return zipCode; }
	public int getPopulation() { return population; }
	public void setLabel(int l) { label = l; }
	public int getLabel() { return label; }
	

}
