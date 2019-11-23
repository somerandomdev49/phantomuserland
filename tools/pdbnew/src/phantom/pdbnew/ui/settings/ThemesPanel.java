package phantom.pdbnew.ui.settings;

import phantom.pdbnew.ui.system.UIConstructor;
import phantom.pdbnew.ui.system.UITheme;

import javax.swing.*;

public class ThemesPanel extends JPanel implements UIConstructor {
    public JList<UITheme> themeList;
    public ThemesPanel() {
        super();
        themeList = new JList<>();
        onConstructUI();
    }
    @Override
    public void onConstructUI() {
        add(themeList);
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
