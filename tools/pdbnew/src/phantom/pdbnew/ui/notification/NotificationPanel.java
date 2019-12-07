package phantom.pdbnew.ui.notification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NotificationPanel extends JPanel {
    private ArrayList<NotificationMessage> ms = new ArrayList<>();
    public NotificationPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(200, 200);
        //setBackground(Color.DARK_GRAY);
    }

    public void notifyMessage(String msg, NotificationType notificationType) {
        NotificationMessage m = (new NotificationMessage(this, msg, notificationType));
        add(m);
        Timer t = new Timer(4000, e -> {
            remove(m);
            getParent().revalidate();
        });
        t.setRepeats(false);
        t.start();
        getParent().revalidate();
    }
}
