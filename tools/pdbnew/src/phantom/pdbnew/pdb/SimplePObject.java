package phantom.pdbnew.pdb;

import phantom.data.ObjectHeader;
import phantom.data.ObjectRef;

import java.util.ArrayList;

public class SimplePObject {
    public int id;
    public ObjectHeader oh;

    public SimplePObject(int id, ObjectHeader oh) {
        this.id = id;
        this.oh = oh;
    }

    public ArrayList<SimplePObjectField> getFields() {
        ArrayList<SimplePObjectField> fields = new ArrayList<>();
        //for(oh.)
        return fields;
    }
}
