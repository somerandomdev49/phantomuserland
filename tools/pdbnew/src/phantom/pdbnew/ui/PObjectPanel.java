package phantom.pdbnew.ui;

import phantom.pdbnew.Receiver;
import phantom.pdbnew.UITransmitter;

import javax.swing.*;
import javax.swing.table.TableColumn;

// TODO: Everything. (The answer is 42);

/**
 * Basically main functionality of the debugger here.
 * @author somerandomdev49
 */
public class PObjectPanel extends JPanel implements Receiver, UIConstructor {
    public UITransmitter uit; // must be set directly.
    private JTable table;

    @Override
    public void onReceive(String type, Object msg) {
        if(type.equals("goto")) {
            uit.send("goto-success", null);
        }
    }

    @Override
    public void onConstructUI() {
        Object[][] data = {};//uit.getDb().getUIReadyData();

        table = new JTable();

    }

    @Override
    public void onConstructToolbar(JToolBar tb) { }
}
