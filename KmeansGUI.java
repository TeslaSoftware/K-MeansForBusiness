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
import java.awt.FlowLayout;
import java.awt.TextArea;

class KmeansGUI extends JFrame {

    static JButton run = new JButton("Run");
    static JButton find = new JButton("Find Optimal K");
    static JButton restart = new JButton("New Evaluation");
    static JButton selectAll = new JButton("Select All");
    static JPanel button = new JPanel();
    static JPanel select = new JPanel(new FlowLayout());
    static JPanel outer = new JPanel(new BorderLayout());
    static JComboBox selectAge;
    static JComboBox selectK;
    static TextArea display = new TextArea();
    static JTable check;

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
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createButtonPanel();
        add(button, BorderLayout.SOUTH);
        createSelectPanel();
        createOuterPanel();
        add(outer, BorderLayout.EAST);
        createCheckBoxTable();
        add(new JScrollPane(check));
        setVisible(true);
    }

    public void createButtonPanel() {
        button.add(run);
        button.add(find);
        button.add(restart);
        run.setEnabled(true);
        find.setEnabled(false);
        restart.setEnabled(false);
        ButtonHandler bh = new ButtonHandler();
        run.addActionListener(bh);
        find.addActionListener(bh);
        restart.addActionListener(bh);
    }

    public void createSelectPanel() {
        String[] age = {"Under 5 years", "5 to 9 years", "10 to 14 years", "15 to 19 years", "20 to 24 years", "25 to 29 years", "30 to 34 years", "35 to 39 years", "40 to 44 years", "45 to 49 years", "50 to 54 years", "55 to 59 years", "60 to 64 years", "65 to 69 years", "70 to 74 years", "75 to 79 years", "80 to 84 years", "85 years and over"};
        String[] k = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"};
        selectAge = new JComboBox(age);
        selectK = new JComboBox(k);
        JLabel ageLabel = new JLabel("Age Group");
        JLabel kLabel = new JLabel("Select K");
        ButtonHandler bh = new ButtonHandler();
        selectAll.addActionListener(bh);
        select.add(selectAll);
        select.add(ageLabel);
        select.add(selectAge);
        select.add(kLabel);
        select.add(selectK);

    }

    private void createOuterPanel() {
        outer.add(select, BorderLayout.NORTH);
        outer.add(display, BorderLayout.SOUTH);
        display.setEditable(true);
    }

    private void createCheckBoxTable() {
        Object[][] stateList = {{"AL", Boolean.FALSE}, {"AK", Boolean.FALSE}, {"AZ", Boolean.FALSE}, {"AR", Boolean.FALSE}, {"CA", Boolean.FALSE}, {"CO", Boolean.FALSE}, {"CT", Boolean.FALSE}, {"DE", Boolean.FALSE}, {"FL", Boolean.FALSE}, {"GA", Boolean.FALSE}, {"HI", Boolean.FALSE}, {"ID", Boolean.FALSE}, {"IL", Boolean.FALSE}, {"IN", Boolean.FALSE}, {"IA", Boolean.FALSE}, {"KS", Boolean.FALSE}, {"KY", Boolean.FALSE}, {"LA", Boolean.FALSE}, {"ME", Boolean.FALSE}, {"MD", Boolean.FALSE}, {"MA", Boolean.FALSE}, {"MI", Boolean.FALSE}, {"MN", Boolean.FALSE}, {"MS", Boolean.FALSE}, {"MO", Boolean.FALSE}, {"MT", Boolean.FALSE}, {"NE", Boolean.FALSE}, {"NV", Boolean.FALSE}, {"NH", Boolean.FALSE}, {"NJ", Boolean.FALSE}, {"NM", Boolean.FALSE}, {"NY", Boolean.FALSE}, {"NC", Boolean.FALSE}, {"ND", Boolean.FALSE}, {"OH", Boolean.FALSE}, {"OK", Boolean.FALSE}, {"OR", Boolean.FALSE}, {"PA", Boolean.FALSE}, {"RI", Boolean.FALSE}, {"SC", Boolean.FALSE}, {"SD", Boolean.FALSE}, {"TN", Boolean.FALSE}, {"TX", Boolean.FALSE}, {"UT", Boolean.FALSE}, {"VT", Boolean.FALSE}, {"VA", Boolean.FALSE}, {"WA", Boolean.FALSE}, {"WV", Boolean.FALSE}, {"WI", Boolean.FALSE}, {"WY", Boolean.FALSE}};
        String[] columnNames = {"State", "CheckBox"};
        States dataModel = new States(stateList, columnNames);
        check = new JTable(dataModel);
    }
}
