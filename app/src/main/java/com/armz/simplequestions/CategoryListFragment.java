package com.armz.simplequestions;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by augustowong on 12/13/17.
 */

public class CategoryListFragment  extends Fragment {
    private RecyclerView mCategoryRecyclerView;
    private CategoryAdapter mAdapter;
    //private String currentUser;
//    private FirebaseDatabase mFirebaseDatabase;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    private DatabaseReference myRef;
    private  String username;
    // hold all categories read from Firebase
    final List<Category> newCategories = new ArrayList<Category>();
    List<String> userCategories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        //inflate the view
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        mCategoryRecyclerView = (RecyclerView) view.findViewById(R.id.category_recycler_view);

        //Recycler view requires a Layout Manager to work
        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle bundle = this.getArguments();
        username = bundle.getString("username");

        updateUI();

        return view;
    }



    private void updateUI(){


        getUserCategories();
        getCategoriesFromFirebase();


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

    private void getCategoriesFromFirebase(){

        mAdapter = new CategoryAdapter(newCategories);

        // read Categories from Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Category category = new Category();
                    category.setName(ds.getValue(Category.class).getName()); //set the name
                    category.setHasPermission(ds.getValue(Category.class).getHasPermission());
                    category.setCost(ds.getValue(Category.class).getCost());

                    //display all the information
                    Log.d(TAG, "showData: name: " + category.getName());
                    Log.d(TAG, "showData: hasPermission: " + category.getHasPermission());
                    Log.d(TAG, "showData: cost: " + category.getCost());


                    if (userCategories.contains(category.getName())) {
                        // Add to list of categories
                        // only if user has paid for it
                        newCategories.add(category);
                        // And create recycler view adapter with the changes
                        mCategoryRecyclerView.setAdapter(mAdapter);
                    }



                    Log.d(TAG, "showData: size of category " + newCategories.size());
                    Log.d(TAG, "showData: size of children " + dataSnapshot.getChildrenCount());
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void getUserCategories() {

        Log.d(TAG, "This is in CategoryListFragment");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User u = new User();
                    u.setUsername(ds.getValue(User.class).getUsername()); //set the name
                    u.setLevel(ds.getValue(User.class).getLevel());
                    u.setMoney(ds.getValue(User.class).getMoney());
                    u.setBoughtCategories((ArrayList<String>) ds.child("boughtCategories").getValue());

                    userCategories = u.getBoughtCategories();

                    //display all the information
                    Log.d(TAG, "showData: name: " + u.getUsername());
                    Log.d(TAG, "showData: level: " + u.getLevel());
                    Log.d(TAG, "showData: money: " + u.getMoney());
                    Log.d(TAG, "showData: boughtCategories0 " + u.getBoughtCategories().get(0));

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}
