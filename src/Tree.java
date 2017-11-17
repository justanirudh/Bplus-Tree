import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class Tree {

    private TreeNode root;
    private int k; //order or maximum number of children a node can hav

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
            newIndexNode.setChild(newDataNode);
            TreeUtils.merge(newIndexNode, dataNode.getParentNode());
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
