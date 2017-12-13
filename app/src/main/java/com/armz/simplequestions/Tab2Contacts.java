package com.armz.simplequestions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by augustowong on 12/9/17.
 */

public class Tab2Contacts  extends Fragment {

    private Button mTestingbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2contacts, container, false);



        //TESTING
        mTestingbutton = (Button) rootView.findViewById(R.id.swipeRight);


        mTestingbutton.setOnClickListener(new View.OnClickListener() {
            boolean test = false;
            @Override
            public void onClick(View v) {

                if(test){
                    mTestingbutton.setText("NOT TESTING");
                }else{
                    mTestingbutton.setText("TESTING");
                }

                test = !test;
            }
        });

        return rootView;
    }


}


