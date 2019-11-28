package datastructures;

/** 
 * Reference: Introduction to Algorithms 3rd Edition by Clifford Stein, Thomas H. Cormen, 
 * Charles E. Leiserson, Ronald L. Rivest
*/

import pojo.*;
import java.util.*;

public class RedBlackTree {
    // Root node of the red black tree.
    private RedBlackNode root;

    // Null external black node.
    private RedBlackNode nil;

    // Constructor.
    public RedBlackTree() {
        this.nil = RedBlackNode.NIL;
        this.root = nil;
        this.root.setLeft(nil);
        this.root.setRight(nil);
    }

    // Checks if the node is left child.
    private boolean isLeftChild(RedBlackNode node) {
        return (node == node.getParent().getLeft());
    }

    // Checks if the node is right child.
    private boolean isRightChild(RedBlackNode node) {
        return (node == node.getParent().getRight());
    }

    // Updates child link for parent node.
    private void updateParentChildLink(RedBlackNode parent, RedBlackNode oldChild, RedBlackNode newChild) {
        // Set parent as new-child's parent.
        newChild.setParent(parent);

        if(parent == nil) { // If rotation caused the new child to become root.
            root = newChild;
        } else if(isLeftChild(oldChild)) { // If the old child was in the left.
            parent.setLeft(newChild);
        } else { // If the old child was in the right.
            parent.setRight(newChild);
        }
    }

    // Executes left rotation on the given node.
    private void rotateLeft(RedBlackNode node) {
        // Copy node's right child.
        RedBlackNode yNode = node.getRight();
        
        // Turn yNode's left subtree into node's right subtree.
        node.setRight(yNode.getLeft());

        // Update yNode's left child's parent by node if it's not a leaf node.
        if(yNode.getLeft() != nil) {
            yNode.getLeft().setParent(node);
        }

        updateParentChildLink(node.getParent(), node, yNode);

        yNode.setLeft(node);
        node.setParent(yNode);
    }

    // Executes right rotation on the given node.
    private void rotateRight(RedBlackNode node) {
        // Copy node's left child.
        RedBlackNode yNode = node.getLeft();

        // Update node's left child by yNode's right subtree.
        node.setLeft(yNode.getRight());

        // Update yNode's right child's parent by node if it's not a leaf node.
        if(yNode.getRight() != nil) {
            yNode.getRight().setParent(node);
        }

        updateParentChildLink(node.getParent(), node, yNode);

        yNode.setRight(node);
        node.setParent(yNode);
    }

    // Adds node into the tree.
    // Node is added as per binary search tree rules, and then rebalanced to maintain red black tree property.
    public void insert(RedBlackNode node) {
        if(node == null) 
            throw new IllegalArgumentException("Error: Invalid input. Cannot insert null.");

        RedBlackNode xNode = root, yNode = nil;

        while(xNode != nil) {
            yNode = xNode;
            xNode = (node.getBuildingNumber() < xNode.getBuildingNumber()) ? xNode.getLeft() : xNode.getRight();
        }

        node.setParent(yNode);
        
        // Inserts the node at appropriate position by binary tree properties.
        if(yNode == nil) {
            root = node;
        } else if(node.getBuildingNumber() < yNode.getBuildingNumber()) {
            yNode.setLeft(node);
        } else if(node.getBuildingNumber() > yNode.getBuildingNumber()) {
            yNode.setRight(node);
        } else if(node.getBuildingNumber() == yNode.getBuildingNumber()) {
            throw new IllegalArgumentException("Error: Trying to insert duplicate node. Building with building id " 
            + node.getBuildingNumber() + " already exists.");
        }

        insertionRebalance(node);
    }

    // Re-establishes the RBT property if there are two consecutive red nodes after insertion.
    private void insertionRebalance(RedBlackNode node) {
        RedBlackNode yNode = null;

        while(node.getParent().getColor() == NodeColor.RED) {
            if(isLeftChild(node.getParent())) {
                yNode = node.getParent().getParent().getRight();
                if(yNode.getColor() == NodeColor.RED) {
                    node.getParent().setColor(NodeColor.BLACK);
                    yNode.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                } else {
                    if(isRightChild(node)) {
                        node = node.getParent();
                        rotateLeft(node);
                    }

                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rotateRight(node.getParent().getParent());
                }
            }
            else {
                yNode = node.getParent().getParent().getLeft();
                if(yNode.getColor() == NodeColor.RED) {
                    node.getParent().setColor(NodeColor.BLACK);
                    yNode.setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    node = node.getParent().getParent();
                } else {
                    if(isLeftChild(node)) {
                        node = node.getParent();
                        rotateRight(node);
                    }

                    node.getParent().setColor(NodeColor.BLACK);
                    node.getParent().getParent().setColor(NodeColor.RED);
                    rotateLeft(node.getParent().getParent());
                }
            }
        }

        root.setColor(NodeColor.BLACK);
    }

    // Removes the given node from the tree.
    public void delete(RedBlackNode node) {
        if(node == null) 
            throw new IllegalArgumentException("Error: Invalid input. Cannot remove null.");
        
        RedBlackNode xNode = null, yNode = node;
        NodeColor nodeColor = yNode.getColor();

        if(node.getLeft() == nil) {
            xNode = node.getRight();
            updateParentChildLink(node.getParent(), node, node.getRight());
        } else if(node.getRight() == nil) {
            xNode = node.getLeft();
            updateParentChildLink(node.getParent(), node, node.getLeft());
        } else {
            yNode = getMinimumNode(node.getRight());
            nodeColor = yNode.getColor();

            xNode = yNode.getRight();

            if(yNode.getParent() == node) {
                xNode.setParent(yNode);
            } else {
                updateParentChildLink(yNode.getParent(), yNode, yNode.getRight());
                yNode.setRight(node.getRight());
                yNode.getRight().setParent(yNode);
            }

            updateParentChildLink(node.getParent(), node, yNode);
            yNode.setLeft(node.getLeft());
            yNode.getLeft().setParent(yNode);
            yNode.setColor(node.getColor());
        }

        if(nodeColor == NodeColor.BLACK) {
            deletionRebalance(xNode);
        }
    }

    // Min Node in any BST can be found by following left child pointers from root until we encounter NULL.
    // This method returns the node with min value in the subtree rooted at node.
    private RedBlackNode getMinimumNode(RedBlackNode node) {
        while(node.getLeft() != nil) {
            node = node.getLeft();
        }
        return node;
    }

    // Re-establishes the RBT property if a black node is deleted.
    private void deletionRebalance(RedBlackNode node) {
        while(node != root && node.getColor() == NodeColor.BLACK) {
            RedBlackNode sibling;
            if(isLeftChild(node)) {
                sibling = node.getParent().getRight();
                if(sibling.getColor() == NodeColor.RED) {
                    sibling.setColor(NodeColor.BLACK);
                    node.getParent().setColor(NodeColor.RED);
                    rotateLeft(node.getParent());
                    sibling = node.getParent().getRight();
                }

                if(sibling.getLeft().getColor() == NodeColor.BLACK && sibling.getRight().getColor() == NodeColor.BLACK) {
                    sibling.setColor(NodeColor.RED);
                    node = node.getParent();
                } else {
                    if(sibling.getRight().getColor() == NodeColor.BLACK) {
                        sibling.getLeft().setColor(NodeColor.BLACK);
                        sibling.setColor(NodeColor.RED);
                        rotateRight(sibling);
                        sibling = node.getParent().getRight();
                    }
    
                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(NodeColor.BLACK);
                    sibling.getRight().setColor(NodeColor.BLACK);
                    rotateLeft(node.getParent());
                    node = root;
                }
            }
            else {
                sibling = node.getParent().getLeft();
                if(sibling.getColor() == NodeColor.RED) {
                    sibling.setColor(NodeColor.BLACK);
                    node.getParent().setColor(NodeColor.RED);
                    rotateRight(node.getParent());
                    sibling = node.getParent().getLeft();
                }

                if(sibling.getRight().getColor() == NodeColor.BLACK && sibling.getLeft().getColor() == NodeColor.BLACK) {
                    sibling.setColor(NodeColor.RED);
                    node = node.getParent();
                } else {
                    if(sibling.getLeft().getColor() == NodeColor.BLACK) {
                        sibling.getRight().setColor(NodeColor.BLACK);
                        sibling.setColor(NodeColor.RED);
                        rotateLeft(sibling);
                        sibling = node.getParent().getLeft();
                    }

                    sibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(NodeColor.BLACK);
                    sibling.getLeft().setColor(NodeColor.BLACK);
                    rotateRight(node.getParent());
                    node = root;
                }
            }
        }

        node.setColor(NodeColor.BLACK);
    }

    // Finds and returns a node with buildingNumber equal to the given buildingNumber.
    // Uses binary search to find the node.
    public RedBlackNode search(int buildingNumber) {
        return searchRecursive(root, buildingNumber);
    }

    private RedBlackNode searchRecursive(RedBlackNode root, int buildingNumber) {
        if(root == nil) return null;
        if(root.getBuildingNumber() == buildingNumber) {
            return root;
        } else if(root.getBuildingNumber() > buildingNumber) {
            return searchRecursive(root.getLeft(), buildingNumber);
        } else {
            return searchRecursive(root.getRight(), buildingNumber);
        }
    }

    // Find and returns a list of nodes for which following condition satisfies.
    // startBuildingNumber <= node.buildingNumber <= endBuildingNumber.
    public List<RedBlackNode> searchInRange(int startBuildingNumber, int endBuildingNumber) {
        List<RedBlackNode> result = new ArrayList<>();
        searchInRangeRecursive(root, startBuildingNumber, endBuildingNumber, result);
        return result;
    }

    private void searchInRangeRecursive(RedBlackNode root, int startBuildingNumber, int endBuildingNumber, List<RedBlackNode> nodes) {
        if(root == nil) return;
        if(root.getBuildingNumber() > startBuildingNumber) {
            searchInRangeRecursive(root.getLeft(), startBuildingNumber, endBuildingNumber, nodes);
        }

        if(root.getBuildingNumber() >= startBuildingNumber && root.getBuildingNumber() <= endBuildingNumber) {
            nodes.add(root);
        } 
        
        if(root.getBuildingNumber() < endBuildingNumber) {
            searchInRangeRecursive(root.getRight(), startBuildingNumber, endBuildingNumber, nodes);
        }
    }
}