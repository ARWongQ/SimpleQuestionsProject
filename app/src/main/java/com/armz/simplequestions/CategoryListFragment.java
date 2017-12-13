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
 * Created by augustowong on 12/13/17.
 */

public class CategoryListFragment  extends Fragment {
    private RecyclerView mCategoryRecyclerView;
    private CategoryAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        mCategoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);

        //Recycler view requires a Layout Manager to work
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
            categories.add(cur);
        }



        mAdapter = new CategoryAdapter(categories);
        mCategoryRecyclerView.setAdapter(mAdapter);
    }


    //VIEW_HOLDER  (Detects presses)
    private class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Parts of the widget
        private TextView mDescriptionTextView;
        private TextView mProgressTextView;
        private ImageView mDoneAccomplishment;


        private Category mCategory;


        public CategoryHolder(LayoutInflater inflater, ViewGroup parent){

            super(inflater.inflate(R.layout.list_item_accomplishment,parent,false));
            itemView.setOnClickListener(this);

            mDescriptionTextView = (TextView) itemView.findViewById(R.id.accomplishment_description);
            mProgressTextView= (TextView) itemView.findViewById(R.id.accomplishmentPercentage);
            mDoneAccomplishment = (ImageView) itemView.findViewById(R.id.accomplishmentDone);
        }


        public void bind(Category category){
            mCategory = category;
            mDescriptionTextView.setText(mCategory.getName());
            mProgressTextView.setText(mCategory.getHasPermission()? " " :"90%");
            mDoneAccomplishment.setVisibility(mCategory.getHasPermission()? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View view){
            if(mCategory.getHasPermission()){
                //Method from the other activity to pass the data
                Intent intent = QuestionActivity.newIntent(CategoryListFragment.this.getContext() , mCategory.getName());
                startActivity(intent);
            }
            Toast.makeText(getActivity(),"Please Buy on the Store", Toast.LENGTH_SHORT).show();


        }
    }

    //ADAPTER
    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder>{
        private List<Category> mCategories;

        public CategoryAdapter(List<Category> categories){
            mCategories = categories;
        }

        //Called whenever there is a need of a new ViewHolder to display an item with
        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CategoryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {
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
