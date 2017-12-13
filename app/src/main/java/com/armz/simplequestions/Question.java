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
    private List<String> wrongAnswers = new ArrayList<String>();
    private String mHint;



    //Constructor
    public Question(String mQuestion, String mAnswer, Boolean mhasPassed, int mID, int numb, List<String> optionalAnswers, String mHint){
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mhasPassed = mhasPassed;
        this.mID = mID;
        this.mQuestionDisplay = "Problem #" + numb;
        this.wrongAnswers = optionalAnswers;
        this.mHint = mHint;
    }

    public String getHint() {
        return mHint;
    }

    public void setHint(String hint) {
        mHint = hint;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
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
