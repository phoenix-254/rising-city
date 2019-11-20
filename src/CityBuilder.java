import java.io.IOException;
import java.util.*;

import test.*;
import util.OutputParser;
import datastructures.*;
import pojo.*;

public class CityBuilder {
    private Queue<TestCase> testCases;

    // Global counter representing the present day.
    private int presentDay = 0;

    private MinHeap minHeap;
    private RedBlackTree redBlackTree;

    private static final int MAX_DAYS_TO_WORK = 5;

    public CityBuilder(Queue<TestCase> testCases) {
        this.testCases = testCases;
        this.minHeap = new MinHeap(2000);
        this.redBlackTree = new RedBlackTree();
    }

    public void build() {
        int daysToNextWork = 0;

        // Work until either there are requests to be processed from queue or there are buildings left to be built.
        while(!testCases.isEmpty() || !minHeap.isEmpty()) {
            if(!testCases.isEmpty()) {
                TestCase testCase = testCases.peek();

                // Work request can be taken only when the input time of the request matches the present day.
                if(testCase.getInputTime() == presentDay) {
                    switch(testCase.getTestCommand()) {
                        case INSERT:
                            insertBuilding(testCase.getBuildingId(), 0, testCase.getTotalConstructionTime());
                            break;
                        case PRINT:
                            OutputParser.addBuildingToBePrinted(redBlackTree.search(testCase.getStartBuildingNum()));
                            break;
                        case PRINT_MULTIPLE:
                            OutputParser.addMultipleBuildingsToBePrinted(redBlackTree.searchInRange(
                                testCase.getStartBuildingNum(), testCase.getEndBuildingNum()));
                            break;
                    }

                    // Remove from queue once executed.
                    testCases.remove();
                }

                if(!testCases.isEmpty()) {
                    daysToNextWork = testCases.peek().getInputTime() - presentDay;
                }
            }

            if(!minHeap.isEmpty()) {
                HeapNode heapNode = minHeap.extractMin();
                int workTodo = Math.min(heapNode.getTotalTime() - heapNode.getExecutedTime(), 
                                        Math.min(MAX_DAYS_TO_WORK, daysToNextWork));

                heapNode.setExecutedTime(heapNode.getExecutedTime() + workTodo);
                heapNode.getRbtReference().setExecutedTime(heapNode.getExecutedTime());

                presentDay += workTodo;

                if(heapNode.getExecutedTime() == heapNode.getTotalTime()) {
                    OutputParser.addFinishedBuildingToBePrinted(heapNode.getRbtReference(), presentDay);
                    redBlackTree.delete(heapNode.getRbtReference());
                } else {    
                    minHeap.add(heapNode);
                }
            }
        }

        try {
            OutputParser.print();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void insertBuilding(int buildingNumber, int executedTime, int totalTime) {
        RedBlackNode rbNode = new RedBlackNode(buildingNumber, executedTime, totalTime);
        HeapNode heapNode = new HeapNode(buildingNumber, executedTime, totalTime, rbNode);
        rbNode.setHeapNodeReference(heapNode);
        redBlackTree.insert(rbNode);
        minHeap.add(heapNode);
    }
}