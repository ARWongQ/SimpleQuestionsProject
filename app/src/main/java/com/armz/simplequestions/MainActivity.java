package com.armz.simplequestions;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    private SensorManager mSensorManager;
    private Sensor mGyro;
    private ArrayList<String> mGreetings = new ArrayList<>();



    //Making sure everything is set up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //Gyro
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mGyro = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mGreetings.add("Thanks for joining");
        mGreetings.add("Enjoy your time!");
        mGreetings.add("There is still much to learn");
        mGreetings.add("Go try some new questions");
        mGreetings.add("Try new categories");
        mGreetings.add("Check the ranking");
        mGreetings.add("Check the store for new categories");
        mGreetings.add("Don't forget to study");
        mGreetings.add("You are doing a good job");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Returns the desired fragment
            switch (position){
                case 0:
                    CategoryListFragment tab1 = new CategoryListFragment();
                    return tab1;

                case 1:
                    AccomplishmentListFragment tab2 = new AccomplishmentListFragment();
                    return tab2;

                case 2:
                    RankingListFragment tab3 = new RankingListFragment();
                    return tab3;

                case 3:
                    StoreListFragment tab4 = new StoreListFragment();
                    return tab4;

                default:
                    System.out.println("Tab not found");
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }


    }

    //My Gyro Data
    public SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            int intY = (int) y;

            //Get a random greeting to display to the user
            if(intY >= 3){
                int start = 0;
                int end = 8;
                Random random = new Random();
                long range = (long)end - (long) start + 1;
                long fraction = (long)(range * random.nextDouble());

                int randomNumb = (int) (fraction + start);
                String display = mGreetings.get(randomNumb);

                Toast myToast= Toast.makeText(MainActivity.this, display ,Toast.LENGTH_SHORT);

                try{
                    myToast.getView().isShown();

                }catch(Exception e){
                    myToast = Toast.makeText(MainActivity.this , display, Toast.LENGTH_LONG);
                }
                myToast.show();
            }


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onStop(){
        super.onStop();
        //Gyro
        mSensorManager.unregisterListener(gyroListener);

    }

    @Override
    public void onResume(){
        super.onResume();
        //Gyro
        mSensorManager.registerListener(gyroListener,mGyro, mSensorManager.SENSOR_DELAY_NORMAL);

    }
}
