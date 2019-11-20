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
    private static final int MAX_BUILDINGS = 2000;

    public CityBuilder(Queue<TestCase> testCases) {
        this.testCases = testCases;
        this.minHeap = new MinHeap(MAX_BUILDINGS);
        this.redBlackTree = new RedBlackTree();
    }

    public void build() {
        // Stores the number of days left to next work.
        int daysToNextWork = 0;

        // Work until either there are requests to be processed from queue or there are buildings left to be built.
        while(!testCases.isEmpty() || !minHeap.isEmpty()) {
            if(!testCases.isEmpty()) {
                TestCase testCase = testCases.peek();

                // Work request can be taken only when the input time of the request matches the present day.
                if(testCase.getInputTime() == presentDay) {
                    switch(testCase.getTestCommand()) {
                        case INSERT:
                            try {
                                insertBuilding(testCase.getBuildingId(), 0, testCase.getTotalConstructionTime());
                            } catch(Exception exception) {
                                OutputParser.addErrorMessage(exception.getMessage());
                                finish();
                            }
                            break;
                        case PRINT:
                            OutputParser.addBuilding(redBlackTree.search(testCase.getStartBuildingNum()));
                            break;
                        case PRINT_MULTIPLE:
                            OutputParser.addMultipleBuildings(redBlackTree.searchInRange(
                                testCase.getStartBuildingNum(), testCase.getEndBuildingNum()));
                            break;
                    }

                    // Remove from queue once executed.
                    testCases.remove();
                }

                if(!testCases.isEmpty()) {
                    daysToNextWork = testCases.peek().getInputTime() - presentDay;
                } else {
                    daysToNextWork = Integer.MAX_VALUE;
                }
            }

            while(daysToNextWork != 0) {
                if(!minHeap.isEmpty()) {
                    // Get the next building to work upon from min heap.
                    HeapNode heapNode = minHeap.extractMin();
                    int workToDo = Math.min(heapNode.getTotalTime() - heapNode.getExecutedTime(),
                                            Math.min(MAX_DAYS_TO_WORK, daysToNextWork));

                    heapNode.setExecutedTime(heapNode.getExecutedTime() + workToDo);

                    presentDay += workToDo;

                    // If building construction is finished, remove it and print (buindingNumber,dayWhenItFinished) tuple.
                    if(heapNode.getExecutedTime() == heapNode.getTotalTime()) {
                        OutputParser.addFinishedBuilding(heapNode.getRbtReference(), presentDay);
                        redBlackTree.delete(heapNode.getRbtReference());
                    } else { // Else, add it again to the heap to complete remaining construction.   
                        heapNode.getRbtReference().setExecutedTime(heapNode.getExecutedTime());
                        minHeap.insert(heapNode);
                    }

                    daysToNextWork -= workToDo;
                } else {
                    presentDay += daysToNextWork;
                    daysToNextWork = 0;
                }
            }
        }

        finish();        
    }

    // Finish execution. Print the output to the file.
    private void finish() {
        try {
            OutputParser.print();
            System.exit(0);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Inserts new building to be worked in Min heap and Red black tree.
    private void insertBuilding(int buildingNumber, int executedTime, int totalTime) {
        RedBlackNode rbNode = new RedBlackNode(buildingNumber, executedTime, totalTime);
        HeapNode heapNode = new HeapNode(buildingNumber, executedTime, totalTime, rbNode);
        rbNode.setHeapNodeReference(heapNode);
        redBlackTree.insert(rbNode);
        minHeap.insert(heapNode);
    }
}