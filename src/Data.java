import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class Data {
    private double key;
    private List<String> values;

    public Data(double key) {
        this.key = key;
        this.values = new ArrayList<>();
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
