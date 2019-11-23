package phantom.pdbnew.ui.system;

import javax.swing.*;

public class UIDialog extends SimpleUIDialog {
    public UIDialog(JDialog self) { // the whole constructor (I didn't change ANYTHING!) is made by IntellijIDEA!
        super(self);
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
    public void onInitializeUI() {

    }
}
