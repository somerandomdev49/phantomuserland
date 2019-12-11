package phantom.pdbnew.pdb;

import phantom.data.ObjectHeader;
import phantom.data.ObjectRef;
import phantom.pdbnew.ui.notification.NotificationType;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SimplePObject {
    public ObjectHeader h;
    public SimplePObjectRef parentObject;
    public ArrayList<SimplePObjectRef> links;
    public long addr;
    public SimplePObject(long addr, ObjectHeader h, SimplePObjectRef parentObject) {
        this.addr = addr;
        this.h = h;
        this.links = new ArrayList<>();
        this.parentObject = parentObject;
    }
    public SimplePObject(long addr, ObjectHeader h) {
        this(addr, h, null);
    }

    /// Don't really need that...
    //public SimplePObject clone() {
    //   return new SimplePObject(addr, h, parentObject); // too lazy...
    //}

    public SimplePObjectRef getClassReference() {
        return new SimplePObjectRef(-1, null, h.getClassRef().getDataAddr()-0x80000000);
    }

    public String getClassName(Debugger d) {
        String s = "<ERROR>";
        SimplePObjectRef cr = getClassReference();
        SimplePObject o = d.dereferenceSimpleObject(cr);
        ByteBuffer cda = o.h.getDataArea();
        d.uit.getMainWindow().notifyMessage("cda size = " + cda.capacity(), NotificationType.INFO);
        try {
            cda.getInt(); // object_flags
            cda.getInt(); // object_data_area_size
            new ObjectRef(cda); // object_default_interface
            cda.getInt(); // sys_table_id
            ObjectRef class_name_ref = new ObjectRef(cda); // class_name
            SimplePObject class_name = d.dereferenceSimpleObject(new SimplePObjectRef(-1, null, class_name_ref.getDataAddr() - 0x80000000));
            ByteBuffer nda = class_name.h.getDataArea();
            int length = nda.getInt();
            byte[] string = new byte[length];
            for (int i = 0; i < length; i++) {
                string[i] = nda.get();
            }
            s = new String(string);
        } catch(Exception e) {

        }
        return s;
    }
}
