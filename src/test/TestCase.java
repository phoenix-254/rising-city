package test;

public class TestCase {
    private TestCommand testCommand;

    private int inputTime;

    private int buildingId;
    private int totalConstructionTime;

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