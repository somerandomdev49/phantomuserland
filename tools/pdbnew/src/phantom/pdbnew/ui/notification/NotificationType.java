package phantom.pdbnew.ui.notification;

import phantom.pdbnew.ui.system.UITheme;

import javax.swing.*;
import java.awt.*;

public enum NotificationType {
    INFO,
    ERROR,
    WARNING,
    SUCCESS;


    Color getFgColor(UITheme theme) {
        switch(this) { // oh no, this weird switch syntax...
            case INFO:
                return theme.getColor_notification_fg_inf();
            case ERROR:
                return theme.getColor_notification_fg_err();
            case SUCCESS:
                return theme.getColor_notification_fg_scs();
            case WARNING:
                return theme.getColor_notification_fg_wrn();
        }
        return Color.BLACK;
    }

    Color getBgColor(UITheme theme) {
        switch(this) { // oh no, this weird switch syntax...
            case INFO:
                return theme.getColor_notification_bg_inf();
            case ERROR:
                return theme.getColor_notification_bg_err();
            case SUCCESS:
                return theme.getColor_notification_bg_scs();
            case WARNING:
                return theme.getColor_notification_bg_wrn();
        }
        return Color.BLACK;
    }

    ImageIcon getIcon(UITheme theme) {
        switch(this) {
            case INFO:
                return new ImageIcon(theme.getUrl_icon_resource_inf());
            case ERROR:
                return new ImageIcon(theme.getUrl_icon_resource_err());
            case SUCCESS:
                return new ImageIcon(theme.getUrl_icon_resource_scs());
            case WARNING:
                return new ImageIcon(theme.getUrl_icon_resource_wrn());
        }
        return new ImageIcon();
    }
}
