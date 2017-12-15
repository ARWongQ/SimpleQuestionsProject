package com.armz.simplequestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by augustowong on 12/9/17.
 */

public class QuestionListFragment extends Fragment {
    private RecyclerView mQuestionRecyclerView;
    private String categoryName;
    private QuestionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        mQuestionRecyclerView = (RecyclerView) view.findViewById(R.id.question_recycler_view);

        //Recycler view requires a Layout Manager to work
        mQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryName = this.getArguments().getString("CategoryName");

        updateUI();

        return view;
    }

    //sets up CrimeListFragment's UI
    private void updateUI(){

        final List<Question> questions = new ArrayList<>();
        mAdapter = new QuestionAdapter(questions);

        // Read questions for this specific category from Firebase
        // read Categories from Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    if (ds.getKey().equals(categoryName)) {

                        Log.d(TAG, "Category Name: " + categoryName);
                        Log.d(TAG, "Child name: " + ds.child("questions").getValue());

                        Iterable<DataSnapshot> it = ds.child("questions").getChildren();
                        while(it.iterator().hasNext()) {
                            DataSnapshot ds2 = it.iterator().next();

                            Question question = new Question();
                            question.setmID(ds2.getValue(Question.class).getmID()); //set the name
                            question.setmQuestionDisplay("Problem " + question.getmID());
                            question.setmQuestion(ds2.getKey());
                            question.setmAnswer(ds2.getValue(Question.class).getmAnswer());
//                            question.setMhasPassed(ds2.getValue(Question.class).getMhasPassed());
                            question.setmHint(ds2.getValue(Question.class).getmHint());
                            List<String> wrongAnswers = new ArrayList<String>();
                            question.setWrongAnswer1(ds2.getValue(Question.class).getWrongAnswer1());
                            question.setWrongAnswer2(ds2.getValue(Question.class).getWrongAnswer2());
                            question.setWrongAnswer3(ds2.getValue(Question.class).getWrongAnswer3());

                            wrongAnswers.add(question.getWrongAnswer1());
                            wrongAnswers.add(question.getWrongAnswer2());
                            wrongAnswers.add(question.getWrongAnswer3());
                            question.setWrongAnswers(wrongAnswers);

                            //display all the information
                            Log.d(TAG, "showData: ID: " + question.getmID());
                            Log.d(TAG, "showData: mQuestionDisplay: " + question.getmQuestionDisplay());
                            Log.d(TAG, "showData: mQuestion: " + question.getmQuestion());
                            Log.d(TAG, "showData: mAnswer: " + question.getmAnswer());
                            Log.d(TAG, "showData: mHasPassed: " + question.getMhasPassed());
                            Log.d(TAG, "showData: wrongAnswer1: " + question.getWrongAnswers().get(0));
                            Log.d(TAG, "showData: wrongAnswer2: " + question.getWrongAnswers().get(1));
                            Log.d(TAG, "showData: wrongAnswer3: " + question.getWrongAnswers().get(2));

                            // Add to list of categories
                            questions.add(question);
                            // And create recycler view adapter with the changes
                            mQuestionRecyclerView.setAdapter(mAdapter);

                            Log.d(TAG, "showData: size of category " + questions.size());
                            Log.d(TAG, "showData: size of children " + dataSnapshot.getChildrenCount());

                            QuestionLab.get(getContext()).setmQuestions(questions);
                        }
                    }
                        }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }


    //VIEW_HOLDER  (Detects presses)
    private class QuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Parts of the widget
        private TextView mQuestionTextView;
        private ImageView mSolvedImageView;


        private Question mQuestion;


        public QuestionHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_question,parent,false));
            itemView.setOnClickListener(this);

            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_question);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.question_solved);


        }


        public void bind(Question question){
            mQuestion = question;
            mQuestionTextView.setText("Problem " + question.getmID());
            //mSolvedImageView.setVisibility(mQuestion.getMhasPassed() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(),mQuestion.getmID() + " clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = QuestionPagerActivity.newIntent(getActivity(), mQuestion.getmID());
            startActivity(intent);
        }
    }

    //ADAPTER
    private class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder>{
        private List<Question> mQuestions;

        public QuestionAdapter(List<Question> questions){
            mQuestions = questions;
        }

        //Called whenever there is a need of a new ViewHolder to display an item with
        @Override
        public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());


            return new QuestionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(QuestionHolder holder, int position) {
            Question crime = mQuestions.get(position);
            //binds it with the widget
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
    }

}
