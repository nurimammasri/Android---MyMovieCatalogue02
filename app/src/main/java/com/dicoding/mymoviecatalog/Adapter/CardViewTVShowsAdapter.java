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
import com.dicoding.mymoviecatalog.Activty.TVShowDetail;
import com.dicoding.mymoviecatalog.Data.TVShowsData;
import com.dicoding.mymoviecatalog.R;

import java.util.ArrayList;

public class CardViewTVShowsAdapter extends RecyclerView.Adapter<CardViewTVShowsAdapter.CardViewHolder> {
    /*UNTUK ONITEMCLICKCALLBACK*/
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(TVShowsData data);
    }


    /*SETTING ADAPTER*/
    private ArrayList<TVShowsData> tvshows;

    public CardViewTVShowsAdapter(ArrayList<TVShowsData> tvshows) {
        this.tvshows = tvshows;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        final TVShowsData tv = tvshows.get(position);

        Glide.with(holder.itemView.getContext())
                .load(tv.getPoster())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPoster);

        holder.txtRate.setText(tv.getScore());
        holder.txtTitle.setText(tv.getTitle());
        holder.txtRunTime.setText(tv.getRuntime());
        holder.txtGenre.setText(tv.getGenre());
        holder.txtOverview.setText(tv.getOverview());
        holder.txtYear.setText(tv.getYear());
        holder.txtRelease.setText(tv.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(tvshows.get(holder.getAdapterPosition()));

                /*MoviesData movie = listMovies.get(position);  -->  DIGUNAKAN PADA BAWAH INI*/
                Intent intent = new Intent(v.getContext().getApplicationContext(), TVShowDetail.class);
                intent.putExtra(TVShowDetail.EXTRA_TVSHOWS, tv);
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return tvshows.size();
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
