package com.armz.simplequestions;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "com.armz.simplequestions.name_title";

    //textView and Button variables
    private TextView mTitleTextView;

    //Knows the implementation details from QuizActivity (like parameter this case answerIsTrue)
    public static Intent newIntent(Context packageContext, String nameTitle){
        //not entirely sure what it does
        Intent intent = new Intent(packageContext, QuestionActivity.class);
        intent.putExtra(EXTRA_NAME, nameTitle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mTitleTextView = (TextView) findViewById(R.id.title_TextView);
        mTitleTextView.setText(getIntent().getStringExtra(EXTRA_NAME));

    }
}
