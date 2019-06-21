package com.example.android.nhlresults;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.nhlresults.data.Game;
import com.example.android.nhlresults.data.LeagueRecord;
import com.example.android.nhlresults.data.ResultsViewModel;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsFragment extends Fragment {

    public static final String ARG_DATE = "date";

    private RecyclerView mRecyclerView;
    private List<Game> mItems = new ArrayList<>();

    //create new instance of the fragment
    public static ResultsFragment newInstance(){
        return new ResultsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resultlist, container, false);

        ResultsViewModel resultsViewModel = ViewModelProviders.of(this).get(ResultsViewModel.class);

        mRecyclerView = view.findViewById(R.id.results_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setRetainInstance(true);

        Bundle args = getArguments();

        resultsViewModel.getData(args.getString(ARG_DATE)).observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                if (games != null){
                    mItems = games;
                    updateUI();
                    setupAdapter();
                } else {
                    Toast.makeText(getContext(), R.string.error_message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        updateUI();
    }

    class ResultHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        @BindView(R.id.status_text_view)
        TextView mStatusTextView;

        @BindView(R.id.home_team_text_view)
        TextView mHomeTeamTextView;

        @BindView(R.id.away_team_text_view)
        TextView mAwayTeamTextView;

        @BindView(R.id.arena_text_view)
        TextView mArenaTextView;

        @BindView(R.id.home_team_record_text_view)
        TextView mHomeTeamRecordTextView;

        @BindView(R.id.away_team_record_text_view)
        TextView mAwayTeamRecordTextView;

        public ResultHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view){
            /*Intent intent = DetailedViewActivity.newIntent(getActivity(), listItem.getId(), tabIdx);
            startActivity(intent);*/
        }

        public void bindArticle(Game item){
            mStatusTextView.setText(item.getStatus().getDetailedState());
            mHomeTeamTextView.setText(item.getTeams().getHome().getTeam().getName());
            mAwayTeamTextView.setText(item.getTeams().getAway().getTeam().getName());
            mHomeTeamRecordTextView.setText(convertTeamRecord(item.getTeams().getHome().getLeagueRecord()));
            mAwayTeamRecordTextView.setText(convertTeamRecord(item.getTeams().getAway().getLeagueRecord()));
            mArenaTextView.setText(item.getVenue().getName());
        }
    }

    private class ResultAdapter extends RecyclerView.Adapter<ResultHolder>{
        private List<Game> mResults;

        public ResultAdapter(List<Game> results){
            mResults = results;
        }

        @Override
        public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.resultlist_item_view, parent, false);
            return new ResultHolder(view);
        }

        @Override
        public void onBindViewHolder(ResultHolder holder, int position){
            Game listItem = mResults.get(position);
            holder.bindArticle(listItem);
        }

        @Override
        public int getItemCount(){
            //TODO: Protection if there are no games on this day
            return mResults.size();
        }
    }

    private void setupAdapter(){
        if(isAdded()){
            mRecyclerView.setAdapter(new ResultAdapter(mItems));
        }
    }

    private void updateUI(){
        if (mRecyclerView.getAdapter() == null){
            setupAdapter();
        } else{
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    private String convertTeamRecord(LeagueRecord record){
        String stringRecord;
        stringRecord = String.valueOf(record.getWins())+"-"
                        +String.valueOf(record.getLosses())+"-"
                        +String.valueOf(record.getOt());

        return stringRecord;
    }
}
