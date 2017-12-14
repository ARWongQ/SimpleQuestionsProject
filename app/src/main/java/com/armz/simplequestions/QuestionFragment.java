package com.armz.simplequestions;


import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


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
    private TextView mHintTextView;


    private static final String ARG_QUESTION_ID = "question_id";

    //Gyro Sensor for hint
    private SensorManager mSensorManager;
    private Sensor mProximitySensor;




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

        //Proximity
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mProximitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
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
        mHintTextView = (TextView) rootView.findViewById(R.id.hint_textView);



        mQuestionTextView.setText(mQuestion.getQuestion());
        mQuestionDisplayTextView.setText(mQuestion.getQuestionDisplay());
        mOption1.setText(mQuestion.getAnswer());
        mHintTextView.setText(mQuestion.getHint());
        mHintTextView.setVisibility(View.INVISIBLE);



        mOption2.setText(mQuestion.getWrongAnswer1());
        mOption3.setText(mQuestion.getWrongAnswer2());
        mOption4.setText(mQuestion.getWrongAnswer3());



        return rootView;
    }



    //My proximity Sensor
    public SensorEventListener proxListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.values[0] < mProximitySensor.getMaximumRange()) {
                mHintTextView.setVisibility(View.VISIBLE);
            } else {
                mHintTextView.setVisibility(View.INVISIBLE);

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onStop(){
        super.onStop();

        //Proximity
        mSensorManager.unregisterListener(proxListener);


    }

    @Override
    public void onResume(){
        super.onResume();

        //Proximity
        mSensorManager.registerListener(proxListener,mProximitySensor, 2*1000*1000);



    }

}
