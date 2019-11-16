package datastructures;

import pojo.*;

public class MinHeap {
    // Heap array containing Node objects.
    private HeapNode[] heap;

    // Max capacity for the heap array.
    private int capacity;

    // Number of currently inserted items into the heap array.
    private int size;

    // Index of the top-most item of the heap.
    private static final int TOP = 1;

    // Constructor.
    public MinHeap(int capacity) {
        this.capacity = capacity;
        initHeap(capacity);
    }

    // Initialize heap array with the given capacity.
    private void initHeap(int capacity) {
        this.heap = new HeapNode[capacity + 1];
        this.heap[0] = null;
        size++;
    }

    // Return true if the heap is empty, false otherwise.
    private boolean isEmpty() {
        return size == 1;
    }

    // Returns true if the given position has parent, false otherwise
    private boolean hasParent(int position) {
        return position != 1;
    }

    // Returns true if the item at given position is a leaf node, false otherwise. 
    private boolean isLeaf(int position) {
        return (position >= size/2 && position <= size);
    }

    // Returns the index of the parent of the given position.
    private int getParentPosition(int position) {
        return (position / 2);
    }

    // Returns the index of the left child of the given position.
    private int getLeftChildPosition(int position) {
        return (position * 2);
    }

    // Returns the index of the right child of the given position.
    private int getRightChildPosition(int position) {
        return (position * 2) + 1;
    }

    // Swap two items in the heap array.
    private void swap(int i, int j) {
        HeapNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Add item into heap array.
    public void add(HeapNode item) {
        if(size > capacity) {
            throw new IllegalStateException("Capacity Exceeded. Cannot insert more than " + capacity + " items.");
        }
        
        heap[size] = item;
        heapifyUp(size);
    }

    // Rearranges the heap array as per heap property whenever a new item is inserted.
    private void heapifyUp(int position) {
        while(hasParent(position) && 
              heap[position].getExecutedTime() < heap[getParentPosition(position)].getExecutedTime()) {
            swap(position, getParentPosition(position));
            position = getParentPosition(position);
        }
        size++;
    }

    // Returns the minimum item from the heap.
    public HeapNode getMin() {
        if(isEmpty()) {
            throw new IllegalStateException("Error. No items available. Heap is empty.");
        }
        return heap[TOP];
    }

    // Removes and returns the minimum item from the heap.
    public HeapNode extractMin() {
        if(isEmpty()) {
            throw new IllegalStateException("Error. No items available. Heap is empty.");
        }

        HeapNode item = heap[TOP];
        // Copies the last heap item at top, and then heapify it down.
        heap[TOP] = heap[size - 1];
        heapifyDown(TOP);
        heap[size - 1] = null;
        size--;
        return item;
    }

    // Set the node at given position to it's appropriate index by recursively performing heapify operation.
    private void heapifyDown(int position) {
        // Break the recursion when the item is leaf node.
        if(isLeaf(position)) return;

        // If the executed time value at position is lesser than it's left or right child.  
        if(heap[position].getExecutedTime() > heap[getLeftChildPosition(position)].getExecutedTime() || 
           heap[position].getExecutedTime() > heap[getRightChildPosition(position)].getExecutedTime()) {
            // If executed time of left child node is lesser than the right child node. 
            if(heap[getLeftChildPosition(position)].getExecutedTime() < 
               heap[getRightChildPosition(position)].getExecutedTime()) {
                swap(position, getLeftChildPosition(position));
                heapifyDown(getLeftChildPosition(position));
            }
            else {
                swap(position, getRightChildPosition(position));
                heapifyDown(getRightChildPosition(position));
            }
        }
    }
}