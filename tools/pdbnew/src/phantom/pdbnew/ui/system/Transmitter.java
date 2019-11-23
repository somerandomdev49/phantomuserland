package phantom.pdbnew.ui.system;

import phantom.pdbnew.Receiver;

import java.util.ArrayList;

public class Transmitter {
    protected ArrayList<Receiver> subscribed;
    public void send(String type, Object msg) {
        for(Receiver r : subscribed) {
            r.onReceive(type, msg);
        }
    }
}
