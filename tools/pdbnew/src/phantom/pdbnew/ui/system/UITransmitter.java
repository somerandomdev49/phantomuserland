package phantom.pdbnew.ui.system;

import phantom.pdbnew.Receiver;
import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.DebuggerWindow;
import phantom.pdbnew.ui.PObjectPanel;

import java.awt.*;
import java.util.ArrayList;

public class UITransmitter extends Transmitter {
    //private Debugger db;       \
    //private DebuggerWindow dw;  |> this was before Trasmitter.
    //private PObjectPanel op;   /
    public UITransmitter(Debugger db, DebuggerWindow dw, PObjectPanel op) {
        subscribed = new ArrayList<>();
        subscribed.add(db);
        subscribed.add(dw);
        subscribed.add(op);
    }

    public Debugger getDb() {
        return (Debugger) subscribed.get(0);
    }
}
