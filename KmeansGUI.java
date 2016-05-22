/*
 * KmeansGUI.java
 * then user can select from dropdown menu number of k (from 2 to lets say 100) 
 * then user can select from dropown menu targeted age group 
 * Then user presses run button after which algorithm is run
 * User gets displayed statistics on JLabel as well as Centroids final values
 * <Optional> Then user will get displayed google maps showing geo-location of each centroid
 * you can find this useful for mapping multiple data points on a map https://gist.github.com/parth1020/4481893
 */
import javax.swing.*;
import java.awt.FLowLayout;
public class KmeansGUI extends JFrame{
	static JMenuBar menuBar = new JMenuBar();//menu bar used to hold the GUI's drop down menus
	static JPanel checkBoxPanel = new JPanel();
	
	public KmeansGUI(String title) {
        setTitle(title);
        setLocation(200, 100);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setJMenuBar(menuBar);
	createAlgorithmMenu();
	createRepeatMenu();
	createCheckBoxPanel();
	add(checkBoxPanel);
	setVisible(true);
	}
	/*to do the algorithm first create Kmeans object
	 * then load the database using method loadData on this object, 
	 * passing HashTable<String, Boolean> where string is state abriviation and boolean is value indicating if state has been selected
	 * then call method runKmeans() to run algorithm
	 * then call method getCentroids() to obtain values of centroids (which are adjusted to their proper geo-location
	 * then in the end call method  getStatistics() statistics to get returned information to display regarding EV and IV
	 * tu run again set the object to null and do above steps once again
	 */
	 //create the alogrithm menu and adds it to the menu bar
    private void createAlgorithmMenu() {
        JMenuItem algorithmItem;//menu item variable that is used to add menu items to the menu
        JMenu algorithmMenu = new JMenu("Algorithm");//declares algorithm menu 
        Kmeans km = new Kmeans();//object of kmeans class that has a method that responds to the users click of the menu item
        
        //initializes menu item, adds a action listener to the menu item, and add the menu item to the menu
        algorithmItem = new JMenuItem("Load Data");
        algorithmItem.addActionListener(km);
        algorithmMenu.add(algorithmItem);

        //draws a line to seperate between one menu item and another
        algorithmMenu.addSeparator();
        
        //initializes menu item, adds a action listener to the menu item, and add the menu item to the menu
	algorithmItem = new JMenuItem("Run Algorithm");
        algorithmItem.addActionListener(km);
        algorithmMenu.add(algorithmItem);
        
        //adds the menu to the menu bar
        menuBar.add(algorithmMenu);
    }
     //create the repeat menu and adds it to the menu bar
    private void createRepeatMenu() {
        JMenuItem repeatItem;//menu item variable that is used to add menu items to the menu
        JMenu repeatMenu = new JMenu("New Evaluation");//declares repeat menu 
        Kmeans km = new Kmeans();//object of kmeans class that has a method that responds to the users click of the menu item
        
        //initializes menu item, adds a action listener to the menu item, and add the menu item to the menu
        repeatItem = new JMenuItem("New Evaluation");
        repeatItem.addActionListener(km);
        repeatMenu.add(repeatItem);

        //adds the menu to the menu bar
        menuBar.add(repeatMenu);
    }
    
    private void createCheckBoxPanel(){
    	checkBoxPanel.setLayout(new FlowLayout());
    	
    }
	
}
