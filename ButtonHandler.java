
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Stack;

class ButtonHandler implements ActionListener {

    static Kmeans kmeans;
    static HashMap states;
    static Hashtable ageGroup;
    static PrintWriter writer = null;
    static String theK = "";
    static String theAge = "";

    public ButtonHandler() {
    }

    public void actionPerformed(ActionEvent event) {
        String buttonName = event.getActionCommand();//records the button item the user clicks on

        //series of if else statements to decide what happens when the user clicks on a specific button
        if (buttonName.equals("Run")) {

            try {
                writer = new PrintWriter("resultsCentroids.txt", "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String k = (String) KmeansGUI.selectK.getSelectedItem();
            String age = (String) KmeansGUI.selectAge.getSelectedItem();
            theK = k;
            theAge = age;
            kmeans = new Kmeans(Integer.parseInt(k));
            states = new HashMap();
            ageGroup = new Hashtable();
            getStates();
            loadAgeGroups();
            int targetGroup = (int) ageGroup.get(age);
            kmeans.loadData(states, targetGroup);
            kmeans.runKmeans();
            KmeansGUI.display.setText("");
            writer.println("Evaluation for k = " + k + ", demographic group = " + age + ", states = " + printSelectedStates());
            KmeansGUI.display.append("Evaluation for k = " + k + ", demographic group = " + age + ", states = " + printSelectedStates() + "\n");
            String[] results = kmeans.getStatistics();
            for (int i = 0; i < results.length; i++) {
                KmeansGUI.display.append(results[i] + "\n");
                writer.println(results[i]);
            }
            writer.close();
            KmeansGUI.findMin.setEnabled(true);
            KmeansGUI.findElbow.setEnabled(true);
        } else if (buttonName.equals("Find Optimal K Minimum Method")) {
            try {
                writer = new PrintWriter("resultsMinimumMethod.txt", "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.println("Evaluation for k = " + theK + ", demographic group = " + theAge + ", states = " + printSelectedStates());
            KmeansGUI.display.append("Evaluation for k = " + theK + ", demographic group = " + theAge + ", states = " + printSelectedStates() + "\n");
            double[] minEVIV = kmeans.calculateOptimumK();
            for (int i = 2; i < minEVIV.length; i++) {
                KmeansGUI.display.append("IV/EV for k=" + i + " is " + minEVIV[i] + "\n");
                writer.println("IV/EV for k=" + i + " is " + minEVIV[i]);
            }

            KmeansGUI.display.append("Optimum k for min IV/EV is: " + kmeans.getOptimumK() + "\n");
            writer.println("Optimum k for min IV/EV is: " + kmeans.getOptimumK());
            writer.close();
        } else if (buttonName.equals("Find Optimal K Elbow Method")) {
            try {
                writer = new PrintWriter("resultsElbowMethod.txt", "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.println("Evaluation for k = " + theK + ", demographic group = " + theAge + ", states = " + printSelectedStates());
            KmeansGUI.display.append("Evaluation for k = " + theK + ", demographic group = " + theAge + ", states = " + printSelectedStates() + "\n");
            double[] elbowResults = kmeans.calculateElbowMethod();
            for (int i = 2; i < elbowResults.length; i++) {
                KmeansGUI.display.append("SSE for k=" + i + " is " + elbowResults[i] + "\n");
                writer.println("SSE for k=" + i + " is " + elbowResults[i]);
            }

            KmeansGUI.display.append("Optimum k using elbow method is: " + kmeans.getOptimumK() + "\n");
            writer.println("Optimum k for min IV/EV is: " + kmeans.getOptimumK());
            writer.close();

        } else if (buttonName.equals("Select All")) {
            for (int i = 0; i < 50; i++) {
                KmeansGUI.check.getModel().setValueAt(true, i, 1);
            }

        }
    }

    private void getStates() {
        String[] stateList = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
        for (int i = 0; i < 50; i++) {
            states.put(stateList[i], Boolean.valueOf(KmeansGUI.check.getValueAt(i, 1).toString()));
        }

    }

    private void loadAgeGroups() {
        Object ageList[] = {"Under 5 years", "5 to 9 years", "10 to 14 years", "15 to 19 years", "20 to 24 years", "25 to 29 years", "30 to 34 years", "35 to 39 years", "40 to 44 years", "45 to 49 years", "50 to 54 years", "55 to 59 years", "60 to 64 years", "65 to 69 years", "70 to 74 years", "75 to 79 years", "80 to 84 years", "85 years and over"};
        for (int i = 0; i < 18; i++) {
            ageGroup.put(ageList[i], i + 1);
        }
    }

    private String printSelectedStates() {
        String theStates = "";
        for (int i = 0; i < 50; i++) {
            if (KmeansGUI.check.getModel().getValueAt(i, 1) == true) {
                theStates = theStates.concat(KmeansGUI.check.getValueAt(i, 0) + ", ");
            }
        }
        return theStates;
    }
}
