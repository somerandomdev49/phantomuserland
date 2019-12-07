package phantom.pdbnew.ui;

import phantom.pdbnew.ui.app.MainWindow;
import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.notification.NotificationPanel;
import phantom.pdbnew.ui.notification.NotificationType;
import phantom.pdbnew.ui.system.UIUtil;
import phantom.pdbnew.ui.system.UIWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author somerandomdev49
 * @see phantom.pdbnew.pdb.Debugger
 */
public class DebuggerWindow extends UIWindow {
    public Debugger debugger;
    private NotificationPanel np;
    private PObjectPanel pop; // pop!
    public DebuggerWindow() {
//        super((Window) null);
        //super(new JFrame("Phantom Debugger"));
        initializeLog(getClass());
        //setModal(true);
        self.setTitle("Phantom Debugger.");
        onConstructUI();
        self.setSize(400, 400);
        self.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        self.setVisible(true);
    }



    private ByteBuffer getByteBufferFromFile() throws IOException {
        JFileChooser fCh = new JFileChooser("/Users/mishka/Documents/GitHub/phantomuserland/tools/pdbnew/data");
        if(fCh.showOpenDialog(self) == JFileChooser.APPROVE_OPTION) {
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
            np.notifyMessage("Debugger successfully started!", NotificationType.SUCCESS);
        } else if(type.equals("start-error")) {
            System.out.println("Something went wrong while starting Debugger! [ERROR]");
            np.notifyMessage("Something went wrong while starting Debugger!", NotificationType.ERROR);
        }
    }

    private void UI_newDumpView() {
        try {
            ByteBuffer bb = getByteBufferFromFile();
            if(bb != null) {
                pop = new PObjectPanel(null);
                debugger = new Debugger(bb);
                //uit = new UITransmitter(debugger, this, null);
                //debugger.uit = uit;
                //pop.uit = uit;
                debugger.load();
            } else {
                np.notifyMessage("File loading canceled!", NotificationType.WARNING);
            }
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
        panel.add(new ObjectViewUI().self);
        np = new NotificationPanel();
        //panel.add(viewButton);
        panel.add(np, BorderLayout.PAGE_END);
        panel.add(tb, BorderLayout.PAGE_START);
        self.add(panel);
    }

    @Override
    public void onConstructToolbar(JToolBar tb) {
        tb.setFloatable(false);
        tb.add(
                navButton(
                        UIUtil.theme.getUrl_icon_resource_file(),
                        e -> UI_newDumpView(),
                        "View new dump.",
                        "add"
                )
        );
        tb.add(
                navButton(
                        UIUtil.theme.getUrl_icon_resource_inf(),
                        e -> {
                            np.notifyMessage(
                                    "Message",
                                    NotificationType.values()[
                                            new Random().nextInt(
                                                    NotificationType.values().length
                                            )
                                            ]
                            );

                        },
                        "Random notification.",
                        "info"
                )
        );
    }


    public static void main(String args[]) {
        MainWindow dw = new MainWindow();

        // transmitter moved to dw.
        //dw.cleanup();
    }
}
