package com.armz.simplequestions;

/**
 * Created by augustowong on 12/13/17.
 */

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

public class StoreListFragment extends Fragment {
    private RecyclerView mStoreRecyclerView;
    private StoreAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);

        mStoreRecyclerView = (RecyclerView) view.findViewById(R.id.store_recycler_view);

        //Recycler view requires a Layout Manager to work
        mStoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI(){

        List<Category> categories = new ArrayList<Category>();
        Category cur;
        String cat;
        for(int i= 1; i < 4; i++){
            cat = "Category #" + i;
            cur = new Category(cat, i%2==0);
            if(!cur.getHasPermission()){
                categories.add(cur);
            }

        }

        cur = new Category("Blocked Category", false, 20);
        categories.add(cur);


        mAdapter = new StoreAdapter(categories);
        mStoreRecyclerView.setAdapter(mAdapter);
    }


    //VIEW_HOLDER  (Detects presses)
    private class StoreHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Parts of the widget
        private TextView mDescriptionTextView;
        private TextView mProgressTextView;
        private ImageView mDoneAccomplishment;


        private Category mCategory;


        public StoreHolder(LayoutInflater inflater, ViewGroup parent){

            super(inflater.inflate(R.layout.list_item_accomplishment,parent,false));
            itemView.setOnClickListener(this);

            mDescriptionTextView = (TextView) itemView.findViewById(R.id.accomplishment_description);
            mProgressTextView= (TextView) itemView.findViewById(R.id.accomplishmentPercentage);
            mDoneAccomplishment = (ImageView) itemView.findViewById(R.id.accomplishmentDone);
        }


        public void bind(Category category){
            mCategory = category;
            mDescriptionTextView.setText(mCategory.getName());
            String cost = "$" + mCategory.getCost();
            mProgressTextView.setText(mCategory.getHasPermission()? " " : cost);
            mDoneAccomplishment.setVisibility(mCategory.getHasPermission()? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View view){

            if(!mCategory.getHasPermission()){
                Toast.makeText(getActivity(),"Bought", Toast.LENGTH_SHORT).show();
            }



        }
    }

    //ADAPTER
    private class StoreAdapter extends RecyclerView.Adapter<StoreHolder>{
        private List<Category> mCategories;

        public StoreAdapter(List<Category> categories){
            mCategories = categories;
        }

        //Called whenever there is a need of a new ViewHolder to display an item with
        @Override
        public StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new StoreHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(StoreHolder holder, int position) {
            Category category = mCategories.get(position);
            //binds it with the widget
            holder.bind(category);
        }

        @Override
        public int getItemCount() {
            return mCategories.size();
        }
    }

}
