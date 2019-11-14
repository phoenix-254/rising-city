import datastructures.MinHeap;
import pojo.Building;

public class RisingCity {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);
        heap.add(new Building(1, 5, 10));
        heap.add(new Building(2, 3, 25));
        heap.add(new Building(3, 17, 18));
        heap.add(new Building(4, 10, 15));
        heap.add(new Building(5, 84, 94));
        heap.add(new Building(6, 19, 20));
        heap.add(new Building(7, 6, 9));
        heap.add(new Building(8, 22, 25));
        heap.add(new Building(9, 9, 30));

        heap.print();
    }
}