package com.armz.simplequestions;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "com.armz.simplequestions.name_title";

    //textView and Button variables
    private TextView mTitleTextView;

    private ViewPager mViewPager;
    private SectionsPagerAdapterTwo mSectionsPagerAdapter;

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

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container_two);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mSectionsPagerAdapter = new SectionsPagerAdapterTwo(fragmentManager);
        mViewPager.setAdapter(mSectionsPagerAdapter);



    }

    public class SectionsPagerAdapterTwo extends FragmentPagerAdapter {

        public SectionsPagerAdapterTwo(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Returns the desired fragment
            switch (position){
                case 0:
                    QuestionListFragment tab3 = new QuestionListFragment();
                    return tab3;

                default:
                    System.out.println("Tab not found");
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 1;
        }


    }
}
