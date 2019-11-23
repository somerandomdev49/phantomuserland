package phantom.pdbnew.ui.notification;

import javax.swing.*;

public class NotificationPanel extends JPanel {
    public NotificationPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(200, 200);
        //setBackground(Color.DARK_GRAY);
    }

    public void notify(String msg, NotificationType notificationType) {
        add(new NotificationMessage(this, msg, notificationType));
        getParent().revalidate();
    }
}
