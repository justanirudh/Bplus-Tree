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

    /*
    search the data list
     */
    public static int searchDataList(List<BEntry> dataList, double key) {
        return Collections.binarySearch(dataList
                .stream()
                .map(BEntry::getKey)
                .collect(Collectors.toList()), key);
    }

    /*
    insert the key value pair in a key-sorted fashion
    also, insert in the data linked list
    if key already exists, prepend the value to the BEntry's value list
     */
    public static void insertInDataNode(TreeNode dataNode, double key, String value) {
        List<BEntry> dataList = dataNode.getDataList();
        int idx = searchDataList(dataList, key);
        if (idx >= 0) { //key already exists; prepend value to the list of values
            dataList.get(idx).getValues().add(0, value);
        } else {
            int pos = -idx - 1;
            BEntry newEntry = new BEntry(key, value);
            //add to per datanode array
            dataList.add(pos, newEntry);

            //add to Linked List
            BEntry curr = dataList.get(pos);
            if (pos - 1 >= 0 && pos + 1 < dataNode.getSize()) {//added in the middle of list
                BEntry prev = dataList.get(pos - 1);
                BEntry next = dataList.get(pos + 1);
                curr.setPrev(prev);
                curr.setNext(next);
                prev.setNext(curr);
                next.setPrev(curr);
            } else if (pos - 1 >= 0 && pos + 1 == dataNode.getSize()) { //added at the end of a list
                BEntry prev = dataList.get(pos - 1);
                BEntry next = prev.getNext();
                curr.setPrev(prev);
                curr.setNext(next);
                prev.setNext(curr);
                if (next != null)
                    next.setPrev(curr);
            } else if (pos - 1 < 0 && pos + 1 < dataNode.getSize()) { //added in the beginning of the list
                BEntry next = dataList.get(pos + 1);
                BEntry prev = next.getPrev();
                curr.setPrev(prev);
                curr.setNext(next);
                next.setPrev(curr);
                if (prev != null)
                    prev.setNext(curr);
            } else { //first base case
                //NOP
            }

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
    public static TreeNode createIdxNode(TreeNode parentNode, List<Double> indices, List<TreeNode> children) {
        TreeNode in = new TreeNode(false);
        in.setParentNode(parentNode);
        in.setIndices(indices);
        for (TreeNode child : children) {
            child.setParentNode(in);
        }
        in.setChildren(children);
        return in;
    }

    /*
    create a DataNode
     */
    public static TreeNode createDataNode(TreeNode parentNode, List<BEntry> dataList) {
        TreeNode dn = new TreeNode(true);
        dn.setParentNode(parentNode);
        dn.setDataList(dataList);
        return dn;
    }
}
