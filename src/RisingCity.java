import java.io.*;
import java.util.*;

import datastructures.MinHeap;
import pojo.*;

import test.*;
import util.InputParser;

public class risingCity {
    private static Queue<TestCase> testCases;

    public static void main(String[] args) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(args[0])))) {
            testCases = new LinkedList<>();
            
            String testCaseStr;
            while((testCaseStr = bufferedReader.readLine()) != null) {
                // Parse the input and add the Test case into Queue.
                testCases.add(InputParser.getParsedTestCase(testCaseStr));
            }
        }

        while(!testCases.isEmpty()) {
            TestCase t = testCases.remove();
            System.out.println("InputTime: " + t.getInputTime() 
            + ", Command: " + t.getTestCommand().toString()
            + ", BuildingId: " + t.getBuildingId()
            + ", ConstructionTime: " + t.getTotalConstructionTime()
            + ", Start: " + t.getStartBuildingNum()
            + ", End: " + t.getEndBuildingNum());
        }
        
        //MinHeap heap = new MinHeap(2000);
        //heap.add(new HeapNode(1, 5, 10, null));
    }
}