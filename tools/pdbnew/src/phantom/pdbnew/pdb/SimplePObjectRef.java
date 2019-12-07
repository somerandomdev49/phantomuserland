package phantom.pdbnew.pdb;

public class SimplePObjectRef {
    public long addr;
    public SimplePObject object;
    public int id;

    public SimplePObjectRef(int id, SimplePObject object, long addr) {
        this.object = object;
        this.addr = addr;
        this.id = id;
    }
}
