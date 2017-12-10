package com.armz.simplequestions;

/**
 * Created by augustowong on 12/9/17.
 */

public class Question {
    private String mQuestion;
    private String mAnswer;
    private Boolean mhasPassed;

    //Constructor
    public Question(String mQuestion, String mAnswer, Boolean mhasPassed){
        this.mQuestion = mQuestion;
        this.mAnswer = mAnswer;
        this.mhasPassed = mhasPassed;
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
