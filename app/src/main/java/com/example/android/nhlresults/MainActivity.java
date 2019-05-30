package com.example.android.nhlresults;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.nhlresults.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static Date sPagerViewDate;

    public static Deque<Date> sDateCollection;

    @BindView(R.id.result_fragment_container)
    ViewPager mViewPager;

    ResultsPagerAdapter mResultsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        sDateCollection = new LinkedList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 01, 15);
        sPagerViewDate = calendar.getTime();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mResultsPagerAdapter = new ResultsPagerAdapter(fragmentManager);
        mViewPager.setAdapter(mResultsPagerAdapter);
        mViewPager.setCurrentItem(50, false);
        /*Fragment resultFragment = ResultsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.result_fragment_container, resultFragment)
                .commit();*/


    }

    /**
     * This method fires off an AsyncTask to perform the GET request using {NHLQueryTask}
     */
    private void makeNHLResultsQuery() {


    }
}
