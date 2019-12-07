package phantom.pdbnew.pdb;

import phantom.data.ObjectHeader;

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
}
