package com.armz.simplequestions;

/**
 * Created by augustowong on 12/12/17.
 */

public class Accomplishment {
    private int id;
    private String goalDescription;
    private int currentProgress;
    private int endProgress;
    private Boolean hasCompleted;


    //Constructor
    public Accomplishment(int id, String goalDescription, int currentProgress, int endProgress, Boolean hasCompleted) {
        this.id = id;
        this.goalDescription = goalDescription;
        this.currentProgress = currentProgress;
        this.endProgress = endProgress;
        this.hasCompleted = hasCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getEndProgress() {
        return endProgress;
    }

    public void setEndProgress(int endProgress) {
        this.endProgress = endProgress;
    }

    public Boolean getHasCompleted() {
        return hasCompleted;
    }

    public void setHasCompleted(Boolean hasCompleted) {
        this.hasCompleted = hasCompleted;
    }
}
