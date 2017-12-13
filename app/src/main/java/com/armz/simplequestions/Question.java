package com.armz.simplequestions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by augustowong on 12/9/17.
 */
@IgnoreExtraProperties
public class Question {

    private int mID;
    private String mQuestionDisplay;
    private String mQuestion;
    private String mAnswer;
    private Boolean mhasPassed;
    private List<String> wrongAnswers = new ArrayList<String>();
    private String mHint;

    public int getmID() {
        return mID;
    }

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


    // Created constructor with no Arguments
    // needed for Firebase
    public Question() {
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmQuestionDisplay() {
        return mQuestionDisplay;
    }

    public void setmQuestionDisplay(String mQuestionDisplay) {
        this.mQuestionDisplay = mQuestionDisplay;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }

    public Boolean getMhasPassed() {
        return mhasPassed;
    }

    public void setMhasPassed(Boolean mhasPassed) {
        this.mhasPassed = mhasPassed;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public String getmHint() {
        return mHint;
    }

    public void setmHint(String mHint) {
        this.mHint = mHint;
    }


}
