import java.util.List;

/**
 * Created by paanir on 11/16/17.
 */
public class TreeNode {
    private boolean isDataNode;
    private int order; //<= k (for root, order >= 2; for internal node, ceil(k/2) <= order )
    private TreeNode parentNode;
    //for index node
    private List<Double> indices; // size <= order - 1
    private List<TreeNode> children; // size <= order
    //populated for data node
    private List<Data> dataList; //size <= order - 1
    private TreeNode prev;
    private TreeNode next;

    public TreeNode(int k, boolean isDataNode) {
        this.isDataNode = isDataNode;
        this.order = k;
    }

    public boolean isDataNode() {
        return isDataNode;
    }

    public void setDataNode(boolean dataNode) {
        isDataNode = dataNode;
    }

    public int getOrder() {
        return order;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public List<Double> getIndices() {
        return indices;
    }

    public void setIndices(List<Double> indices) {
        this.indices = indices;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public TreeNode getPrev() {
        return prev;
    }

    public void setPrev(TreeNode prev) {
        this.prev = prev;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }
}
