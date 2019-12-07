package phantom.pdbnew.ui;

import phantom.pdbnew.ui.app.ObjectView;
import phantom.pdbnew.ui.system.ThemedUI;

import javax.swing.*;

public class ObjectViewUI extends ThemedUI<JPanel> {
    public ObjectViewUI() {
        super(new ObjectView());
    }



    @Override
    public void onReceive(String type, Object msg) {

    }

    @Override
    public void onConstructUI() {

    }

    @Override
    public void onConstructToolbar(JToolBar tb) {

    }

    @Override
    public void onConstructTabview(JTabbedPane tb) {

    }

    @Override
    public void onInitializeUI() {

    }
}
