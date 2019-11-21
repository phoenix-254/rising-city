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
    private HeapNode currentBuilding;

    private RedBlackTree redBlackTree;

    private static final int MAX_DAYS_TO_WORK = 5;
    private static final int MAX_BUILDINGS = 2000;

    // In each session, we store the number of days we need to continuously work on one building.
    // This can be min(timeLeftForBuildingToComplete, MAX_DAYS_TO_WORK).
    private int daysLeftToWorkInSession = 0;

    long startTime, endTime;

    public CityBuilder(Queue<TestCase> testCases) {
        this.testCases = testCases;
        this.minHeap = new MinHeap(MAX_BUILDINGS);
        this.redBlackTree = new RedBlackTree();
    }

    public void build() {
        startTime = System.nanoTime();

        // Work until either there are requests to be processed from queue or there are buildings left to be built.
        while(!testCases.isEmpty() || !minHeap.isEmpty() || currentBuilding != null) {
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
            }

            // Check if last building assignment is finished.
            // If yes, take the next from min heap.
            if(currentBuilding == null) {
                if(!minHeap.isEmpty()) {
                    currentBuilding = minHeap.extractMin();
                    daysLeftToWorkInSession = Math.min(currentBuilding.getTotalTime() - 
                                                    currentBuilding.getExecutedTime(), MAX_DAYS_TO_WORK);
                }
                else { // Just increment the global timer if heap is empty..
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
                OutputParser.addFinishedBuilding(currentBuilding.getRbtReference(), presentDay);
                redBlackTree.delete(currentBuilding.getRbtReference());
            }
            
            daysLeftToWorkInSession--;
            // If wokr for current building is done in this session, add it again to the heap
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

        finish();        
    }

    // Finish execution. Print the output to the file.
    private void finish() {
        try {
            endTime = System.nanoTime();
            OutputParser.addErrorMessage(String.valueOf((endTime - startTime)/1000000) + " ms.");
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