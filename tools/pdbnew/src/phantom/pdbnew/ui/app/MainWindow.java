package phantom.pdbnew.ui.app;

import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.ObjectView;
import phantom.pdbnew.ui.PObjectPanel;
import phantom.pdbnew.ui.notification.NotificationType;
import phantom.pdbnew.ui.system.UITransmitter;
import phantom.pdbnew.ui.system.UIUtil;
import phantom.pdbnew.ui.system.UIWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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

    public MainWindow() {
        onInitializeUI();
        onConstructUI();
    }

    @Override
    public void onConstructUI() {
        self.setLayout(new BorderLayout());

        onConstructToolbar(toolbar.self);

        self.add(toolbar.self, BorderLayout.PAGE_START);
        self.add(objectView.self, BorderLayout.CENTER);
        self.add(notificationPanel.self, BorderLayout.PAGE_END);

        self.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        self.setVisible(true);
    }

    @Override
    public void onInitializeUI() {
        toolbar = new MainWindowToolbar();
        objectView = new MainWindowObjectView(new ObjectView());
    }

    private void UI_newDumpView() {
        try {
            ByteBuffer bb = getByteBufferFromFile();
            if(bb != null) {
                //pop = new PObjectPanel(null);
                //debugger = new Debugger(bb);
                //uit = new UITransmitter(debugger, this, pop);
                //debugger.uit = uit;
                //pop.uit = uit;
                //debugger.load();
            } else {
                notificationPanel.self.notify("File loading canceled!", NotificationType.WARNING);
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
                        "View new dump.",
                        "add"
                )
        );
        tb.add(
                navButton(
                        UIUtil.theme.getUrl_icon_resource_inf(),
                        e -> {
                            notificationPanel.self.notify(
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
}
