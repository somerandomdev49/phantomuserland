package phantom.pdbnew.ui;

import javax.swing.*;
import java.awt.*;

public class ObjectView extends JPanel {
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(10, 10, 40, 40);
    }
}
