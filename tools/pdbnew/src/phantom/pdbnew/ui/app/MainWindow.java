package phantom.pdbnew.ui.app;

import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.notification.NotificationPanel;
import phantom.pdbnew.ui.notification.NotificationType;
import phantom.pdbnew.ui.system.UITransmitter;
import phantom.pdbnew.ui.system.UIUtil;
import phantom.pdbnew.ui.system.UIWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;

public class MainWindow extends UIWindow {
    // COMPONENTS //
    private MainWindowToolbar toolbar;
    private MainWindowObjectView objectView;
    private MainWindowNotificationPanel notificationPanel;

    public Debugger db;

    public MainWindow() {
        //this.db = db;
        onInitializeUI();
        onConstructUI();
    }

    public void notifyMessage(String msg, NotificationType notificationType) {
        notificationPanel.self.notifyMessage(msg, notificationType);
    }

    @Override
    public void onConstructUI() {


        onConstructToolbar(toolbar.self);


        self.add(toolbar.self, BorderLayout.PAGE_START);
        //self.add(objectView.self, BorderLayout.CENTER);
        self.add(notificationPanel.self, BorderLayout.PAGE_END);


    }

    @Override
    public void onInitializeUI() {
        self.setLayout(new BorderLayout());

        toolbar = new MainWindowToolbar();
        //objectView = new MainWindowObjectView(new ObjectView(), db.dereferenceSimpleObject(null, -1));
        notificationPanel = new MainWindowNotificationPanel(new NotificationPanel());

        if(objectView != null)
            objectView.sp.setPreferredSize(new Dimension(self.getWidth()-50, self.getHeight()-50));
        self.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                if(objectView != null) {
                    objectView.sp.setPreferredSize(new Dimension(self.getWidth()-50, self.getHeight()-50));
                }
            }
        });

        self.setSize(new Dimension(200, 200));
        self.setLocationByPlatform(true);
        self.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        self.setVisible(true);
    }

    private void UI_newDumpView() {
        try {
            ByteBuffer bb = getByteBufferFromFile();
            if(bb != null) {
                db = new Debugger(bb);
                uit = new UITransmitter(db, this);
                db.uit = uit;
                db.load();
                objectView = new MainWindowObjectView(db.dereferenceSimpleObject(null, -1), self.getWidth(), self.getHeight());
                objectView.uit = uit;
                self.add(objectView.self);
                self.revalidate();
                self.repaint();
            } else {
                notificationPanel.self.notifyMessage("File loading canceled!", NotificationType.WARNING);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    private ByteBuffer getByteBufferFromFile() throws IOException {
        JFileChooser fCh = new JFileChooser("/Users/mishka/Documents/GitHub/phantomuserland/tools/pdbnew/data");
        if (fCh.showOpenDialog(self) == JFileChooser.APPROVE_OPTION) {
            File file = fCh.getSelectedFile();
            InputStream initialStream = new FileInputStream(file);
            return ByteBuffer.wrap(initialStream.readAllBytes());
        }
        return null;
    }

    @Override
    public void onConstructToolbar(JToolBar tb) {
        tb.setFloatable(false);
        tb.add(
                navButton(
                        UIUtil.theme.getUrl_icon_resource_file(),
                        e -> UI_newDumpView(),
                        "Load file.",
                        "add"
                )
        );
        tb.add(
                navButton(
                        UIUtil.theme.getUrl_icon_resource_inf(),
                        e -> notificationPanel.self.notifyMessage(
                                "Message",
                                NotificationType.values()[
                                        new Random().nextInt(
                                                NotificationType.values().length
                                        )
                                        ]
                        ),
                        "Random notification.",
                        "info"
                )
        );
    }
}
