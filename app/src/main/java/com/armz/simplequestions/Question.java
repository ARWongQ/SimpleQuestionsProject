package com.armz.simplequestions;

import java.util.UUID;

/**
 * Created by augustowong on 12/9/17.
 */

public class Question {
    private UUID mID;
    private String mQuestion;

    // you idiot, this should be a list
    private String mAnswer;



    // for now though...
    private String mAnswer2;
    private String mAnswer3;
    private String mAnswer4;
    private Boolean mhasPassed;


    // Created constructor with no Arguments
    public Question() {
    }

    //Constructor
    public Question(String mQuestion, String mAnswer, Boolean mhasPassed, UUID mID){
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mhasPassed = mhasPassed;
        this.mID = mID;
    }

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public Boolean getMhasPassed() {
        return mhasPassed;
    }

    public void setMhasPassed(Boolean mhasPassed) {
        this.mhasPassed = mhasPassed;
    }

    public String getmAnswer2() {
        return mAnswer2;
    }

    public void setmAnswer2(String mAnswer2) {
        this.mAnswer2 = mAnswer2;
    }

    public String getmAnswer3() {
        return mAnswer3;
    }

    public void setmAnswer3(String mAnswer3) {
        this.mAnswer3 = mAnswer3;
    }

    public String getmAnswer4() {
        return mAnswer4;
    }

    public void setmAnswer4(String mAnswer4) {
        this.mAnswer4 = mAnswer4;
    }
}
