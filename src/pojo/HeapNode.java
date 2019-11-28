package pojo;

public class HeapNode extends Building {
    // Pointer to the corresponding red black node.
    private RedBlackNode rbtNodeReference;

    // Constructor.
    public HeapNode(int buildingNumber, int executedTime, int totalTime, RedBlackNode node) {
        super(buildingNumber, executedTime, totalTime);
        this.setRbtReference(node);
    }

    public RedBlackNode getRbtReference() {
        return rbtNodeReference;
    }

    private void setRbtReference(RedBlackNode node) {
        this.rbtNodeReference = node;
    }
}