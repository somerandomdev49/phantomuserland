package phantom.pdbnew;

import phantom.pdbnew.pdb.Debugger;
import phantom.pdbnew.ui.DebuggerWindow;
import phantom.pdbnew.ui.PObjectPanel;

public class UITransmitter {
    private Debugger db;
    private DebuggerWindow dw;
    private PObjectPanel op;
    public UITransmitter(Debugger db, DebuggerWindow dw, PObjectPanel op) {
        this.db = db;
        this.dw = dw;
        this.op = op;
    }

    public void send(String type, Object msg) {
        this.db.onReceive(type, msg);
        this.dw.onReceive(type, msg);
        this.op.onReceive(type, msg);
    }

    public Debugger getDb() {
        return db;
    }
}
