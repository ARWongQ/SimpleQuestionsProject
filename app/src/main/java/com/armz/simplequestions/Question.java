package com.armz.simplequestions;

import java.util.UUID;

/**
 * Created by augustowong on 12/9/17.
 */

public class Question {
    private UUID mID;
    private String mQuestion;
    private String mAnswer;
    private Boolean mhasPassed;



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
}
