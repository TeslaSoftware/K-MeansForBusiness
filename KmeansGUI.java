/*
 * KmeansGUI.java
 * This class allows user to select from check boxes the targeted age group 
 * then user can select from dropdown menu number of k (from 2 to lets say 100) 
 * then user can select from dropown menu targeted age group 
 * Then user presses run button after which algorithm is run
 * User gets displayed statistics on JLabel as well as Centroids final values
 * <Optional> Then user will get displayed google maps showing geo-location of each centroid
 * you can find this useful for mapping multiple data points on a map https://gist.github.com/parth1020/4481893
 */
import javax.swing.*;
import java.awt.BorderLayout;
class KmeansGUI extends JFrame{
static JButton run = new JButton("Run");
static JButton find = new JButton("Find Optimal K");
static JButton restart = new JButton("Restart Algorithm");
static JPanel button = new JPanel();
	
	/*to do the algorithm first create Kmeans object
	 * then load the database using method loadData on this object, 
	 * passing HashTable<String, Boolean> where string is state abriviation and boolean is value indicating if state has been selected
	 * then call method runKmeans() to run algorithm
	 * then call method getCentroids() to obtain values of centroids (which are adjusted to their proper geo-location
	 * then in the end call method  getStatistics() statistics to get returned information to display regarding EV and IV
	 * tu run again set the object to null and do above steps once again
	 */
	public KmeansGUI(String title) {
        	setTitle(title);
        	setLocation(200, 100);
        	setSize(500, 300);
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		createButtonPanel();
		add(button, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void createButtonPanel(){
		button.add(run);
		button.add(find);
		button.add(restart);
		run.setEnabled(true);
		find.setEnabled(false);
		restart.setEnabled(false);
	}
	
}
