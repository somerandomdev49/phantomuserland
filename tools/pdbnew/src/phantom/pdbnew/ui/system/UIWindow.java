package phantom.pdbnew.ui.system;

import phantom.pdbnew.internal.debug.PLogger;

import javax.swing.*;

public class UIWindow extends SimpleUIWindow {

    public UIWindow() {
        super(new JFrame());
    }

    @Override
    public void onReceive(String type, Object msg) {}
    @Override
    public void onConstructUI() {}
    @Override
    public void onConstructToolbar(JToolBar tb) {}
    @Override
    public void onConstructTabview(JTabbedPane tb) {}

    @Override
    public void onInitializeUI() {}
}
