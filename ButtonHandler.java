import java.awt.event.*;
class ButtonHandler implements ActionListener{
static Kmeans kmeans;

  public ButtonHandler(){}
  //responds to the user clicking on a button
  public void actionPerformed(ActionEvent event) {
    String buttonName = event.getActionCommand();//records the button item the user clicks on
        
      //series of if else statements to decide what happens when the user clicks on a specific button
      if (buttonName.equals("Run")) {
          kmeans = new Kmeans();//insert k number
          loadData();//get states and age group
          runKmeans();
          KmeansGUI.run.setEnabled(false);
          KmeansGUI.find.setEnabled(true);
          KmeansGUI.restart.setEnabled(true);
      } else if (menuName.equals("Find Optimal K")) {
          //insert method here
    	} else if (menuName.equals("New Evaluation")){
    	    //set something to null
    	    runKmeans();
    	}
    }  

}
