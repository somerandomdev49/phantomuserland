package phantom.pdbnew.ui;

import phantom.pdbnew.ui.settings.MainPanel;
import phantom.pdbnew.ui.settings.ThemesPanel;
import phantom.pdbnew.ui.system.UIDialog;
import phantom.pdbnew.ui.system.UIUtil;
import phantom.pdbnew.ui.system.UIWindow;

import javax.swing.*;

public class SettingsWindow extends UIDialog {
    public SettingsWindow() {
        super(new JDialog());
        initializeLog(getClass());
    }
    @Override
    public void onReceive(String type, Object msg) {}

    @Override
    public void onConstructUI() {
        JTabbedPane tp = new JTabbedPane();
        onConstructTabview(tp);
        self.add(tp);
    }

    @Override
    public void onConstructTabview(JTabbedPane tp) {
        tp.addTab("Settings", UIUtil.newIcon(UIUtil.theme.url_icon_resource_scs), new MainPanel());
        tp.addTab("Themes", UIUtil.newIcon(UIUtil.theme.url_icon_resource_file), new ThemesPanel());
    }
}
