package com.armz.simplequestions;

import android.content.Context;
import android.content.Intent;
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
import java.util.Random;

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

public class QuestionFragment extends Fragment implements View.OnClickListener{
    private Question mQuestion;

    private TextView mQuestionTextView;
    private TextView mQuestionDisplayTextView;
    private Button mOption1;
    private Button mOption2;
    private Button mOption3;
    private Button mOption4;
    private TextView mHintTextView;
    private TextView mCorrectTextView;


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
        mOption1 = rootView.findViewById(R.id.button_One);
        mOption2 = rootView.findViewById(R.id.button_Two);
        mOption3 = rootView.findViewById(R.id.button_Three);
        mOption4 = rootView.findViewById(R.id.button_Four);


        mQuestionTextView.setText(mQuestion.getmQuestion());
        mQuestionDisplayTextView.setText(mQuestion.getmQuestionDisplay());
        mOption1.setText(mQuestion.getmAnswer());

        mHintTextView = (TextView) rootView.findViewById(R.id.hint_textView);
        mCorrectTextView = (TextView) rootView.findViewById(R.id.correctAnswer_textView);



        mQuestionTextView.setText(mQuestion.getmQuestion());
        mQuestionDisplayTextView.setText(mQuestion.getmQuestionDisplay());
        mHintTextView.setText(mQuestion.getmHint());




        int start = 0;
        int end = 3;
        Random random = new Random();
        long range = (long)end - (long) start + 1;
        long fraction = (long)(range * random.nextDouble());

        int randomNumb = (int) (fraction + start);

        switch (randomNumb){
            case 0:
                mOption1.setText(mQuestion.getmAnswer());
                mOption2.setText(mQuestion.getWrongAnswer1());
                mOption3.setText(mQuestion.getWrongAnswer2());
                mOption4.setText(mQuestion.getWrongAnswer3());
                break;
            case 1:
                mOption2.setText(mQuestion.getmAnswer());
                mOption3.setText(mQuestion.getWrongAnswer1());
                mOption4.setText(mQuestion.getWrongAnswer2());
                mOption1.setText(mQuestion.getWrongAnswer3());
                break;
            case 2:
                mOption3.setText(mQuestion.getmAnswer());
                mOption4.setText(mQuestion.getWrongAnswer1());
                mOption1.setText(mQuestion.getWrongAnswer2());
                mOption2.setText(mQuestion.getWrongAnswer3());
                break;
            case 3:
                mOption4.setText(mQuestion.getmAnswer());
                mOption1.setText(mQuestion.getWrongAnswer1());
                mOption2.setText(mQuestion.getWrongAnswer2());
                mOption3.setText(mQuestion.getWrongAnswer3());
                break;
        }

        //Setting listener
        mOption1.setOnClickListener(this);
        mOption2.setOnClickListener(this);
        mOption3.setOnClickListener(this);
        mOption4.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.button_One:
                displayFeedback(mOption1);
                break;

            case R.id.button_Two:
                displayFeedback(mOption2);
                break;

            case R.id.button_Three:
                displayFeedback(mOption3);
                break;

            case R.id.button_Four:
                displayFeedback(mOption4);
                break;

            default:
                break;
        }


    }

    private void displayFeedback(Button ButtonPressed){
        if(isRight(ButtonPressed.getText().toString())){
            mCorrectTextView.setText("Correct!");
            mCorrectTextView.setVisibility(View.VISIBLE);
        }else if(mCorrectTextView.getText().equals("Try Again!")){
            mCorrectTextView.setText("One more time!");
        }
        else{
            mCorrectTextView.setText("Try Again!");
            mCorrectTextView.setVisibility(View.VISIBLE);
        }
    }



    //Returns true if the button pressed is the right answer
    private Boolean isRight(String ButtonText){
        if(ButtonText.equals(mQuestion.getmAnswer())){
            return true;
        }else{
            return false;
        }

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
