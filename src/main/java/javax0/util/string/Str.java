package javax0.util.string;

public class Str {

    final String string;

    private Str(String string) {
        this.string = string;
    }

    public static Str str(String string) {
        return new Str(string);
    }

    public String sub(int start, int end) {
        return sub(string, start, end);
    }

    public static String sub(String s, int start, int end) {
        if (s == null) {
            return "";
        }
        if (end < 0) {
            end = s.length() - end;
        }
        if (end < 0) {
            end = 0;
        }
        if (start > end) {
            int swap = start;
            start = end;
            end = swap;
        }
        if (s.length() < end) {
            end = s.length();
        }
        if (start < 0) {
            start = 0;
        }
        return s.substring(start, end);
    }


}
