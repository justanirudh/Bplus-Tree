import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class Tree {

    private TreeNode root;

    private Tree(int k) {
        this.root = new TreeNode(k ,true);
    }

    static public Tree initialize(int k){
        return new Tree(k);
    }

    public void insert(double key, String value){
        //TODO: implement this
    }

    public List<String> search(double key) {
        //TODO: implement this
        return null;
    }

    public List<Data> search(double start, double finish) {
        //TODO: implement this
        return null;
    }

}
