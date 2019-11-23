package phantom.pdbnew.ui.system;

import javax.swing.*;
import java.awt.*;

public abstract class ThemedUI<P extends Component> extends LoggerUI<P> {
    public ThemedUI(P self) {
        super(self);
        self.setBackground(UIUtil.theme.color_main_bg);
        self.setForeground(UIUtil.theme.color_main_fg);
    }
}
