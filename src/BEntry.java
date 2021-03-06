import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class BEntry {
    private double key;
    private List<String> values;
    private BEntry next;
    private BEntry prev;

    public BEntry(double key, String value) {
        this.key = key;
        List<String> values = new ArrayList<>();
        values.add(value);
        this.values = values;
    }

    public BEntry getPrev() {
        return prev;
    }

    public void setPrev(BEntry prev) {
        this.prev = prev;
    }

    public BEntry getNext() {
        return next;
    }

    public void setNext(BEntry next) {
        this.next = next;
    }

    public double getKey() {
        return key;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        List<String> l = new ArrayList<>();
        for (String value : values) {
            l.add("(" + key + "," + value + ")");
        }
        return String.join(",", l);
    }
}
