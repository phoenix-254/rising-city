package pojo;

enum NodeColor {
    RED,
    BLACK
}

public class RedBlackNode extends Building {
    private NodeColor color;

    private HeapNode heapNodeReference;

    private RedBlackNode left, right, parent;

    public RedBlackNode(int buildingNumber, int executedTime, int totalTime) {
        super(buildingNumber, executedTime, totalTime);
        this.color = NodeColor.RED;
    }

    public HeapNode getHeapNodeReference() {
        return heapNodeReference;
    }

    public void setHeapNodeReference(HeapNode heapNodeReference) {
        this.heapNodeReference = heapNodeReference;
    }

    public RedBlackNode getParent() {
        return parent;
    }

    public void setParent(RedBlackNode parent) {
        this.parent = parent;
    }
}