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

    public void setKey(double key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
