package com.armz.simplequestions;



import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by augustowong on 12/9/17.
 */

public class QuestionLab {
    private static QuestionLab sQuestionLab;

    private List<Question> mQuestions;

    public QuestionLab(Context context){
        mQuestions = new ArrayList<Question>();


    }

    public static QuestionLab get(Context context){
        if (sQuestionLab == null){
            sQuestionLab = new QuestionLab(context);
        }
        return  sQuestionLab;
    }

    public Question getQuestion(int mID){
        for (Question q : mQuestions) {
            if (q.getmID() == mID) {
                return q;
            }
        }
        return null;
    }

    public List<Question> getQuestions(){
        return mQuestions;
    }

    public void setmQuestions(List<Question> mQuestions) {
        this.mQuestions = mQuestions;
    }


}
