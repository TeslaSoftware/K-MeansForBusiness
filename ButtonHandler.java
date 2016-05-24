
import java.awt.event.*;

class ButtonHandler implements ActionListener {

    static Kmeans kmeans;

    public ButtonHandler() {
    }
    //responds to the user clicking on a button

    public void actionPerformed(ActionEvent event) {
        String buttonName = event.getActionCommand();//records the button item the user clicks on

        //series of if else statements to decide what happens when the user clicks on a specific button
        if (buttonName.equals("Run")) {
            String k = (String) KmeansGUI.selectK.getSelectedItem();
            String age = (String) KmeansGUI.selectAge.getSelectedItem();
            kmeans = new Kmeans(Integer.parseInt(k));//insert k number
            kmeans.loadData();//get states and age group
            kmeans.runKmeans();
            KmeansGUI.run.setEnabled(false);
            KmeansGUI.find.setEnabled(true);
            KmeansGUI.restart.setEnabled(true);
        } else if (buttonName.equals("Find Optimal K")) {
            //insert method here
        } else if (buttonName.equals("New Evaluation")) {
            //set something to null
            String k = (String) KmeansGUI.selectK.getSelectedItem();
            String age = (String) KmeansGUI.selectAge.getSelectedItem();
            kmeans = new Kmeans(Integer.parseInt(k));
            kmeans.loadData();//get states and age group
            kmeans.runKmeans();
        }
    }
}
