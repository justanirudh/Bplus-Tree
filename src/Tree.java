import java.util.ArrayList;
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
    private void merge(TreeNode treeIdxNode, TreeNode loneIdxNode) {
        //new node is an idx node with a singleton idx list and children list (that can have idxnodes or datanodes)
        //treeNode can be either datanode or idx node
        if (treeIdxNode == null) {
            /*
            - Prepend treeNode to children list of newNode
            - Set parent of old root to new node
            - make newNode as the new root
             */
//            System.out.println("parentNode is null");
            loneIdxNode.getChildren().add(0, root);
            root.setParentNode(loneIdxNode);
            root = loneIdxNode;
//            System.out.println("root: " + root);
//            System.out.println("Children of root: ");
//            for (TreeNode tn : root.getChildren()) {
//                System.out.println(tn);
//                System.out.println("Its children");
//                for (TreeNode tnn : tn.getChildren()) {
//                    System.out.print(tnn + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        } else {

//            System.out.println("parent: " + treeIdxNode);

            //get the idx and the child from new node
            double key = loneIdxNode.getIndices().get(0);
            TreeNode child = loneIdxNode.getChildren().get(0);

            //make changes to new parent: search the correct idx to add idx and child
            int idxIdx = TreeUtils.searchIdxList(treeIdxNode.getIndices(), key) + 1;
            treeIdxNode.getIndices().add(idxIdx, key);
            int idxData = idxIdx + 1;
            treeIdxNode.getChildren().add(idxData, child);

            //make changes to child: set new parent for the child
            child.setParentNode(treeIdxNode);

//            see if overfull
            if (treeIdxNode.getSize() == k) {

                //split indexlist and children list
                List<Double> leftIdxList = new ArrayList<>(treeIdxNode.getIndices().subList(0, k / 2));
                List<Double> rightIdxList = new ArrayList<>(treeIdxNode.getIndices().subList(k / 2, k));

                List<TreeNode> leftChildren;
                List<TreeNode> rightChildren;

                if (k % 2 == 1) { //k is odd
                    leftChildren = new ArrayList<>(treeIdxNode.getChildren().subList(0, (k + 1) / 2));
                    rightChildren = new ArrayList<>(treeIdxNode.getChildren().subList((k + 1) / 2, k + 1));
                } else {
                    leftChildren = new ArrayList<>(treeIdxNode.getChildren().subList(0, (k + 2) / 2));
                    rightChildren = new ArrayList<>(treeIdxNode.getChildren().subList((k + 2) / 2, k + 1));
                }

                //fix current treenode
                treeIdxNode.setIndices(leftIdxList);
                treeIdxNode.setChildren(leftChildren);

                //create new index node
                List<Double> parentIdx = new ArrayList<>();
                parentIdx.add(rightIdxList.get(0));
                TreeNode newIdxNode = TreeUtils.createIdxNode(null, parentIdx, new ArrayList<>());
                rightIdxList.remove(0); //remove first index as that is now parent
                TreeNode newChildNode = TreeUtils.createIdxNode(newIdxNode, rightIdxList, rightChildren);

                //set child
                newIdxNode.setChild(newChildNode); //set idx node's child to be the child idx node

                //merge with parent
                merge(treeIdxNode.getParentNode(), newIdxNode);
            }

//            System.out.println("After insertion: " + treeIdxNode);
//            System.out.println("Children of old node");
//            for (TreeNode tn : treeIdxNode.getChildren()) {
//                System.out.print(tn + " ");
//            }
//            System.out.println();
        }
    }

    public Tree(int k) {
        //first node formed is a data node
        this.root = new TreeNode(true);
        this.k = k;
    }

    public void insert(double key, String value) {
        TreeNode dataNode = TreeUtils.searchForDataNode(key, root);
//        System.out.println("Before insertion: " + dataNode.toString());
        TreeUtils.insertInDataNode(dataNode, key, value);
        if (dataNode.getSize() == k) { //overfull node
            /*
            1. split dataList into roughly 2 equal parts
            2. create 2 datanodes D1 and D2 with lower part and upper part of dataList
            3. Replace current node with D1. Also change its size
            4. Pass D2 as TreeNode (DataNode),key (which is the first elem of D2.datalist), parent node -> merge fn
             */
//            System.out.println("Overfull node");

            //split list into 2 roughly equal lists
            List<BEntry> dataList = dataNode.getDataList();
            List<BEntry> leftDataList = new ArrayList<>(dataList.subList(0, k / 2));
            List<BEntry> rightDataList = new ArrayList<>(dataList.subList(k / 2, k));

            //change current datanode with smaller list of entries
            dataNode.setDataList(leftDataList);

            //create a new TreeNode (Idx Node) that has a child (data node)
            //newIdxNode always will have singleton indices list and singleton children list
            List<Double> parentIdx = new ArrayList<>();
            parentIdx.add(rightDataList.get(0).getKey());
            TreeNode newIdxNode = TreeUtils.createIdxNode(null, parentIdx, new ArrayList<>());
            TreeNode newDataNode = TreeUtils.createDataNode(newIdxNode, rightDataList, null, null);
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
        /*
        - go to the best node for insertion of start
         - go the the key itself or the smallest node greater than key
         - traverse in LL until you either reach end or the number exceeds finish
         */
        TreeNode dataNode = TreeUtils.searchForDataNode(start, root);
        List<BEntry> dataList = dataNode.getDataList();
        int idx = TreeUtils.searchDataList(dataList, start);

        List<BEntry> res = new ArrayList<>();
        BEntry currEntry;

        if (idx >= 0) //start exists
            currEntry = dataList.get(idx);
        else {
            idx = -idx - 1;
            if (idx >= 0 && idx < dataNode.getSize()) //next bigger
                currEntry = dataList.get(idx);
            else//go to smallest in the next node's list
                currEntry = dataList.get(idx - 1).getNext();
        }

        //find all
        while (currEntry != null && currEntry.getKey() <= finish) {
            res.add(currEntry);
            currEntry = currEntry.getNext();
        }

        return res;
    }

}
