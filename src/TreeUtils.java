import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by paanir on 11/16/17.
 */
public class TreeUtils {

    /*
    if key is in indices, it returns that index
    if key is not in indices, it returns the index of the largest element smaller than key
     */
    private static int searchIndexList(List<Double> indices, double key) {
        int index = Collections.binarySearch(indices, key);
        if (index >= 0)
            return index;
        else
            return -index - 2;
    }

    public static int searchDataList(List<BEntry> dataList, double key) {
        return Collections.binarySearch(dataList
                .stream()
                .map(BEntry::getKey)
                .collect(Collectors.toList()), key);
    }

    /*
    insert the key value pair in a key-sorted fashion
    if key already exists, prepend the value to the BEntry's value list
     */
    public static void insertInDataNode(TreeNode dataNode, double key, String value) {
        List<BEntry> dataList = dataNode.getDataList();
        int index = searchDataList(dataList, key);
        if (index >= 0) { //key already exists; prepend value to the list of values
            dataList.get(index).getValues().add(0, value);
        } else {
            int pos = -index - 1;
            dataList.add(pos, new BEntry(key, value));
        }
    }

    /*
    Searches for the key
    returns a node that either has the key or should have the key
     */
    public static TreeNode searchForDataNode(double key, TreeNode currNode) {
        if (currNode.isDataNode())
            return currNode;
        else {
            int index = TreeUtils.searchIndexList(currNode.getIndices(), key);
            return searchForDataNode(key, currNode.getChildren().get(index + 1));
        }
    }

    /*
    create a IndexNode
     */
    public static TreeNode createIndexNode(TreeNode parentNode, double index, List<TreeNode> children) {
        //TODO: Remove children, parentNode as always null for a new index node?
        List<Double> indices = new ArrayList<>();
        indices.add(index);
        TreeNode in = new TreeNode(false);
        in.setIndices(indices);
        in.setChildren(children);
        return in;
    }

    /*
    create a DataNode
     */
    public static TreeNode createDataNode(TreeNode parentNode, List<BEntry> dataList, TreeNode prev, TreeNode next) {
        //TODO: Remove prev, next as always null?
        TreeNode dn = new TreeNode(true);
        dn.setParentNode(parentNode);
        dn.setDataList(dataList);
        dn.setPrev(prev);
        dn.setNext(next);
        return dn;
    }
}
