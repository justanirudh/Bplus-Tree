/**
 * Created by paanir on 11/16/17.
 */
public class StringUtils {

    public static boolean isInsert(String s) {
        return s.startsWith("I") && s.contains(",");
    }

    public static BPair getInsertPair(String s) { //Insert(3.55, Value1 )
        String[] pair = ((s.split("\\(")[1]).split("\\)")[0]).split(",");
        double key = Double.parseDouble(pair[0].trim());
        String value = pair[1].trim();
        return new BPair(key, value);
    }

    public static boolean isSearch(String s) {
        return s.startsWith("S") && !s.contains(",");
    }

    public static double getSearchKey(String s) { //Search( 3.55 )
        return Double.parseDouble(((s.split("\\(")[1]).split("\\)")[0]).trim());
    }

    public static boolean isSearchRange(String s) {
        return s.startsWith("S") && s.contains(",");
    }

    public static double[] getSearchRange(String s) {//Search( -3.91, 30.96 )
        String[] pair = ((s.split("\\(")[1]).split("\\)")[0]).split(",");
        double[] keys = new double[2];
        keys[0] = Double.parseDouble(pair[0].trim());
        keys[1] = Double.parseDouble(pair[1].trim());
        if(keys[0] > keys[1])
            throw new InvalidInputException("start of range is greater than end");
        return keys;
    }


}
