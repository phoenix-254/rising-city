package pojo;

public class RedBlackNode extends Building {
    private NodeColor color;

    private HeapNode heapNodeReference;

    private RedBlackNode left, right, parent;

    public RedBlackNode(int buildingNumber, int executedTime, int totalTime) {
        super(buildingNumber, executedTime, totalTime);
        this.setColor(NodeColor.RED);
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public HeapNode getHeapNodeReference() {
        return heapNodeReference;
    }

    public void setHeapNodeReference(HeapNode heapNodeReference) {
        this.heapNodeReference = heapNodeReference;
    }

    public RedBlackNode getLeft() {
        return left;
    }

    public void setLeft(RedBlackNode left) {
        this.left = left;
    }

    public RedBlackNode getRight() {
        return right;
    }

    public void setRight(RedBlackNode right) {
        this.right = right;
    }

    public RedBlackNode getParent() {
        return parent;
    }

    public void setParent(RedBlackNode parent) {
        this.parent = parent;
    }
}