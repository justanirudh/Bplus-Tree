import java.util.ArrayList;
import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class Tree {
    //TODO: Add doubly linked list functionality in all data nodes

    private TreeNode root;
    private int k; //order or maximum number of children a node can have

    /*
    merge function (key, TreeNode, parentNode)
    return void
    -  check parent is null or not
    if parent is non-null
    -  add key sortedly to idxlist at idx i
    -  add TreeNode to children at idx i + 1
    -  check for overfull
    -  if not overfull, we are done
    -  if overfull,
    -  split idxList in half
    -  split children into half (make sure it is in line with cut at idxList - odd/even lengths etc)
    -  Keep lower idxList and lower children in current node
    -  remove first element of idxList. That would be the new key. make a TreeNode (IdxNode)
     with the leftover idxList and the upper children as children
    - pass key, TreeNode and parent node recursively
    if parent is null
    - Make new root which will be idx node
    -- idxList = only 1 element which is key
    -- children = prepend the current root to the children list
    TADAAAA!
     */
    private void merge(TreeNode treeIdxNode, TreeNode newIdxNode) {
        //new node is an idx node with a singleton idx list and children list (that can have idxnodes or datanodes)
        //treeNode can be either datanode or idx node
        if (treeIdxNode == null) {
            /*
            - Prepend treeNode to children list of newNode
            - Set parent of old root to new node
            - make newNode as the new root
             */
            System.out.println("parentNode is null");
            newIdxNode.getChildren().add(0, root);
            root.setParentNode(newIdxNode);
            root = newIdxNode;
        } else {

        }
    }

    public Tree(int k) {
        //first node formed is a data node
        this.root = new TreeNode(true);
        this.k = k;
    }

    public void insert(double key, String value) {
        TreeNode dataNode = TreeUtils.searchForDataNode(key, root);
        System.out.println("Before insertion: " + dataNode.toString());
        TreeUtils.insertInDataNode(dataNode, key, value);
        if (dataNode.getSize() == k) { //overfull node
            /*
            1. split dataList into roughly 2 equal parts
            2. create 2 datanodes D1 and D2 with lower part and upper part of dataList
            3. Replace current node with D1. Also change its size
            4. Pass D2 as TreeNode (DataNode),key (which is the first elem of D2.datalist), parent node -> merge fn
             */
            System.out.println("Overfull node");

            //split list into 2 roughly equal lists
            List<BEntry> entries = dataNode.getDataList();
            List<BEntry> head = new ArrayList<>(entries.subList(0, entries.size() / 2));
            List<BEntry> tail = new ArrayList<>(entries.subList(entries.size() / 2, entries.size()));

            //change current datanode with smaller list of entries
            dataNode.setDataList(head);

            //create a new TreeNode (Idx Node) that has a child (data node)
            //newIdxNode always will have singleton indices list and singleton children list
            TreeNode newIdxNode = TreeUtils.createIdxNode(null, tail.get(0).getKey(), null);
            TreeNode newDataNode = TreeUtils.createDataNode(newIdxNode, tail, null, null);
            newIdxNode.setChild(newDataNode); //set idx node's child to be the data node

            //merge with parent
            merge(dataNode.getParentNode(), newIdxNode);
        }
    }

    public List<String> search(double key) {
        TreeNode dataNode = TreeUtils.searchForDataNode(key, root);
        List<BEntry> dataList = dataNode.getDataList();
        int idx = TreeUtils.searchDataList(dataList, key);
        if (idx >= 0)
            return dataList.get(idx).getValues();
        else {
            List<String> list = new ArrayList<>();
            list.add("Null");
            return list;
        }
    }

    public List<BEntry> search(double start, double finish) {
        //TODO: implement this
        return null;
    }

}
