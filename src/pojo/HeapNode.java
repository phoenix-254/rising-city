package pojo;

public class HeapNode extends Building {
    private RedBlackNode rbtNodeReference;

    public HeapNode(int buildingNumber, int executedTime, int totalTime, RedBlackNode node) {
        super(buildingNumber, executedTime, totalTime);
        this.setRbtReference(node);
    }

    public RedBlackNode getRbtReference() {
        return rbtNodeReference;
    }

    public void setRbtReference(RedBlackNode node) {
        this.rbtNodeReference = node;
    }
}