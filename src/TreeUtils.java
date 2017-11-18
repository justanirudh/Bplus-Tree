import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by paanir on 11/16/17.
 */
public class TreeUtils {

    /*
    if key is in indices, it returns that idx
    if key is not in indices, it returns the idx of the largest element smaller than key
     */
    public static int searchIdxList(List<Double> indices, double key) {
        int idx = Collections.binarySearch(indices, key);
        if (idx >= 0)
            return idx;
        else
            return -idx - 2;
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
        int idx = searchDataList(dataList, key);
        if (idx >= 0) { //key already exists; prepend value to the list of values
            dataList.get(idx).getValues().add(0, value);
        } else {
            int pos = -idx - 1;
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
            int idx = TreeUtils.searchIdxList(currNode.getIndices(), key);
            return searchForDataNode(key, currNode.getChildren().get(idx + 1));
        }
    }

    /*
    create a IdxNode
     */
    public static TreeNode createIdxNode(TreeNode parentNode, double idx, List<TreeNode> children) {
        //TODO: Remove children, parentNode as always null for a new idx node?
        List<Double> indices = new ArrayList<>();
        indices.add(idx);
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
