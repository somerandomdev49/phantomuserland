package phantom.pdbnew.ui;

import phantom.pdbnew.Receiver;

import javax.swing.*;

public interface UIConstructor {
    void onConstructUI();

    /**
     * Optional!
     * @param tb toolbar to construct.
     */
    void onConstructToolbar(JToolBar tb);
}
