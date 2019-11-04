package phantom.pdbnew.ui;

import javax.swing.*;
import java.awt.*;

import static phantom.pdbnew.ui.UIUtil.RESOURCES_PATH;

public enum NotificationType {
    INFO,
    ERROR,
    WARNING,
    SUCCESS;


    Color getColor() {
        switch(this) { // oh no, this weird switch syntax...
            case INFO:
                return Color.BLUE;
            case ERROR:
                return Color.RED;
            case SUCCESS:
                return Color.GREEN;
            case WARNING:
                return Color.YELLOW;
        }
        return Color.BLACK;
    }

    ImageIcon getIcon() {
        switch(this) {
            case INFO:
                return new ImageIcon(RESOURCES_PATH+"info.png");
            case ERROR:
                return new ImageIcon(RESOURCES_PATH+"error.png");
            case SUCCESS:
                return new ImageIcon(RESOURCES_PATH+"success.png");
            case WARNING:
                return new ImageIcon(RESOURCES_PATH+"warning.png");
        }
        return new ImageIcon();
    }
}
