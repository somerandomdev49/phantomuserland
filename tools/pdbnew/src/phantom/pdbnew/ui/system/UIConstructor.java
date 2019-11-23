package phantom.pdbnew.ui.system;

import phantom.pdbnew.Receiver;

import javax.swing.*;

public interface UIConstructor extends UIInitializer {
    /**
     * Called on UI Construction.
     */
    void onConstructUI();

    /**
     * Optional!
     * @param tb toolbar to construct.
     */
    void onConstructToolbar(JToolBar tb);

    /**
     * Optional!
     * @param tb tabbed pane to construct
     */
    void onConstructTabview(JTabbedPane tb);
}
