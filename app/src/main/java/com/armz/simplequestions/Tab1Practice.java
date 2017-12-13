package com.armz.simplequestions;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by augustowong on 12/9/17.
 */

public class Tab1Practice extends Fragment {
    private Button myTestingButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1practice, container, false);

        myTestingButton = (Button) rootView.findViewById(R.id.button_category_one);
        myTestingButton.setText("CATEGORY ONE");
        myTestingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Method from the other activity to pass the data
                Intent intent = QuestionActivity.newIntent(Tab1Practice.this.getContext() , myTestingButton.getText().toString());
                startActivity(intent);


            }
        });

        return rootView;
    }
}
