package com.example.android.nhlresults;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.nhlresults.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment resultFragment = ResultsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.result_fragment_container, resultFragment)
                .commit();

    }

    /**
     * This method fires off an AsyncTask to perform the GET request using {NHLQueryTask}
     */
    private void makeNHLResultsQuery() {


    }
}
