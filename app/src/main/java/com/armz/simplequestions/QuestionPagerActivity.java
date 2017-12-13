package com.armz.simplequestions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class QuestionPagerActivity extends AppCompatActivity {

    private static final String EXTRA_QUESTION_ID =
            "com.armz.simplequestions.question_id";

    private ViewPager mViewPager;
    private List<Question> mQuestions;

    public static Intent newIntent(Context packageContext, int questionID) {
        Intent intent = new Intent(packageContext, QuestionPagerActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_pager);

        int questionID = (int) getIntent()
                .getSerializableExtra(EXTRA_QUESTION_ID);

        mViewPager = (ViewPager) findViewById(R.id.question_view_pager);


        mQuestions = QuestionLab.get(this).getQuestions();
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                System.out.println("GETTING NEW POSITION");
                Question question = mQuestions.get(position);
                return QuestionFragment.newInstance(question.getmID());

            }

            @Override
            public int getCount() {
                return mQuestions.size();
            }
        });



        for(int i = 0; i < mQuestions.size(); i++){
            if (mQuestions.get(i).getmID() == questionID){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
