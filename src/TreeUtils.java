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
        //TODO: Implement this
        return null;
    }

    /*
    create a DataNode
     */
    public static TreeNode createDataNode(int size, TreeNode parentNode, List<BEntry> dataList, TreeNode prev, TreeNode next) {
        //TODO: Implement this
        return null;
    }

    /*
    merge function (key, TreeNode, parentNode)
    return void
    -  check parent is null or not
    if parent is non-null
    -  add key sortedly to indexlist at index i
    -  add TreeNode to children at index i + 1
    -  check for overfull
    -  if not overfull, we are done
    -  if overfull,
    -  split indexList in half
    -  split children into half (make sure it is in line with cut at indexList - odd/even lengths etc)
    -  Keep lower indexList and lower children in current node
    -  remove first element of indexList. That would be the new key. make a TreeNode (IndexNode)
     with the leftover indexList and the upper children as children
    - pass key, TreeNode and parent node recursively
    if parent is null
    - Make new root which will be index node
    -- indexList = only 1 element which is key
    -- children = prepend the current root to the children list
    TADAAAA
     */

    public static void merge(TreeNode treeNode, TreeNode newNode) {
        //TODO: Implement this
    }
}
