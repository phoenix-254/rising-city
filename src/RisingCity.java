import java.io.*;
import java.util.*;

import test.*;
import util.InputParser;

import datastructures.*;
import pojo.*;

public class risingCity {
    // Store all the test data from file at once, and store them in a Queue.
    private static Queue<TestCase> testCases;

    public static void main(String[] args) throws IOException {
        // try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(args[0])))) {
        //     testCases = new LinkedList<>();
            
        //     String testCaseStr;
        //     while((testCaseStr = bufferedReader.readLine()) != null) {
        //         // Parse the input and add the Test case into Queue.
        //         testCases.add(InputParser.getParsedTestCase(testCaseStr));
        //     }
        // }

        // MinHeap heap = new MinHeap(10);
        // heap.add(new HeapNode(1, 5, 10, null));
        // heap.add(new HeapNode(2, 3, 25, null));
        // heap.add(new HeapNode(3, 17, 18, null));
        // heap.add(new HeapNode(4, 10, 15, null));
        // heap.add(new HeapNode(5, 84, 94, null));
        // heap.add(new HeapNode(6, 19, 20, null));
        // heap.add(new HeapNode(7, 6, 9, null));
        // heap.add(new HeapNode(8, 22, 25, null));
        // heap.add(new HeapNode(9, 9, 30, null));
        // heap.add(new HeapNode(10, 1, 5, null));
        // heap.print();

        // System.out.println("Min: " + heap.getMin().getExecutedTime());

        // System.out.println("Removing Min");
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();
        // heap.extractMin();heap.print();

        RedBlackTree redBlackTree = new RedBlackTree();

        // redBlackTree.add(new RedBlackNode(5, 0, 0));
        // redBlackTree.add(new RedBlackNode(15, 0, 0));
        // redBlackTree.add(new RedBlackNode(2, 0, 0));
        // redBlackTree.add(new RedBlackNode(18, 0, 0));
        // redBlackTree.add(new RedBlackNode(10, 0, 0));
        // redBlackTree.add(new RedBlackNode(20, 0, 0));
        // redBlackTree.add(new RedBlackNode(17, 0, 0));
        // redBlackTree.add(new RedBlackNode(22, 0, 0));

        redBlackTree.add(new RedBlackNode(5, 0, 0));
        redBlackTree.add(new RedBlackNode(10, 0, 0));
        redBlackTree.add(new RedBlackNode(2, 0, 0));
        redBlackTree.add(new RedBlackNode(1, 0, 0));
        redBlackTree.add(new RedBlackNode(12, 0, 0));
        redBlackTree.add(new RedBlackNode(7, 0, 0));
        redBlackTree.add(new RedBlackNode(3, 0, 0));
        redBlackTree.add(new RedBlackNode(6, 0, 0));
        redBlackTree.add(new RedBlackNode(25, 0, 0));
        redBlackTree.add(new RedBlackNode(27, 0, 0));
        redBlackTree.add(new RedBlackNode(0, 0, 0));
        redBlackTree.add(new RedBlackNode(14, 0, 0));
        redBlackTree.add(new RedBlackNode(4, 0, 0));
        redBlackTree.add(new RedBlackNode(9, 0, 0));
        redBlackTree.add(new RedBlackNode(13, 0, 0));
        redBlackTree.add(new RedBlackNode(-1, 0, 0));
        redBlackTree.print();
    }
}