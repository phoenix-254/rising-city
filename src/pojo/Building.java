package pojo;

/**
 * A POJO class for storing building details.
 * - buildingNumber - unique integer identifier for each building
 * - executedTime - total number of days spent so far working on this building
 * - totalTime - total number of days needed to complete the construction of the building
 */

public class Building {
    private int buildingNumber;
    private int executedTime;
    private int totalTime;

    public Building(int buildingNumber, int executedTime, int totalTime) {
        this.setBuildingNumber(buildingNumber);
        this.setExecutedTime(executedTime);
        this.setTotalTime(totalTime);
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    private void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(int executedTime) {
        this.executedTime = executedTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    private void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "(" + this.getBuildingNumber() + "," + this.getExecutedTime() + "," + this.getTotalTime() + ")";
    }
}