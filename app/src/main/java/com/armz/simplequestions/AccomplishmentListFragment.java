package com.armz.simplequestions;

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

/**
 * Created by augustowong on 12/12/17.
 */

public class AccomplishmentListFragment extends Fragment {
    private RecyclerView mAccomplishmentRecyclerView;
    private AccomplishmentAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_accomplishment_list, container, false);

        mAccomplishmentRecyclerView = (RecyclerView) view.findViewById(R.id.accomplishment_recycler_view);

        //Recycler view requires a Layout Manager to work
        mAccomplishmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI(){

        List<Accomplishment> accomplishments = new ArrayList<Accomplishment>();
        Accomplishment cur;
        String goalDesc;
        for(int i= 1; i < 4; i++){
            goalDesc = "Accomplishment #" + i;
            cur = new Accomplishment(i, goalDesc, 2, 4, false);
            accomplishments.add(cur);
        }



        mAdapter = new AccomplishmentAdapter(accomplishments);
        mAccomplishmentRecyclerView.setAdapter(mAdapter);
    }


    //VIEW_HOLDER  (Detects presses)
    private class AccomplishmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Parts of the widget
        private TextView mDescriptionTextView;
        private TextView mProgressTextView;
        private ImageView mDoneAccomplishment;


        private Accomplishment mAccomplishment;


        public AccomplishmentHolder(LayoutInflater inflater, ViewGroup parent){

            super(inflater.inflate(R.layout.list_item_accomplishment,parent,false));
            itemView.setOnClickListener(this);

            mDescriptionTextView = (TextView) itemView.findViewById(R.id.accomplishment_description);
            mProgressTextView= (TextView) itemView.findViewById(R.id.accomplishmentPercentage);
            mDoneAccomplishment = (ImageView) itemView.findViewById(R.id.accomplishmentDone);





        }


        public void bind(Accomplishment accomplishment){
            mAccomplishment = accomplishment;
            mDescriptionTextView.setText(accomplishment.getGoalDescription());

            mProgressTextView.setText(mAccomplishment.getId()%2 == 0? " " :"90%");
            mDoneAccomplishment.setVisibility(mAccomplishment.getId()%2 == 0? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View view){
            if(mAccomplishment.getId() % 2 == 0){
                Toast.makeText(getActivity(),"Accomplishment Completed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getActivity(),"Keep Playing!", Toast.LENGTH_SHORT).show();


        }
    }

    //ADAPTER
    private class AccomplishmentAdapter extends RecyclerView.Adapter<AccomplishmentHolder>{
        private List<Accomplishment> mAccomplishments;

        public AccomplishmentAdapter(List<Accomplishment> accomplishments){
            mAccomplishments = accomplishments;
        }

        //Called whenever there is a need of a new ViewHolder to display an item with
        @Override
        public AccomplishmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new AccomplishmentHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AccomplishmentHolder holder, int position) {
            Accomplishment acc = mAccomplishments.get(position);
            //binds it with the widget
            holder.bind(acc);
        }

        @Override
        public int getItemCount() {
            return mAccomplishments.size();
        }
    }

}
