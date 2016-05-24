
import javax.swing.table.DefaultTableModel;

public class States extends DefaultTableModel {

    public States(Object[][] states, String[] columnNames) {
        super(states, columnNames);
    }

    //enables the program to convert Boolean value into check box
    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 1) {
            return getValueAt(0, 1).getClass();
        }
        return super.getColumnClass(col);
    }
}
