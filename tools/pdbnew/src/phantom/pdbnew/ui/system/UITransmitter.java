package phantom.pdbnew.ui.system;

import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.app.MainWindow;

import java.util.ArrayList;

public class UITransmitter extends Transmitter {
    //private Debugger db;       \
    //private DebuggerWindow dw;  |> this was before Transmitter.
    //private PObjectPanel op;   /
    public UITransmitter(Debugger db, MainWindow mw) {
        subscribed = new ArrayList<>();
        subscribed.add(db);
        subscribed.add(mw);
    }

    public Debugger getDebugger() {
        return (Debugger) subscribed.get(0);
    }
    public MainWindow getMainWindow() {
        return (MainWindow) subscribed.get(1);
    }
}
