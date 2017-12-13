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

    private QuestionLab(Context context){
        mQuestions = new ArrayList<Question>();

        for(int i= 0; i<6; i++){

            String question = "Question " + i;
            question += " This is a very long ass question that i am not sure how it will be displayed";
            List<String> options = new ArrayList<String>();
            options.add("Option 1");
            options.add("Option 2");
            options.add("Option 3");

            Question newQ = new Question(question,"Answer",false, UUID.randomUUID(), i+1, options);
            mQuestions.add(newQ);
        }
    }


    public static QuestionLab get(Context context){
        if (sQuestionLab == null){
            sQuestionLab = new QuestionLab(context);
        }
        return  sQuestionLab;
    }

    public List<Question> getQuestions(){
        return mQuestions;
    }

    public Question getQuestion(UUID id){
        for(Question curQuestion: mQuestions){
            if(curQuestion.getID().equals(id)){
                return curQuestion;
            }
        }

        return null;
    }

}
