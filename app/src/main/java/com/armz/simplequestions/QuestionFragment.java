package com.armz.simplequestions;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static android.widget.CompoundButton.*;

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


    public static QuestionFragment newInstance(UUID questionID) {
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
        UUID questionId = (UUID) getArguments().getSerializable(ARG_QUESTION_ID);
        mQuestion = QuestionLab.get(getActivity()).getQuestion(questionId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestionTextView = (TextView) rootView.findViewById(R.id.question_textView);
        mQuestionDisplayTextView = (TextView) rootView.findViewById(R.id.questionDisplay_textView);
        mOption1 = (Button) rootView.findViewById(R.id.button_One);
        mOption2 = (Button) rootView.findViewById(R.id.button_Two);
        mOption3 = (Button) rootView.findViewById(R.id.button_Three);
        mOption4 = (Button) rootView.findViewById(R.id.button_Four);



        mQuestionTextView.setText(mQuestion.getQuestion());
        mQuestionDisplayTextView.setText(mQuestion.getQuestionDisplay());
        mOption1.setText(mQuestion.getAnswer());

        options = mQuestion.getWrongAnswers();

        mOption2.setText(options.get(0));
        mOption3.setText(options.get(1));
        mOption4.setText(options.get(2));

        return rootView;
    }

}
