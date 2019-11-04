package phantom.pdbnew.pdb;
import phantom.data.DataLoadException;
import phantom.data.ObjectFlags;
import phantom.data.ObjectHeader;
import phantom.data.ObjectRef;
import phantom.pdbnew.Receiver;
import phantom.pdbnew.UITransmitter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;


public class Debugger implements Receiver {
    private ByteBuffer memory;
    public UITransmitter uit;
    public Debugger(ByteBuffer m) {
        memory = m;
    }

    public void load() {
        memory.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println("memory size: " + memory.remaining());
        //ObjectRef h = new ObjectRef(memory);
        //System.out.println((h.getDataAddr() - 800000000) / 8);
        //ObjectRef f = new ObjectRef(memory);
        //System.out.println(f.getInterfaceAddr());
        ObjectHeader m = new ObjectHeader();
        //memory.get((int) ((h.getDataAddr() - 800000000)/8));
        try {
            m.loadHeader(memory);
            uit.send("start-success", null);
            doStuff();
        } catch (DataLoadException e) {
            e.printStackTrace();
        }

    }

    private void doStuff() {
        // TODO: Do stuff...
    }

    private ArrayList<ObjectRef> getFields() {
        ArrayList<ObjectRef> lst = new ArrayList<>();

        return lst;
    }

    public void cleanup() {
        memory.clear();
    }

    @Override
    public void onReceive(String type, Object msg) {

    }
}
