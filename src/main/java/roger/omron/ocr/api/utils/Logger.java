package roger.omron.ocr.api.utils;

public class Logger {
    public static void debug(String label, String msg){
        System.out.println("debug [DEBUG]: " + label + " -> " + msg);
    }

    public static void info(String msg){
        System.out.println("debug [DEBUG]: " + msg);
    }
}
