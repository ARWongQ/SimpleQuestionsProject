package com.armz.simplequestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by augustowong on 12/9/17.
 */

public class QuestionListFragment extends Fragment {
    private RecyclerView mQuestionRecyclerView;
    private QuestionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        mQuestionRecyclerView = (RecyclerView) view.findViewById(R.id.question_recycler_view);

        //Recycler view requires a Layout Manager to work
        mQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    //sets up CrimeListFragment's UI
    private void updateUI(){
        QuestionLab questionLab = QuestionLab.get(getActivity());
        List<Question> questions = questionLab.getQuestions();

        mAdapter = new QuestionAdapter(questions);
        mQuestionRecyclerView.setAdapter(mAdapter);
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
            mQuestionTextView.setText(mQuestion.getQuestionDisplay());
            //mSolvedImageView.setVisibility(mQuestion.getMhasPassed() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(),mQuestion.getID() + " clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = QuestionPagerActivity.newIntent(getActivity(), mQuestion.getID());
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
