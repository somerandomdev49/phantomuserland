package phantom.pdbnew.ui.notification;

import phantom.pdbnew.ui.system.UIUtil;

import javax.swing.*;
import java.awt.*;

public class NotificationMessage extends JLabel {

    public static final int IMAGE_SIZE = 16; //px;

    public NotificationMessage(Container p, String msg, NotificationType nt) {
        super(msg);
        setFont(new Font("Fira Code", Font.PLAIN, 14));
        setForeground(nt.getFgColor(UIUtil.theme));
        setBackground(nt.getBgColor(UIUtil.theme));
        setIcon(new ImageIcon(UIUtil.getScaledImage(nt.getIcon(UIUtil.theme).getImage(), IMAGE_SIZE, IMAGE_SIZE)));
//        SwingUtilities.invokeLater(() -> {
//            try {
//                Thread.sleep(1000);
//                p.remove(this);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });


//      new Thread(() -> {
//            try {
//                Thread.sleep(1000);//currentThread().wait(1000);
//                getParent().remove(this);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
        // THAT is what we need, AWFUL GLASS ICONS!
        // LOOK AT THIS BEAUTY! https://www.iconfinder.com/iconsets/DarkGlass_Reworked
    }
}
