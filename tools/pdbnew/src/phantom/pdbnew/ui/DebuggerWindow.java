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
import java.util.Random;

/**
 * @author somerandomdev49
 * @see phantom.pdbnew.pdb.Debugger
 */
public class DebuggerWindow extends JFrame implements Receiver, UIConstructor {
    public Debugger debugger;
    NotificationPanel np;
    UITransmitter uit;
    PObjectPanel pop; // pop!
    public DebuggerWindow() {
//        super((Window) null);
        super();
        //setModal(true);
        setTitle("Phantom Debugger.");
        onConstructUI();
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
            pop = new PObjectPanel();
            debugger = new Debugger(getByteBufferFromFile());
            uit = new UITransmitter(debugger, this, pop);
            debugger.uit = uit;
            pop.uit = uit;
            debugger.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //<--////////////////////// UI //////////////////////-->//
    @Override
    public void onConstructUI() {
        JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
        onConstructToolbar(tb);
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

    @Override
    public void onConstructToolbar(JToolBar tb) {
        tb.setFloatable(false);
        tb.add(
                makeNavigationButton(
                        UIUtil.theme.getUrl_icon_resource_add(),
                        e -> UI_newDumpView(),
                        "View new dump.",
                        "add"
                )
        );
        tb.add(
                makeNavigationButton(
                        UIUtil.theme.getUrl_icon_resource_inf(),
                        e -> {
                            np.notify(
                                    "<RANDOM MESSAGE>",
                                    NotificationType.values()[
                                            new Random().nextInt(
                                                    NotificationType.values().length
                                            )
                                            ]
                            );
                            revalidate();
                        },
                        "Random notification.",
                        "info"
                )
        );
    }

    protected JButton makeNavigationButton(String imgLocation,
                                           ActionListener l,
                                           String toolTipText,
                                           String altText) {
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
