package phantom.pdbnew.ui.components;

import phantom.pdbnew.ui.system.ThemedUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Mike's Table :)
 * Provides alternative to JTable,
 * because MTable can store other
 * Components in itself.
 */
public class MTable extends JPanel {
    //public ArrayList<ArrayList<Component>> rows;
    //public ArrayList<String> names;
    private Component[][] rows;
    private String[] names;

    public GridLayout layout;
    //public Class<JLabel> defaultColumnNameComponent;

    public MTable(Component[][] data, String[] columnNames) {
        super(new BorderLayout(data.length, data[0].length));
        rows = data;
        names = columnNames;
        UI();
    }

    private void UI() {
        int i = rows.length + 1;
        int j = rows[0].length;
        JPanel[][] panelHolder = new JPanel[i][j];
        layout = new GridLayout(i,j);
        setLayout(layout);

        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                add(panelHolder[m][n]);
            }
        }
        for(int n = 0; n < j; n++) {
            panelHolder[0][n].add(new JLabel(names[n]));
        }

        for(int m = 1; m < i-1; m++) {
            for(int n = 0; n < j; n++) {
                System.out.println(m + "  :  " + n);
                panelHolder[m][n].add(rows[m][n]);
            }
        }
    }
}
