//package utils;/

/**
 * Created by paanir on 11/16/17.
 */
public class StringUtils {

    public static boolean isInsert(String s) {
        return s.startsWith("I") && s.contains(",");
    }

    public static BPair getInsertPair(String s) { //Insert(3.55, Value1 )
        //TODO: implement this
        return null;

    }

    public static boolean isSearch(String s) {
        return s.startsWith("S") && !s.contains(",");
    }

    public static double getSearchKey(String s) {
        //TODO: implement this
        return 0;
    }

    public static boolean isSearchRange(String s) {
        return s.startsWith("S") && s.contains(",");
    }

    public static double[] getSearchRange(String s) {
        //TODO: implement this
        return null;
    }


}
