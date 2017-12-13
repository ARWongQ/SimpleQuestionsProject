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
//
//    private String mQuestionDisplay;

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

//    public String getmQuestionDisplay() {
//        return mQuestionDisplay;
//    }
//
//    public void setmQuestionDisplay(String mQuestionDisplay) {
//        this.mQuestionDisplay = mQuestionDisplay;
//    }

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

    private String mQuestion;

    private String mAnswer;
    // for now though...
    private String mAnswer2;
    private String mAnswer3;
    private String mAnswer4;


    // Created constructor with no Arguments
    public Question() {
    }

    //Constructor
    public Question(int mID, String mQuestion, String mAnswer, String mAnswer2, String mAnswer3, String mAnswer4){
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mID = mID;
        this.mAnswer2 = mAnswer2;
        this.mAnswer3 = mAnswer3;
        this.mAnswer4 = mAnswer4;
//        this.mQuestionDisplay = "Problem #" + numb;
    }

}
