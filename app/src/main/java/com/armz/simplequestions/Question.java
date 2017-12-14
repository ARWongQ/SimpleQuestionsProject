package com.armz.simplequestions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by augustowong on 12/9/17.
 */

public class Question {
    private int mID;
    private String mQuestionDisplay;
    private String mQuestion;
    private String mAnswer;
    private Boolean mhasPassed;
    private String mWrongAnswer1;
    private String mWrongAnswer2;
    private String mWrongAnswer3;


    private String mHint;



    //Constructor
    public Question(String mQuestion, String mAnswer, Boolean mhasPassed, int mID, int numb, String mHint,
                    String mWrongAnswer1, String mWrongAnswer2, String mWrongAnswer3){
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mhasPassed = mhasPassed;
        this.mID = mID;
        this.mQuestionDisplay = "Problem #" + numb;
        this.mWrongAnswer1 = mWrongAnswer1;
        this.mWrongAnswer2 = mWrongAnswer2;
        this.mWrongAnswer3 = mWrongAnswer3;
        this.mHint = mHint;
    }

    public String getHint() {
        return mHint;
    }

    public void setHint(String hint) {
        mHint = hint;
    }

    public String getWrongAnswer1() {
        return mWrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        mWrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return mWrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        mWrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return mWrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        mWrongAnswer3 = wrongAnswer3;
    }

    public String getQuestionDisplay() {
        return mQuestionDisplay;
    }

    public void setQuestionDisplay(String questionDisplay) {
        mQuestionDisplay = questionDisplay;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
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
