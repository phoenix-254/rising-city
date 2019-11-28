package util;

import test.*;
import java.util.regex.*;

// A utility class for reading test case data from the input and creating TestCase object from it.

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
        
        // Decode input string using regular expression.
        Pattern pattern = Pattern.compile("(^\\d+): ([a-zA-Z]+)\\((.+)\\)");
        Matcher matcher = pattern.matcher(testCaseStr);
        if(matcher.find()) {
            testCase.setInputTime(Integer.parseInt(matcher.group(1)));
            testCase.setTestCommand(TestCommand.valueOf(matcher.group(2).toUpperCase()));
        }

        String[] numbers = matcher.group(3).split(",");
        switch (testCase.getTestCommand()) {
            case INSERT:
                testCase.setBuildingId(Integer.parseInt(numbers[0]));
                testCase.setTotalConstructionTime(Integer.parseInt(numbers[1]));
                break;
            case PRINT:
            case PRINTBUILDING:
                testCase.setStartBuildingNum(Integer.parseInt(numbers[0]));
                if(numbers.length > 1) testCase.setEndBuildingNum(Integer.parseInt(numbers[1]));
                break;
        }

        return testCase;
    }
}