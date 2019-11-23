package phantom.pdbnew.ui.system;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class SimpleUIDialog extends ThemedUI<JDialog> {
    public UITransmitter uit;

    public SimpleUIDialog(JDialog self) {
        super(self);
    }

    protected JButton navButton(String imgLocation,
                                ActionListener l,
                                String toolTipText,
                                String altText) {
        //URL imageURL = ToolBarDemo.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.addActionListener(l);
        button.setIcon(new ImageIcon(UIUtil.getScaledImage(new ImageIcon(imgLocation, altText).getImage(), 32, 32)));
        //} else {                                     //no image found
        //    button.setText(altText);
        //    System.err.println("Resource not found: " + imgLocation);
        //}

        return button;
    }
}
