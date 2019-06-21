package com.example.android.nhlresults.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.android.nhlresults.R;
import com.example.android.nhlresults.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ResultsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Game>> data;

    public ResultsViewModel(Application application){
        super(application);
    }

    public LiveData<List<Game>> getData(String date){
        if(data==null){
            data = new MutableLiveData<>();
            URL nhlResultsUrl = NetworkUtils.buildUrl(date);
            loadData(nhlResultsUrl);
        }
        return data;
    }

    private void loadData(URL nhlResultsUrl){
        new AsyncTask<URL, Void, String>() {
            @Override
            protected String doInBackground(URL... params) {

                URL nhlResultsUrl = params[0];
                String nhlResults = null;
                try {
                    nhlResults = NetworkUtils.getResponseFromHttpUrl(nhlResultsUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return nhlResults;
            }

            @Override
            protected void onPostExecute(String nhlResults) {
                if (nhlResults != null && !nhlResults.equals("")) {
                    MatchDaySchedule matchDaySchedule = MatchDaySchedule.parseJSON(nhlResults);
                    if (!matchDaySchedule.getDates().isEmpty()) {
                        data.setValue(matchDaySchedule.getDates().get(0).getGames());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(nhlResultsUrl);
    }
}
