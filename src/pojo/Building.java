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
        this.buildingNumber = buildingNumber;
        this.executedTime = executedTime;
        this.totalTime = totalTime;
    }

    public int getBuildingNumber() {
        return this.buildingNumber;
    }

    public int getExecutedTime() {
        return this.executedTime;
    }

    public void setExecutedTime(int executedTime) {
        this.executedTime = executedTime;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}