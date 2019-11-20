package pojo;

public class RedBlackNode extends Building {
    // Null external black node.
    public static final RedBlackNode NIL = new RedBlackNode(-1, -1, -1);

    // Left, Right and Parent pointers.
    private RedBlackNode left, right, parent;

    // Color of the node. Red or Black.
    private NodeColor color;

    // Corresponding heap node reference.
    private HeapNode heapNodeReference;

    public RedBlackNode(int buildingNumber, int executedTime, int totalTime) {
        super(buildingNumber, executedTime, totalTime);
        this.setLeft(NIL);
        this.setRight(NIL);
        this.setParent(NIL);

        // Set RED color to all the nodes except NIL node, by default.
        this.setColor(buildingNumber == -1 ? NodeColor.BLACK : NodeColor.RED);
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

    @Override
    public String toString() {
        return "(" + this.getBuildingNumber() + "," + this.getExecutedTime() + "," + this.getTotalTime() + ")";
    }
}