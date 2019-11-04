package phantom.pdbnew.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class NotificationMessage extends JLabel {

    public static final int IMAGE_SIZE = 16; //px;

    public NotificationMessage(String msg, NotificationType nt) {
        super(msg);
        setFont(new Font("Fira Code", Font.PLAIN, 14));
        setForeground(nt.getFgColor(UIUtil.theme));
        setBackground(nt.getBgColor(UIUtil.theme));
        setIcon(new ImageIcon(UIUtil.getScaledImage(nt.getIcon(UIUtil.theme).getImage(), IMAGE_SIZE, IMAGE_SIZE)));
        // THAT is what we need, AWFUL GLASS ICONS!
        // LOOK AT THIS BEAUTY! https://www.iconfinder.com/iconsets/DarkGlass_Reworked
    }
}
