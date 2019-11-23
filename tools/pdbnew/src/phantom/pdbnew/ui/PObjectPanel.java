package phantom.pdbnew.ui;

import phantom.pdbnew.pdb.SimplePObject;
import phantom.pdbnew.ui.components.MTable;
import phantom.pdbnew.ui.system.ThemedUI;
import phantom.pdbnew.ui.system.UIConstructor;
import phantom.pdbnew.ui.system.UITransmitter;
import phantom.pdbnew.ui.system.LoggerUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.nio.ByteOrder;

// TODO: Everything. (The answer is 42);

/**
 * Basically main functionality of the debugger here.
 * @author somerandomdev49
 */
public class PObjectPanel extends ThemedUI<JPanel> {
    public UITransmitter uit; // must be set directly.
    private MTable table;

    protected PObjectPanel(SimplePObject object) {
        super(new JPanel(new FlowLayout()));
        table = new MTable(new Component[][]{
                { new JLabel(".internal.string")  , new JButton("object@0x1232131") , new JLabel("\"Hello, World!\"")  },
                { new JLabel(".internal.int")     , new JButton("object@0x2312312") , new JLabel("123")                },
                { new JLabel("object")            , new JButton("object@0x5464577") , new JLabel("")                   },
        }, new String[] {"Type", "Value", "Simple Value"});
        table.setSize(100, 100);
        onConstructUI();
    }

    @Override
    public void onReceive(String type, Object msg) {
        if(type.equals("goto")) {
            uit.send("goto-success", null);
        }
    }

    @Override
    public void onConstructUI() {
        GridLayout l = new GridLayout(1, 1);
        self.setLayout(l);
        self.add(table);
    }

    @Override
    public void onConstructToolbar(JToolBar tb) { }

    @Override
    public void onConstructTabview(JTabbedPane tb) {

    }

    @Override
    public void onInitializeUI() {

    }
}
