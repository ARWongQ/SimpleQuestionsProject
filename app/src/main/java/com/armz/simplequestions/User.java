package com.armz.simplequestions;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by augustowong on 12/13/17.
 */


public class User {
    private String username;
    private int level;
    private int experience;
    private int money;
    private int ranking;

    @Exclude
    private ArrayList<String> boughtCategories;

    public User(String username, int level, int experience, int money, int ranking){
        this.username = username;
        this.level = level;
        this.experience = experience;
        this.money = money;
        this.ranking = ranking;
        this.boughtCategories = new ArrayList<>();
    }

    public User() {}

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<String> getBoughtCategories() {
        return boughtCategories;
    }

    public void setBoughtCategories(ArrayList<String> boughtCategories) {
        this.boughtCategories = boughtCategories;
    }
}
