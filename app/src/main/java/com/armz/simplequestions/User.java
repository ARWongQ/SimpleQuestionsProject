package com.armz.simplequestions;

/**
 * Created by augustowong on 12/13/17.
 */

public class User {
    private String username;
    private String password;
    private int level;
    private int experience;
    private int money;
    private int ranking;

    public User(String username, String password, int level, int experience, int money, int ranking){
        this.username = username;
        this.password = password;
        this.level = level;
        this.experience = experience;
        this.money = money;
        this.ranking = ranking;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
