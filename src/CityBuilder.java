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

    // MinHeap instance.
    private MinHeap minHeap;
    // HeapNode instance representing the current building we are working upon.
    private HeapNode currentBuilding;

    // RedBlackTree instance.
    private RedBlackTree redBlackTree;
    // RedBlackNode instance representing the node to be deleted after building construction has been finished for that node.
    private RedBlackNode nodeToBeDeleted;

    // Represents the maximum number of days allowed to be worked upon one building at a time.
    private static final int MAX_DAYS_TO_WORK = 5;
    // Represents the maximum number of buildings allowed to be inserted.
    private static final int MAX_BUILDINGS = 2000;

    // In each session, we store the number of days we need to continuously work on one building.
    // This can be min(timeLeftForBuildingToComplete, MAX_DAYS_TO_WORK).
    private int daysLeftToWorkInSession = 0;

    // Constructor.
    public CityBuilder(Queue<TestCase> testCases) {
        this.testCases = testCases;
        this.minHeap = new MinHeap(MAX_BUILDINGS);
        this.redBlackTree = new RedBlackTree();
    }

    // Starts city building process.
    public void build() {
        // Work until either there are requests to be processed from queue or there are buildings left to be built.
        // Or if there's currentBuilding to work upon. Would be not-null in case of last building.
        while(!testCases.isEmpty() || !minHeap.isEmpty() || currentBuilding != null) {
            // Check if there are test case operations left to be performed.
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
                        case PRINTBUILDING:
                            OutputParser.addMultipleBuildings(redBlackTree.searchInRange(
                                testCase.getStartBuildingNum(), testCase.getEndBuildingNum()));
                            break;
                    }

                    // Remove from queue once executed.
                    testCases.remove();
                }
            }

            // Check if there's any building finished, if yes delete it.
            if(nodeToBeDeleted != null) {
                OutputParser.addFinishedBuilding(nodeToBeDeleted, presentDay);
                redBlackTree.delete(nodeToBeDeleted);
                nodeToBeDeleted = null;
            }

            // Check if last building assignment is finished.
            // If yes, take the next from min heap.
            if(currentBuilding == null) {
                if(!minHeap.isEmpty()) {
                    currentBuilding = minHeap.extractMin();
                    daysLeftToWorkInSession = Math.min(currentBuilding.getTotalTime() - 
                                                    currentBuilding.getExecutedTime(), MAX_DAYS_TO_WORK);
                }
                else { // Just increment the global timer if heap is empty.
                    presentDay++;
                    continue;
                }
            }

            // Update executed time in our data structures.
            currentBuilding.setExecutedTime(currentBuilding.getExecutedTime() + 1);
            currentBuilding.getRbtReference().setExecutedTime(currentBuilding.getExecutedTime());
            
            // Increment present day counter.
            presentDay++;

            // If building construction is finished, remove it and print (buindingNumber,dayWhenItFinished) tuple.
            if(currentBuilding.getExecutedTime() == currentBuilding.getTotalTime()) {
                // We defer node deletion from RBT to avoid mishandling the scenario when next input comes 
                // for the same building on immediate next day.
                // Example: (8,0,10) finishing on day 40 and on the same days there's a print command for the same 
                // building i.e 40: Print(8).
                nodeToBeDeleted = currentBuilding.getRbtReference();
            }
            
            daysLeftToWorkInSession--;
            
            // If work for current building is done in this session, add it again to the heap
            // if it isn't has not been completed yet.
            if(daysLeftToWorkInSession == 0) {
                if(currentBuilding.getExecutedTime() != currentBuilding.getTotalTime()) {
                    minHeap.insert(currentBuilding);
                }

                // Reset the currentBuilding to null, so that we can select the next building to work
                // upon in the next session.
                currentBuilding = null;
            }
        }

        // Last node to be printed.
        if(nodeToBeDeleted != null) {
            OutputParser.addFinishedBuilding(nodeToBeDeleted, presentDay);
            redBlackTree.delete(nodeToBeDeleted);
            nodeToBeDeleted = null;
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