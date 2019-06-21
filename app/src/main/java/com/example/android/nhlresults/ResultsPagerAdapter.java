package com.example.android.nhlresults;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ResultsPagerAdapter extends FragmentStatePagerAdapter {

    public ResultsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment resultFragment = ResultsFragment.newInstance();
        Bundle args = new Bundle();
        args.putString(ResultsFragment.ARG_DATE, getDate(i));
        resultFragment.setArguments(args);
        return resultFragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getDate(position);
    }

    private String getDate(int i){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(MainActivity.sPagerViewDate);
        calendar.add(Calendar.DAY_OF_YEAR, i-50);
        Date currentViewDate = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return df.format(currentViewDate);
    }
}
