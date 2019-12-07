package phantom.pdbnew.pdb;
import phantom.data.DataLoadException;
import phantom.data.ObjectHeader;
import phantom.data.ObjectRef;
import phantom.pdbnew.Debug;
import phantom.pdbnew.Receiver;
import phantom.pdbnew.ui.system.UITransmitter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;


public class Debugger implements Receiver {
    private ByteBuffer memory;
    public UITransmitter uit;
    public Debugger(ByteBuffer m) {
        memory = m;
    }


    public void load() {
//        memory.order(ByteOrder.LITTLE_ENDIAN);
//        System.out.println("memory size: " + memory.remaining());
//        //ObjectRef h = new ObjectRef(memory);
//        //System.out.println((h.getDataAddr() - 800000000) / 8);
//        //ObjectRef f = new ObjectRef(memory);
//        //System.out.println(f.getInterfaceAddr());
//        ObjectHeader m = new ObjectHeader();
//        //memory.get((int) ((h.getDataAddr() - 800000000)/8));
//        try {
//            m.loadHeader(memory);
//            System.out.println("=" + m.getObjectFlags());
//            uit.send("start-success", null);
//            doStuff(m);
//        } catch (DataLoadException e) {
//            e.printStackTrace();
//        }
        memory.order(ByteOrder.LITTLE_ENDIAN);
    }

    private class ProcessedObject {
        public ObjectHeader h;
        public long addr;

        public ProcessedObject(ObjectHeader h, long addr) {
            this.h = h;
            // cool pattern.
            // <|>->>{|x|}<<-<|>->>{|x|}<<-<|>->>{|x|}<<-<|>->>{|x|}<<-<|>->>{|x|}<<-
            this.addr = addr;
        }
    }

    private ProcessedObject processObject(ByteBuffer b) throws DataLoadException {
        ObjectHeader h = new ObjectHeader(); // resulting header.
        ObjectRef r = new ObjectRef(b); // reference to object.
        b.order(ByteOrder.LITTLE_ENDIAN);
        // System.out.println("H: " + (int)(r.getDataAddr()-0x80000000));
        int rawDataAddr = (int) r.getDataAddr();
        if(rawDataAddr==0)Debug.log("Data address of field is all 0s!");
        else h.loadHeader(memory.position(rawDataAddr-0x80000000));
        return new ProcessedObject(h, rawDataAddr-0x80000000);
    }

    private SimplePObject simplify(ObjectHeader h) {
        SimplePObject s = new SimplePObject(0,null);
        int fieldAmount = h.getDaSize() / 4;
        Debug.log("Fields to process: " + fieldAmount);
        ByteBuffer b = h.getDataArea();
        b.order(ByteOrder.LITTLE_ENDIAN);
        //ArrayList<ProcessedObject> fields = new ArrayList<>();
        for(int i=0;i<fieldAmount;i++) {
            Debug.log("Processing field #"+i+"!");
            try {
                ProcessedObject o = processObject(b);
                s.links.add(new SimplePObjectRef(i,new SimplePObject(o.addr,o.h), o.addr));
            } catch(Exception e) {
                Debug.log("An error occurred while processing field #" + i + ":");
                Debug.log(e.getMessage(),1);
            }
        }

        return s;
    }

    private void doStuff(ObjectHeader m) throws DataLoadException {
        // TODO: Stuff...
//        int fieldAmount = m.getDaSize() / 4;
//        Debug.log("Fields to process: " + fieldAmount);
//        ByteBuffer b = m.getDataArea();
//        b.order(ByteOrder.LITTLE_ENDIAN);
//        ArrayList<ObjectHeader> fields = new ArrayList<>();
//        for(int i=0;i<fieldAmount;i++) {
//            Debug.log("Processing field #"+i+"!");
//            try {
//                fields.add(processObject(b));
//            } catch(Exception e) {
//                Debug.log("An error occurred while processing field #" + i + ":");
//                Debug.log(e.getMessage(),1);
//            }
//        }
        simplify(m);
    }

    @Deprecated
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

    public SimplePObject dereferenceSimpleObject(SimplePObject o, int linkN) {
        if(o==null) {
            ObjectHeader m = new ObjectHeader();
            try {
                m.loadHeader(memory);
                return simplify(m);
            } catch (DataLoadException e) {
                e.printStackTrace();
            }
        } else {
            SimplePObjectRef ref = o.links.get(linkN);
            if (ref.object == null) {
                ObjectHeader m = new ObjectHeader();
                // there was s thing where i forgot to
                // add 0x before, so nothing was working :D
                try {
                    m.loadHeader(memory.position((int) ref.addr));
                    //uit.send("start-success", null);
                    return simplify(m);
                } catch (DataLoadException e) {
                    e.printStackTrace();
                }
            }
            return ref.object;
        }
        return null;
    }
}
