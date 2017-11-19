/**
 * Created by paanir on 11/16/17.
 */
public class BPair {
    private double key;
    private String value;

    public BPair(double key, String value) {
        this.key = key;
        this.value = value;
    }

    public double getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BPair{" +
                "key=" + key +
                ", value=" + value  +
                '}';
    }
}
