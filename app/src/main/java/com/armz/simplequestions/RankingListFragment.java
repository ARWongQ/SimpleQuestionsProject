package com.armz.simplequestions;

/**
 * Created by augustowong on 12/13/17.
 */

import android.graphics.Color;
import android.media.Image;
import android.support.v4.app.Fragment;

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

import java.util.ArrayList;
import java.util.List;

public class RankingListFragment extends Fragment {
    private RecyclerView mRankingRecyclerView;
    private RankingAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_ranking_list, container, false);

        mRankingRecyclerView = (RecyclerView) view.findViewById(R.id.ranking_recycler_view);

        //Recycler view requires a Layout Manager to work
        mRankingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI(){

        List<User> users = new ArrayList<User>();
        User cur;
        String name;
        for(int i= 1; i < 6; i++){
            name = "User #" + i;
            cur = new User(name, "abc", i^2, i^4, i*10, i);

            users.add(cur);
        }



        mAdapter = new RankingAdapter(users);
        mRankingRecyclerView.setAdapter(mAdapter);
    }


    //VIEW_HOLDER  (Detects presses)
    private class RankingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Parts of the widget
        private TextView mDescriptionTextView;
        private TextView mLevelTextView;
        private TextView mRankingTextView;
        private ImageView mTopRank;



        private User mUser;


        public RankingHolder(LayoutInflater inflater, ViewGroup parent){

            super(inflater.inflate(R.layout.list_item_ranking,parent,false));
            itemView.setOnClickListener(this);

            mDescriptionTextView = (TextView) itemView.findViewById(R.id.username_TextView);
            mLevelTextView = (TextView) itemView.findViewById(R.id.level_TextView);
            mTopRank = (ImageView) itemView.findViewById(R.id.topRanked_image);
            mRankingTextView = (TextView) itemView.findViewById(R.id.ranking_TextView);


        }


        public void bind(User user){
            mUser = user;
            mDescriptionTextView.setText(mUser.getUsername());
            String levelDescr = "lvl " + mUser.getLevel();
            String ranking = "#" + mUser.getRanking();
            mLevelTextView.setText(levelDescr);

            float fl;
            switch (mUser.getRanking()){
                case 1:
                    fl = 28;
                    mRankingTextView.setTextColor(Color.parseColor("#e5c100"));
                    break;
                case 2:
                    fl = 25;

                    mRankingTextView.setTextColor(Color.parseColor("#c0c0c0"));
                    break;
                case 3:
                    fl = 23;

                    mRankingTextView.setTextColor(Color.parseColor("#cd7f32"));
                    break;
                default:
                    fl = 21;
            }

            mRankingTextView.setTextSize(fl);

            mTopRank.setVisibility(mUser.getRanking() < 4 ? View.VISIBLE : View.GONE);
            mRankingTextView.setText(ranking);

        }

        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(),"You are close!", Toast.LENGTH_SHORT).show();
        }
    }

    //ADAPTER
    private class RankingAdapter extends RecyclerView.Adapter<RankingHolder>{
        private List<User> mUsers;

        public RankingAdapter(List<User> users){
            mUsers = users;
        }

        //Called whenever there is a need of a new ViewHolder to display an item with
        @Override
        public RankingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new RankingHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RankingHolder holder, int position) {
            User us = mUsers.get(position);
            //binds it with the widget
            holder.bind(us);
        }

        @Override
        public int getItemCount() {
            return mUsers.size();
        }
    }

}
