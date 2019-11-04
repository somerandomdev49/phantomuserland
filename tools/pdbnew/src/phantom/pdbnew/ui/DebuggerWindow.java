package phantom.pdbnew.ui;

import phantom.pdbnew.Receiver;
import phantom.pdbnew.UITransmitter;
import phantom.pdbnew.pdb.Debugger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author somerandomdev49
 * @see phantom.pdbnew.pdb.Debugger
 */
public class DebuggerWindow extends JFrame implements Receiver {
    public Debugger debugger;
    NotificationPanel np;
    UITransmitter uit;
    public DebuggerWindow() {
//        super((Window) null);
        super();
        //setModal(true);
        setTitle("Phantom Debugger.");
        initializeUI();
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }



    private ByteBuffer getByteBufferFromFile() throws IOException {
        JFileChooser fCh = new JFileChooser("/Users/mishka/Documents/GitHub/phantomuserland/tools/pdbnew/data");
        if(fCh.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fCh.getSelectedFile();
            InputStream initialStream = new FileInputStream(file);
            return ByteBuffer.wrap(initialStream.readAllBytes());
        }
        return null;
    }
    public void cleanup() {
        debugger.cleanup();
    }


    //<--////////////////////// UI ACTION //////////////////////-->//

    @Override
    public void onReceive(String type, Object msg) {
        if(type.equals("start-success")) {
            System.out.println("Debugger successfully started! [SUCCESS]");
            np.notify("Debugger successfully started!", NotificationType.SUCCESS);
            revalidate();
        }
    }

    private void UI_newDumpView() {
        try {
            debugger = new Debugger(getByteBufferFromFile());
            uit = new UITransmitter(debugger, this);
            debugger.uit = uit;
            debugger.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //<--////////////////////// UI //////////////////////-->//

    private void initializeUI() {
        JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
        initializeToolbar(tb);
        //JButton viewButton = new JButton("View!");
        //viewButton.addActionListener(e -> {
        //
        //});
        JPanel panel = new JPanel(new BorderLayout());
        np = new NotificationPanel();
        //panel.add(viewButton);
        panel.add(np, BorderLayout.SOUTH);
        panel.add(tb, BorderLayout.PAGE_START);
        add(panel);
    }

    private void initializeToolbar(JToolBar tb) {
        tb.setFloatable(false);
        tb.add(makeNavigationButton("add", e -> {
            UI_newDumpView();
        }, "View new dump.", "add"));
    }

    protected JButton makeNavigationButton(String imageName,
                                           ActionListener l,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation =
                UIUtil.RESOURCES_PATH
                + imageName
                + ".png";
        //URL imageURL = ToolBarDemo.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.addActionListener(l);
        button.setIcon(new ImageIcon(UIUtil.getScaledImage(new ImageIcon(imgLocation, altText).getImage(), 32, 32)));
        //} else {                                     //no image found
        //    button.setText(altText);
        //    System.err.println("Resource not found: " + imgLocation);
        //}

        return button;
    }



    public static void main(String args[]) {
        DebuggerWindow dw = new DebuggerWindow();
        // transmitter moved to dw.
        //dw.cleanup();
    }
}
