package utils;

/**
 * Created by paanir on 11/16/17.
 */
public class StringUtils {

    public static boolean isInsert(String s) {
        return s.startsWith("I") && s.contains(",");
    }

    public static boolean isSearch(String s) {
        return s.startsWith("S") && !s.contains(",");
    }

    public static boolean isSearchRange(String s) {
        return s.startsWith("S") && s.contains(",");
    }


}
