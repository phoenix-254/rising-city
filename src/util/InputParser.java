package util;

import test.*;

// A parser for reading test case data from the input and creating TestCase object from it.

/**
 * Sample Input:
 * 0: Insert(50,20)
 * 15: Insert(15,25)
 * 16: PrintBuilding(0,100)
 * 20: Print(50)
 * 29: Insert(75,12)
 */
public class InputParser {
    public static TestCase getParsedTestCase(final String testCaseStr) {
        final TestCase testCase = new TestCase();
        testCase.setInputTime(getInputTime(testCaseStr));
        testCase.setTestCommand(getTestCommand(testCaseStr));
        
        switch (testCase.getTestCommand()) {
            case INSERT:
                testCase.setBuildingId(getNumber(testCaseStr, "(", ","));
                testCase.setTotalConstructionTime(getNumber(testCaseStr, ",", ")"));
                break;
            case PRINT:
                testCase.setStartBuildingNum(getNumber(testCaseStr, "(", ")"));
                break;
            case PRINTBUILDING:
                testCase.setStartBuildingNum(getNumber(testCaseStr, "(", ","));
                testCase.setEndBuildingNum(getNumber(testCaseStr, ",", ")"));
                break;
        }

        return testCase;
    }

    private static int getInputTime(final String testCaseStr) {
        return Integer.parseInt(testCaseStr.split(":")[0]);
    }

    private static TestCommand getTestCommand(final String testCaseStr) {
        final String temp = testCaseStr.substring(testCaseStr.indexOf(":") + 1, testCaseStr.indexOf("(")).trim();
        return temp.startsWith("I") ? TestCommand.INSERT : 
            (testCaseStr.contains(",") ? TestCommand.PRINTBUILDING : TestCommand.PRINT);
    }

    private static int getNumber(final String testCaseStr, final String separator1, final String separator2) {
        return Integer.parseInt(testCaseStr.substring(testCaseStr.indexOf(separator1) + 1, testCaseStr.indexOf(separator2)));
    }
}