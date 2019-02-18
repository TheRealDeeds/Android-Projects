package com.example.idlegamepractice;

public class Resource {

    private String resourceName;
    private int resourceNumber;
    private int acquireNumber;
    private int acquireCount = 1;
    private int incrementNumber = 1;

    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceNumber(int resourceNumber) {
        this.resourceNumber = resourceNumber;
    }

    public int getResourceNumber() {
        return resourceNumber;
    }

    public int getAcquireNumber() {
        return acquireNumber;
    }

    public void setAcquireNumber(int acquireNumber) {
        this.acquireNumber = acquireNumber;
    }

    public int getAcquireCount() {
        return acquireCount;
    }

    public void setAcquireCount(int acquireCount) {
        this.acquireCount = acquireCount;
    }

    public int getIncrementNumber() {
        return incrementNumber;
    }

    public void setIncrementNumber(int incrementNumber) {
        this.incrementNumber = incrementNumber;
    }
}
