/*
 * Centroid.java
 * This class stores information about centroid of a cluster
 * 
 */
public class Centroid {
	private double x;
	private double y;
	
	public Centroid() {}
	
	//mutators
	public void setX(double i) { x = i; }
	public void setY(double i) { y = i; }
	
	//accessors
	public double getX() { return x; }
	public double getY() { return y; }

}
