package phantom.pdbnew;

import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.DebuggerWindow;

public class UITransmitter {
    public Debugger db;
    public DebuggerWindow dw;
    public UITransmitter(Debugger db, DebuggerWindow dw) {
        this.db = db;
        this.dw = dw;
    }

    public void send(String type, Object msg) {
        this.db.onReceive(type, msg);
        this.dw.onReceive(type, msg);
    }
}
