package datastructures;

import java.util.Stack;

import pojo.*;

public class RedBlackTree {
    // Number of currently inserted items in the red black tree.
    private int size;

    // Root node of the red black tree.
    private RedBlackNode root;

    public RedBlackTree() {}

    // Adds node into the tree.
    // Node is added as per binary search tree rules, and then rebalanced to maintain red black tree property.
    public void add(RedBlackNode node) {
        if(node == null) 
            throw new IllegalArgumentException("Error: Invalid input. Cannot insert null.");

        // Initialized root node.
        if(root == null) {
            root = node;
            root.setColor(NodeColor.BLACK);
            size++;
            return;
        }

        // Create a copy of root node.
        RedBlackNode rootCopy = root;
        while(rootCopy != null) {
            // Generate error and stop if building already exists.
            if(rootCopy.getBuildingNumber() == node.getBuildingNumber()) {
                throw new IllegalArgumentException("Error. Trying to insert duplicate node. Building with building id " 
                + node.getBuildingNumber() + " already exists.");
            }

            // If root value is greater than the node to be inserted, i.e insert into left sub-tree.
            if(rootCopy.getBuildingNumber() > node.getBuildingNumber()) {
                // Add node as a left child if it's null.
                if(rootCopy.getLeft() == null) {
                    rootCopy.setLeft(node);
                    rootCopy.getLeft().setParent(rootCopy);
                    rebalance(rootCopy.getLeft());
                    size++;
                    return;
                }
                // Update root and continue.
                rootCopy = rootCopy.getLeft();
            }
            // insert into right-subtree.
            else {
                // Add node as a right child if it's null.
                if(rootCopy.getRight() == null) {
                    rootCopy.setRight(node);
                    rootCopy.getRight().setParent(rootCopy);
                    rebalance(rootCopy.getRight());
                    size++;
                    return;
                }
                // Update root and continue.
                rootCopy = rootCopy.getRight();
            }
        }
    }


    private void rebalance(RedBlackNode node) {
        RedBlackNode parent = node.getParent();
        // If root node, nothing to do.
        if(parent == null) return;
        
        RedBlackNode grandParent = parent.getParent();
        if(grandParent == null) return;

        // Already balanced, since there are no consecutive red nodes.
        if(node.getColor() == NodeColor.BLACK || parent.getColor() == NodeColor.BLACK) {
            return;
        }

        boolean isNodeLeft = (parent.getLeft() == node);
        boolean isParentLeft = (grandParent.getLeft() == parent);

        RedBlackNode uncle = isParentLeft ? grandParent.getRight() : grandParent.getLeft();
        boolean isUncleRed = (uncle != null ? (uncle.getColor() == NodeColor.RED) : false);

        // If uncle node is red, perform recoloring.
        if(isUncleRed) {
            if(grandParent.getParent() != null) grandParent.setColor(NodeColor.RED);
            parent.setColor(NodeColor.BLACK);
            uncle.setColor(NodeColor.BLACK);
            // Continue rebalancing from grandparent.
            rebalance(grandParent);
        }
        // If uncle node is black, perform rotations.
        else {
            if(isParentLeft) {
                if(isNodeLeft) {
                    // LLb rotation
                    leftLeftBlackCase(node, parent, grandParent);
                } else {
                    // LRb rotation
                    leftRightBlackCase(node, parent, grandParent);
                }
            } else {
                if(isNodeLeft) {
                    // RLb rotation
                    rightLeftBlackCase(node, parent, grandParent);
                } else {
                    // RRb rotation
                    rightRightBlackCase(node, parent, grandParent);
                }
            }
        }
    }

    // Performs LL rotation to rebalance the tree.
    private void leftLeftBlackCase(RedBlackNode node, RedBlackNode parent, RedBlackNode grandParent) {
        RedBlackNode temp = parent.getRight();
        RedBlackNode parentOfGrandParent = grandParent.getParent();

        grandParent.setColor(NodeColor.RED);
        grandParent.setLeft(temp);
        if(temp != null) temp.setParent(grandParent);

        parent.setRight(grandParent);
        parent.setColor(NodeColor.BLACK);
        parent.setParent(parentOfGrandParent);
        grandParent.setParent(parent);

        updateParentChildLink(parentOfGrandParent, grandParent, parent);
    }

    // Performs RR rotation to rebalance the tree.
    private void rightRightBlackCase(RedBlackNode node, RedBlackNode parent, RedBlackNode grandParent) {
        RedBlackNode temp = parent.getLeft();
        RedBlackNode parentOfGrandParent = grandParent.getParent();

        grandParent.setColor(NodeColor.RED);
        grandParent.setRight(temp);
        if(temp != null) temp.setParent(grandParent);

        parent.setLeft(grandParent);
        parent.setColor(NodeColor.BLACK);
        parent.setParent(parentOfGrandParent);
        grandParent.setParent(parent);

        updateParentChildLink(parentOfGrandParent, grandParent, parent);
    }

    // Performs LR rotation to rebalance the tree.
    private void leftRightBlackCase(RedBlackNode node, RedBlackNode parent, RedBlackNode grandParent) {
        RedBlackNode left = node.getLeft();
        RedBlackNode right = node.getRight();
        RedBlackNode parentOfGrandParent = grandParent.getParent();

        parent.setRight(left);
        if(left != null) left.setParent(parent);

        grandParent.setLeft(right);
        if(right != null) right.setParent(grandParent);

        node.setLeft(parent);
        node.setRight(grandParent);
        node.setParent(parentOfGrandParent);
        node.setColor(NodeColor.BLACK);
        
        grandParent.setParent(node);
        grandParent.setColor(NodeColor.RED);

        updateParentChildLink(parentOfGrandParent, grandParent, node);
    }

    // Performs RL rotation to rebalance the tree.
    private void rightLeftBlackCase(RedBlackNode node, RedBlackNode parent, RedBlackNode grandParent) {
        RedBlackNode left = node.getLeft();
        RedBlackNode right = node.getRight();
        RedBlackNode parentOfGrandParent = grandParent.getParent();
        
        parent.setLeft(right);
        if(right != null) right.setParent(parent);

        grandParent.setRight(left);
        if(left != null) left.setParent(grandParent);

        node.setLeft(grandParent);
        node.setRight(parent);
        node.setParent(parentOfGrandParent);
        node.setColor(NodeColor.BLACK);

        grandParent.setParent(node);
        grandParent.setColor(NodeColor.RED);

        updateParentChildLink(parentOfGrandParent, grandParent, node);
    }

    // Updates child link for parent node.
    private void updateParentChildLink(RedBlackNode parent, RedBlackNode oldChild, RedBlackNode newChild) {
        if(parent != null) {
            // If the old child was in the left.
            if(parent.getLeft() == oldChild) {
                parent.setLeft(newChild);
            }
            // If the old child was in the right.
            else {
                parent.setRight(newChild);
            }
        } else {
            // If rotation caused the new child to become root.
            root = newChild;
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder("[");

        RedBlackNode copy = root;
        Stack<RedBlackNode> stack = new Stack<>();
        while(copy != null || !stack.isEmpty()) {
            while(copy != null) {
                stack.push(copy);
                copy = copy.getLeft();
            }

            copy = stack.pop();
            sb.append(" [Value: " + copy.getBuildingNumber() + ", Color: " + copy.getColor().toString() + "], ");
            copy = copy.getRight();
        }

        sb.append("]");

        System.out.println(sb.toString());
    }
}