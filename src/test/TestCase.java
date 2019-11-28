package test;

// A generic class containing information regarding different types of test cases.
public class TestCase {
    // Contains enum value representing the type of operation to be performed.
    private TestCommand testCommand;

    // Time when the test case has to be executed.
    private int inputTime;

    // Unique integer identifier for the building.
    private int buildingId;
    // Total time required for constructing the entire building.
    private int totalConstructionTime;

    // Range of building numbers to be printed. 
    // endBuildingNum would be 0 in case if only one building has to be printed.
    private int startBuildingNum, endBuildingNum;

    public TestCase() {}

    public TestCommand getTestCommand() {
        return testCommand;
    }

    public void setTestCommand(TestCommand testCommand) {
        this.testCommand = testCommand;
    }

    public int getInputTime() {
        return inputTime;
    }

    public void setInputTime(int inputTime) {
        this.inputTime = inputTime;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getTotalConstructionTime() {
        return totalConstructionTime;
    }

    public void setTotalConstructionTime(int totalConstructionTime) {
        this.totalConstructionTime = totalConstructionTime;
    }

    public int getStartBuildingNum() {
        return startBuildingNum;
    }

    public void setStartBuildingNum(int startBuildingNum) {
        this.startBuildingNum = startBuildingNum;
    }

    public int getEndBuildingNum() {
        return endBuildingNum;
    }

    public void setEndBuildingNum(int endBuildingNum) {
        this.endBuildingNum = endBuildingNum;
    }   
}