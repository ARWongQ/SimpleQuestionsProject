package com.armz.simplequestions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by augustowong on 12/10/17.
 */

public class QuestionFragment extends Fragment {
    private Question mQuestion;

    private TextView mQuestionTextView;
    private TextView mQuestionDisplayTextView;
    private Button mOption1;
    private Button mOption2;
    private Button mOption3;
    private Button mOption4;
    private List<String> options;

    private static final String ARG_QUESTION_ID = "question_id";


    public static QuestionFragment newInstance(int questionID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUESTION_ID, questionID);
        System.out.println("MY QUESTION ID in QUESTION FRAGMENT " + questionID);

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int questionId = (int) getArguments().getSerializable(ARG_QUESTION_ID);
        mQuestion = QuestionLab.get(getActivity()).getQuestion(questionId);


//        System.out.println("Setting up the Firebase --------------------------------------");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//
//
//        System.out.println("Setting up the Firebase ---------------------------------------");

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("question");
//
//        Question q = new Question(6, "Are you there NOW?", "Yes", "No", "Maybe", "Asleep");
//
//        myRef.child(q.getmQuestion()).setValue(q);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionTextView = (TextView) rootView.findViewById(R.id.question_textView);
        mQuestionDisplayTextView = (TextView) rootView.findViewById(R.id.questionDisplay_textView);
        mOption1 = rootView.findViewById(R.id.button_One);
        mOption2 = rootView.findViewById(R.id.button_Two);
        mOption3 = rootView.findViewById(R.id.button_Three);
        mOption4 = rootView.findViewById(R.id.button_Four);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("question");



        myRef.child("Are you there NOW?").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Question mQuestion = dataSnapshot.getValue(Question.class);
                System.out.println("This is the question");
                System.out.println(mQuestion.getmQuestion());

                mQuestionTextView.setText(mQuestion.getmQuestion());
                mOption1.setText(mQuestion.getmAnswer());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

//        mQuestionTextView.setText(mQuestion.getQuestion());
//        mQuestionDisplayTextView.setText(mQuestion.getQuestionDisplay());
//        mOption1.setText(mQuestion.getAnswer());
//
//        options = mQuestion.getWrongAnswers();
//
//        mOption2.setText(options.get(0));
//        mOption3.setText(options.get(1));
//        mOption4.setText(options.get(2));

        return rootView;
    }

}
