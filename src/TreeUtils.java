import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class TreeUtils {

    /*
    if key is in indices, it returns that index
    if key is not in indices, it returns the index of the largest element smaller than key
     */
    private static int searchIndexList(List<Double> indices, double key) {
        //TODO: Implement this
        return -1;
    }

    /*
    insert the key value pair in a key-sorted fashion
    if key already exists, prepend the value to the BEntry's value list
     */
    public static void insertInNode(TreeNode dataNode, double key, String value) {
        //TODO: Implement this
    }

    /*
    Searches for the key
    returns a node that either has the key or should have the key
     */
    public static TreeNode searchBestNode(double key, TreeNode currNode) {
        if (currNode.isDataNode())
            return currNode;
        else {
            int index = TreeUtils.searchIndexList(currNode.getIndices(), key);
            return searchBestNode(key, currNode.getChildren().get(index + 1));
        }
    }

    /*
    create a IndexNode
     */
    public static TreeNode createIndexNode(int size, TreeNode parentNode, double index, List<TreeNode> children) {
        //TODO: Remove children, parentNode as always null for a new index node?
        List<Double> indices = new ArrayList<>();
        indices.add(index);
        TreeNode in = new TreeNode(false);
        in.setSize(size);
        in.setIndices(indices);
        in.setChildren(children);
        return in;
    }

    /*
    create a DataNode
     */
    public static TreeNode createDataNode(int size, TreeNode parentNode, List<BEntry> dataList, TreeNode prev, TreeNode next) {
        //TODO: Remove prev, next as always null?
        TreeNode dn = new TreeNode(true);
        dn.setSize(size);
        dn.setParentNode(parentNode);
        dn.setDataList(dataList);
        dn.setPrev(prev);
        dn.setNext(next);
        return dn;
    }
}