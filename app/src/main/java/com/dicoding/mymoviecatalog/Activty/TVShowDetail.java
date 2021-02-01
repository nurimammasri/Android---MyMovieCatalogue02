package com.dicoding.mymoviecatalog.Activty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicoding.mymoviecatalog.Data.TVShowsData;
import com.dicoding.mymoviecatalog.MainActivity;
import com.dicoding.mymoviecatalog.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class TVShowDetail extends AppCompatActivity {

    public static final String EXTRA_TVSHOWS = "extra-tvshows";

    int image;

    WebView youtubeVideo;
    CollapsingToolbarLayout collapsingToolbar;
    TextView txtYear, txtRate, txtOverview,
            txtTopBilledCast, txtRelease, txtOriginalLanguage,
            txtRunTime, txtGenre;

    ImageView imgPoster, Back;

    TVShowsData tvshows = new TVShowsData();


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }


        //DATA YANG AKAN DISET KE LAYOUT
        //title
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        //video youtube
        youtubeVideo = findViewById(R.id.youtube);

        imgPoster = findViewById(R.id.image_poster);
        txtGenre = findViewById(R.id.genre);
        txtRunTime = findViewById(R.id.run_time);
        txtYear = findViewById(R.id.year);
        txtRelease = findViewById(R.id.release);
        txtRate = findViewById(R.id.rating);
        txtTopBilledCast = findViewById(R.id.topBilledCast);
        txtOriginalLanguage = findViewById(R.id.originalLanguage);
        txtOverview = findViewById(R.id.overview);


        tvshows = getIntent().getParcelableExtra(EXTRA_TVSHOWS);

        //title
        String title = null;
        if (tvshows != null) {
            title = tvshows.getTitle();
        }
        // pengaturan dan inisialisasi collapsing toolbar
        Typeface bikoBold = Typeface.createFromAsset(getAssets(), "font/biko_bold.otf");
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setCollapsedTitleTypeface(bikoBold);
        collapsingToolbar.setExpandedTitleTypeface(bikoBold);

        //youtube
        //pengaturan video youtube
        // setting
        youtubeVideo.setWebViewClient(new WebViewClient());
        youtubeVideo.setWebChromeClient(new WebChromeClient());
        youtubeVideo.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        youtubeVideo.getSettings().setJavaScriptEnabled(true);
        youtubeVideo.getSettings().setPluginState(WebSettings.PluginState.ON);
        youtubeVideo.getSettings().setDefaultFontSize(10);

        String youtube = tvshows.getYoutube();
        muatVideo(youtube);

        image = tvshows.getPoster();
        imgPoster.setImageResource(image);


        String genre = tvshows.getGenre();
        txtGenre.setText(genre);

        String runtime = tvshows.getRuntime();
        txtRunTime.setText(runtime);

        String year = tvshows.getYear();
        txtYear.setText(year);

        String release = tvshows.getStatus();
        txtRelease.setText(release);

        String rate = tvshows.getScore();
        txtRate.setText(rate);

        String topBilledCast = tvshows.getTopBilledCast();
        txtTopBilledCast.setText(topBilledCast);

        String originalLanguage = tvshows.getOriginalLanguage();
        txtOriginalLanguage.setText(originalLanguage);


        String overview = tvshows.getOverview();
        txtOverview.setText(overview);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void muatVideo(String code_youtube) {
        String kodeHTML = "<head></head><body style=\"margin: 0; padding: 0\">" +
                "<iframe width=\"360\" height=\"240\" src=\"https://www.youtube.com/embed/" + code_youtube + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"
                + "</body>";
        youtubeVideo.loadData(kodeHTML, "text/html; charset=utf-8", null);
    }
}
