import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by paanir on 11/16/17.
 */
public class TreeNode {
    private boolean isDataNode; //data node or index node
    private int size; //size of indices/dataList of an indexnode/datanode, <= k-1
    private TreeNode parentNode;
    //for index node
    private List<Double> indices; // size <= k - 1
    private List<TreeNode> children; // size <= k
    //populated for data node
    private List<BEntry> dataList; //size <= k - 1
    private TreeNode prev;
    private TreeNode next;

    public TreeNode(boolean isDataNode) {
        this.isDataNode = isDataNode;
        indices = new ArrayList<>();
        children = new ArrayList<>();
        dataList = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isDataNode() {
        return isDataNode;
    }

    public void setDataNode(boolean dataNode) {
        isDataNode = dataNode;
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

    public void setChild(TreeNode child) {
        List<TreeNode> children = new ArrayList<>();
        children.add(child);
        this.children = children;
    }

    public List<BEntry> getDataList() {
        return dataList;
    }

    public void setDataList(List<BEntry> dataList) {
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

    @Override
    public String toString() {
        return "TreeNode{" +
                "isDataNode=" + isDataNode +
                ", size=" + size +
                ", parentNode=" + parentNode +
                ", indices=" + String.join(",", indices.stream().map(Object::toString).collect(Collectors.toList())) +
                ", dataList=" + String.join(",", dataList.stream().map(BEntry::toString).collect(Collectors.toList())) +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
