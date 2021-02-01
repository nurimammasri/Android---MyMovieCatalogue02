package com.dicoding.mymoviecatalog.Activty;

import android.graphics.Typeface;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dicoding.mymoviecatalog.Data.MoviesData;
import com.dicoding.mymoviecatalog.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MovieDetail extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra-movie";

    WebView youtubeVideo;
    CollapsingToolbarLayout collapsingToolbar;
    TextView txtYear, txtRate, txtOverview,
            txtTopBilledCast, txtRelease, txtOriginalLanguage,
            txtRunTime, txtBudget, txtRevenue, txtGenre;

    ImageView imgPoster;

    MoviesData movie = new MoviesData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

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
        txtBudget = findViewById(R.id.budget);
        txtRevenue = findViewById(R.id.revenue);
        txtOverview = findViewById(R.id.overview);


        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        //title
        String title = null;
        if (movie != null) {
            title = movie.getTitle();
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

        String youtube = movie.getYoutube();
        muatVideo(youtube);

        int image = movie.getPoster();
        imgPoster.setImageResource(image);


        String genre = movie.getGenre();
        txtGenre.setText(genre);

        String runtime = movie.getRuntime();
        txtRunTime.setText(runtime);

        String year = movie.getYear();
        txtYear.setText(year);

        String release = movie.getStatus();
        txtRelease.setText(release);

        String rate = movie.getScore();
        txtRate.setText(rate);

        String topBilledCast = movie.getTopBilledCast();
        txtTopBilledCast.setText(topBilledCast);

        String originalLanguage = movie.getOriginalLanguage();
        txtOriginalLanguage.setText(originalLanguage);

        String budget = movie.getBudget();
        txtBudget.setText(budget);

        String revenue = movie.getRevenue();
        txtRevenue.setText(revenue);

        String overview = movie.getOverview();
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
