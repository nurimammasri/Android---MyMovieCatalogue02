package com.dicoding.mymoviecatalog.Fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.mymoviecatalog.Adapter.CardViewTVShowsAdapter;
import com.dicoding.mymoviecatalog.Data.TVShowsData;
import com.dicoding.mymoviecatalog.R;

import java.util.ArrayList;

public class TVShowsFragment extends Fragment {
    private ArrayList<TVShowsData> list = new ArrayList<>();
    private RecyclerView rvTVShows;


    public TVShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTVShows = view.findViewById(R.id.rv_tvshows);
        rvTVShows.setHasFixedSize(true);

        list.addAll(getListTVShows());
        showRecyclerCardView();

    }


    private ArrayList<TVShowsData> getListTVShows() {
        //ARRAY UNTUK SIMPAN DATA STRING ARRAY
        String[] dataTitle, dataYear, dataRate, dataOverview,
                dataTopBilledCast, dataRelease, dataOriginalLanguage,
                dataRunTime, dataGenre, dataCodeYoutube;

        TypedArray dataPoster;

        dataPoster = getResources().obtainTypedArray(R.array.tvshows_poster);


        dataTitle = getResources().getStringArray(R.array.tvshows_title);
        dataOverview = getResources().getStringArray(R.array.tvshows_overview);
        dataGenre = getResources().getStringArray(R.array.tvshows_genre);
        dataRelease = getResources().getStringArray(R.array.tvshows_status);
        dataRate = getResources().getStringArray(R.array.tvshows_score);
        dataOriginalLanguage = getResources().getStringArray(R.array.tvshows_originalLg);
        dataRunTime = getResources().getStringArray(R.array.tvshows_runtime);
        dataYear = getResources().getStringArray(R.array.tvshows_year);
        dataTopBilledCast = getResources().getStringArray(R.array.tvshows_topBilledCast);
        dataCodeYoutube = getResources().getStringArray(R.array.tvshows_youtube);

        ArrayList<TVShowsData> listTVShows = new ArrayList<>();


        for (int i = 0; i < dataTitle.length; i++) {
            TVShowsData tvshows = new TVShowsData();
            tvshows.setPoster(dataPoster.getResourceId(i, -1));
            tvshows.setTitle(dataTitle[i]);
            tvshows.setOverview(dataOverview[i]);
            tvshows.setGenre(dataGenre[i]);
            tvshows.setStatus(dataRelease[i]);
            tvshows.setScore(dataRate[i]);
            tvshows.setOriginalLanguage(dataOriginalLanguage[i]);
            tvshows.setRuntime(dataRunTime[i]);
            tvshows.setYear(dataYear[i]);
            tvshows.setTopBilledCast(dataTopBilledCast[i]);
            tvshows.setYoutube(dataCodeYoutube[i]);


            listTVShows.add(tvshows);
        }

        dataPoster.recycle(); //digunankan untuk dealokasi
        return listTVShows;
    }

    private void showRecyclerCardView() {
        rvTVShows.setLayoutManager(new LinearLayoutManager(this.getContext()));
        CardViewTVShowsAdapter cardViewTVShowsAdapter = new CardViewTVShowsAdapter(list);
        rvTVShows.setAdapter(cardViewTVShowsAdapter);

        cardViewTVShowsAdapter.setOnItemClickCallback(new CardViewTVShowsAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TVShowsData data) {
                showSelectedTVShows(data);
            }
        });
    }

    private void showSelectedTVShows(TVShowsData data) {
        Toast.makeText(this.getContext(), "Kamu memilih " + data.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
