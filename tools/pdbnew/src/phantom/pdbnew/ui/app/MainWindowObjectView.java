package phantom.pdbnew.ui.app;

import phantom.pdbnew.Debug;
import phantom.pdbnew.pdb.SimplePObject;
import phantom.pdbnew.pdb.SimplePObjectRef;
import phantom.pdbnew.ui.components.MTable;
import phantom.pdbnew.ui.notification.NotificationType;
import phantom.pdbnew.ui.system.ThemedUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;

public class MainWindowObjectView extends ThemedUI<JPanel> implements ActionListener {
    private MTable table;

    private GridBagConstraints c;
    private BorderLayout l;

    private int _ParentWidth, _ParentHeight;

    public JScrollPane sp;
    public SimplePObject o; // current.
    //public SimplePObject p = null; // previous.

    public MainWindowObjectView(SimplePObject o, int _ParentWidth, int _ParentHeight) {
        super(new JPanel());
        this.o = o;
        this._ParentWidth = _ParentWidth;
        this._ParentHeight = _ParentHeight;
        onInitializeUI();
        onConstructUI();
    }

    @Override
    public void onReceive(String type, Object msg) {

    }

    @Override
    public void onConstructUI() {
        sp = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //sp.setPreferredSize(new Dimension(_ParentWidth, _ParentHeight));
        self.add(sp, BorderLayout.CENTER);
        self.revalidate();
        self.repaint();
    }

    @Override
    public void onConstructToolbar(JToolBar tb) {

    }

    @Override
    public void onConstructTabview(JTabbedPane tb) {

    }

    private void linkfy(JButton b) {
        HashMap<TextAttribute, Object> textAttrMap = new HashMap<>();
        textAttrMap.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        textAttrMap.put(TextAttribute.FOREGROUND, Color.BLUE);
        b.setFont(b.getFont().deriveFont(textAttrMap));
        b.setFocusPainted(false);
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private Component componentFromSimplePObjectRef(SimplePObjectRef o) {
        JButton b = new JButton("Object@" + o.addr);
        //b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        linkfy(b);
        b.addActionListener(this);
        b.setActionCommand("MainWindowObjectView_Object" + o.id);
        return b;
    }

    @Override
    public void onInitializeUI() {
        if(table != null){
            table.layout.setHgap(0);
            table.layout.setVgap(0);
        }
        //self.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        l = new BorderLayout();

        // component list for MTable.
        ArrayList<Component> components = new ArrayList<>();

        // Button to go back.
        JButton backButton = new JButton("<-");
        // Disable the button if there's no previous node.
        backButton.setEnabled(o.parentObject!=null);
        linkfy(backButton);
        components.add(backButton);

        // Add all links.
        for(SimplePObjectRef ref : o.links) {
            components.add(componentFromSimplePObjectRef(ref));
        }
        // oranges are cool.
        // abcdefghijklmnopqrstuvwxyz 0123456789 <- Victor Mono

        // Convert from ArrayList to Component[][].
        // Thanks, ->https://codereview.stackexchange.com/questions/126368/converting-a-list-of-integers-into-a-two-dimensional-array-in-java
        Component[][] data = components.stream()
                .map(c -> new Component[]{c})
                .toArray(Component[][]::new);

        // Set the table.
        table = new MTable(data, new String[]{"Object@"+o.addr});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Extract the action command.
        String cmd = e.getActionCommand();

        /**/ if (cmd.startsWith("MainWindowObjectView_ObjectBack")) {
            o = o.parentObject.object;
            uit.getMainWindow().notifyMessage("BACK!", NotificationType.INFO);
            uit.send("objectview-go-back", null);
        } else if(cmd.startsWith("MainWindowObjectView_Object")) {
            // remove the first part.
            cmd = cmd.replace("MainWindowObjectView_Object", "");

            // extract the link number.
            int linkN = Integer.parseInt(cmd);

            uit.getDebugger().dereferenceSimpleObject(o, linkN);

            Debug.log("ReInitializing User Interface.");
            onInitializeUI();
            self.removeAll();
            onConstructUI();
            uit.getMainWindow().notifyMessage("OBJECT!", NotificationType.INFO);
            uit.send("objectview-go-object", null);
        }
    }
}
