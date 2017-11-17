import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class Tree {

    private TreeNode root;
    private int k; //order or maximum number of children a node can have

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
    TADAAAA!
     */

    private void merge(TreeNode treeNode, TreeNode newNode) {
        //new node is an index node with a singleton index list and children list (that can have indexnodes or datanodes)
        //treeNode can be either datanode or index node
        if (treeNode == null) {
            /*
            1. Prepend children list of newNode with treeNode
            2. make newNode as the new root
             */
            newNode.getChildren().add(0, root);
            root = newNode;
        } else {
            //TODO: Implement this
        }
    }

    public Tree(int k) {
        //first node formed is a data node
        this.root = new TreeNode(true);
        this.k = k;
    }

    //TODO: Add doubly linked list functionality in all data nodes

    public void insert(double key, String value) {
        TreeNode dataNode = TreeUtils.searchBestNode(key, root);
        TreeUtils.insertInNode(dataNode, key, value); //just insert
        if (dataNode.getSize() == k) { //overfull node
            /*
            1. split dataList into roughly 2 equal parts
            2. create 2 datanodes D1 and D2 with lower part and upper part of dataList
            3. Replace current node with D1. Also change its size
            4. Pass D2 as TreeNode (DataNode),key (which is the first elem of D2.datalist), parent node -> merge fn
             */
            //split list into 2 lists
            List<BEntry> entries = dataNode.getDataList();
            List<BEntry> head = entries.subList(0, entries.size() / 2);
            List<BEntry> tail = entries.subList(entries.size() / 2, entries.size());
            //change current datanode with smaller list of entries
            dataNode.setDataList(head);
            dataNode.setSize(head.size());
            //create D2 TreeNode (Index Node) that has a child (data node)
            TreeNode newIndexNode = TreeUtils.createIndexNode(1, null, tail.get(0).getKey(), null);
            TreeNode newDataNode = TreeUtils.createDataNode(tail.size(), newIndexNode, tail, null, null);
            newIndexNode.setChild(newDataNode); //set index node's child to be the data node
            merge(newIndexNode, dataNode.getParentNode());
        }
    }

    public List<String> search(double key) {
        //TODO: implement this
        return null;
    }

    public List<BEntry> search(double start, double finish) {
        //TODO: implement this
        return null;
    }

}
