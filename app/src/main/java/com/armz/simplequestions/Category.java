package com.armz.simplequestions;

/**
 * Created by augustowong on 12/13/17.
 */

public class Category {
    private String name;
    private Boolean hasPermission;
    private int cost;


    //Constructor
    public Category(String name, Boolean hasPermission) {
        this.name = name;
        this.hasPermission = hasPermission;
        this.cost = 0;
    }

    public Category(String name, Boolean hasPermission, int cost) {
        this.name = name;
        this.hasPermission = hasPermission;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
}