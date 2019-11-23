package phantom.pdbnew;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Debug {
    private Debug() {}
    public static String getCurrentDateAndTimeString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return(dtf.format(now));
    }

    public static void log(Object s, int i) {
        System.out.println( new String(new char[]{'\t'},0,i) + "[" + getCurrentDateAndTimeString() + "]: " + s);
    }
    public static void log(Object s) {
        log(s, 0);
    }
}
