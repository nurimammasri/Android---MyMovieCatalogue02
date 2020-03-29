package com.dicoding.mymoviecatalog.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.mymoviecatalog.Activty.MovieDetail;
import com.dicoding.mymoviecatalog.Data.MoviesData;
import com.dicoding.mymoviecatalog.R;

import java.util.ArrayList;

public class CardViewMoviesAdapter extends RecyclerView.Adapter<CardViewMoviesAdapter.CardViewHolder> {

    /*UNTUK ONITEMCLICKCALLBACK*/
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(MoviesData data);
    }


    /*SETTING ADAPTER*/
    private ArrayList<MoviesData> listMovies;

    public CardViewMoviesAdapter(ArrayList<MoviesData> movies) {
        this.listMovies = movies;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        final MoviesData movie = listMovies.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPoster);

        holder.txtRate.setText(movie.getScore());
        holder.txtTitle.setText(movie.getTitle());
        holder.txtRunTime.setText(movie.getRuntime());
        holder.txtGenre.setText(movie.getGenre());
        holder.txtOverview.setText(movie.getOverview());
        holder.txtYear.setText(movie.getYear());
        holder.txtRelease.setText(movie.getStatus());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovies.get(holder.getAdapterPosition()));

                /*MoviesData movie = listMovies.get(position);  -->  DIGUNAKAN PADA BAWAH INI*/
                Intent intent = new Intent(v.getContext().getApplicationContext(), MovieDetail.class);
                intent.putExtra(MovieDetail.EXTRA_MOVIE, movie);
                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtYear, txtRate, txtOverview,
                txtRelease, txtRunTime, txtGenre;

        private ImageView imgPoster;

        CardViewHolder(@NonNull View view) {
            super(view);

            imgPoster = view.findViewById(R.id.img_poster);
            txtRate = view.findViewById(R.id.rating);
            txtTitle = view.findViewById(R.id.title);
            txtRunTime = view.findViewById(R.id.run_time);
            txtGenre = view.findViewById(R.id.genre);
            txtOverview = view.findViewById(R.id.overview);
            txtYear = view.findViewById(R.id.year);
            txtRelease = view.findViewById(R.id.release);


        }
    }


}
