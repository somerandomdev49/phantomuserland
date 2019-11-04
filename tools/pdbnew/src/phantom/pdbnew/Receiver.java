package phantom.pdbnew;

public interface Receiver {
    void onReceive(String type, Object msg);
}
