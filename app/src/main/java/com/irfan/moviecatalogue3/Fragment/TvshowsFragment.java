package com.irfan.moviecatalogue3.Fragment;


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
import android.widget.ProgressBar;

import com.irfan.moviecatalogue3.Adapter.MovieAdapter;
import com.irfan.moviecatalogue3.Adapter.TvshowAdapter;
import com.irfan.moviecatalogue3.Model.Result;
import com.irfan.moviecatalogue3.R;
import com.irfan.moviecatalogue3.ViewModel.MainViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowsFragment extends Fragment {

    private TvshowAdapter tvshowAdapter;
    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;
    private ProgressBar progressBar;

    private Observer<ArrayList<Result>> getTvshows = new Observer<ArrayList<Result>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Result> results) {
            if (results != null){
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                tvshowAdapter = new TvshowAdapter(results, getContext());
                recyclerView.setAdapter(tvshowAdapter);
                showLoading(false);
            }
        }
    };

    public TvshowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tvshows, container, false);
        recyclerView = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.progress_bar_tvshow);
        showLoading(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mainViewModel.getListTv(getActivity()).observe(getActivity(), getTvshows);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
