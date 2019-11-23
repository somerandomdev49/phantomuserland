package phantom.pdbnew.ui.system;

import phantom.pdbnew.Receiver;

import javax.swing.*;
import java.awt.*;

public abstract class UI<P extends Component> implements Receiver, UIConstructor {
    public P self;
    protected UI(P self) {
        this.self = self;
    }
}
