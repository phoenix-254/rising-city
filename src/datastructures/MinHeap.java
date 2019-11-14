package datastructures;

import pojo.Building;

public class MinHeap {
    // Heap array containing Building objects.
    private Building[] heap;

    // Max capacity for the heap array.
    private int capacity;

    // Number of currently inserted items in the heap array.
    private int size;

    // Constructor.
    public MinHeap(int capacity) {
        this.capacity = capacity;
        initHeap(capacity);
    }

    // Initialize heap array with given capacity.
    private void initHeap(int capacity) {
        this.heap = new Building[capacity + 1];
        this.heap[0] = new Building(-1, Integer.MIN_VALUE, -1);
        size++;
    }

    // Returns if the given position has parent.
    private boolean hasParent(int position) {
        return position != 1;
    }

    // Swap two items in the heap array.
    private void swap(int i, int j) {
        Building temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Rearranges the heap array as per heap property.
    private void heapify(int position) {
        while(hasParent(position)) {
            int parentPosition = (int) position / 2;
            if(heap[position].getExecutedTime() < heap[parentPosition].getExecutedTime()) {
                swap(position, parentPosition);
                position = parentPosition;
            } else {
                break;
            }
        }

        size++;
    }

    // Add item into heap array.
    public void add(Building item) {
        if(size > capacity) {
            throw new IllegalStateException("Capacity Exceeded. Cannot insert more than " + capacity + " items.");
        }
        
        heap[size] = item;
        heapify(size);
    }

    public void print() {
        for(int i = 1; i <= size/2; i++) {
            System.out.println("Parent: " + heap[i].getExecutedTime() + 
            ", LC: " + heap[2*i].getExecutedTime() +
            ", RC: " + heap[2*i + 1].getExecutedTime());
        }
    }
}