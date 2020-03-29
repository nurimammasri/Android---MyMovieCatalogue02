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

import com.dicoding.mymoviecatalog.Adapter.CardViewMoviesAdapter;
import com.dicoding.mymoviecatalog.Data.MoviesData;
import com.dicoding.mymoviecatalog.R;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private ArrayList<MoviesData> list = new ArrayList<>();
    private RecyclerView rvMovies;


    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        list.addAll(getListMovie());
        showRecyclerCardView();


    }

    private ArrayList<MoviesData> getListMovie() {
        //ARRAY UNTUK SIMPAN DATA STRING ARRAY
        String[] dataTitle, dataYear, dataRate, dataOverview,
                dataTopBilledCast, dataRelease, dataOriginalLanguage,
                dataRunTime, dataBudget, dataRevenue, dataGenre, dataCodeYoutube;

        TypedArray dataPoster;

        dataPoster = getResources().obtainTypedArray(R.array.movie_poster);


        dataTitle = getResources().getStringArray(R.array.movie_title);
        dataOverview = getResources().getStringArray(R.array.movie_overview);
        dataGenre = getResources().getStringArray(R.array.movie_genre);
        dataRelease = getResources().getStringArray(R.array.movie_status);
        dataRate = getResources().getStringArray(R.array.movie_score);
        dataOriginalLanguage = getResources().getStringArray(R.array.movie_originalLg);
        dataRunTime = getResources().getStringArray(R.array.movie_runtime);
        dataYear = getResources().getStringArray(R.array.movie_year);
        dataTopBilledCast = getResources().getStringArray(R.array.movie_topBilledCast);
        dataBudget = getResources().getStringArray(R.array.movie_budget);
        dataRevenue = getResources().getStringArray(R.array.movie_revenue);
        dataCodeYoutube = getResources().getStringArray(R.array.movie_youtube);

        ArrayList<MoviesData> listMovies = new ArrayList<>();


        for (int i = 0; i < dataTitle.length; i++) {
            MoviesData movie = new MoviesData();
            movie.setPoster(dataPoster.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setOverview(dataOverview[i]);
            movie.setGenre(dataGenre[i]);
            movie.setStatus(dataRelease[i]);
            movie.setScore(dataRate[i]);
            movie.setOriginalLanguage(dataOriginalLanguage[i]);
            movie.setRuntime(dataRunTime[i]);
            movie.setYear(dataYear[i]);
            movie.setTopBilledCast(dataTopBilledCast[i]);
            movie.setBudget(dataBudget[i]);
            movie.setRevenue(dataRevenue[i]);
            movie.setYoutube(dataCodeYoutube[i]);

            listMovies.add(movie);
        }

        dataPoster.recycle(); //digunankan untuk dealokasi untuk TypedArray
        return listMovies;
    }


    private void showRecyclerCardView() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));
        CardViewMoviesAdapter cardViewMoviesAdapter = new CardViewMoviesAdapter(list);
        rvMovies.setAdapter(cardViewMoviesAdapter);

        cardViewMoviesAdapter.setOnItemClickCallback(new CardViewMoviesAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(MoviesData data) {
                showSelectedMovies(data);
            }
        });
    }

    private void showSelectedMovies(MoviesData data) {
        Toast.makeText(this.getContext(), "Kamu memilih " + data.getTitle(), Toast.LENGTH_SHORT).show();
    }


}
