package com.irfan.moviecatalogue3.Activity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.irfan.moviecatalogue3.Model.Result;
import com.irfan.moviecatalogue3.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private TextView txtProgress;
    private ProgressBar progressBar, progressBarDetail;
    private int pStatus = 0;
    private Handler handler = new Handler();
    private boolean isMovie;
    private TextView tvTitle, tvRelease, tvOverview;
    private ImageView imgPoster, imgCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        tvTitle = findViewById(R.id.tv_title);
        tvRelease = findViewById(R.id.tv_release);
        tvOverview = findViewById(R.id.tv_desc);
        imgCover = findViewById(R.id.img_Photo2);
        imgPoster = findViewById(R.id.img_photo);
        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBarDetail = (ProgressBar) findViewById(R.id.progress_bar_detail);
        progressBarDetail.setVisibility(View.VISIBLE);

        isMovie = getIntent().getBooleanExtra("isMovie", true);

        if (isMovie) {
            Result movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            String cover = "https://image.tmdb.org/t/p/w500" + movie.getPosterImage();
            String poster = "https://image.tmdb.org/t/p/original" + movie.getBackdropImage();
            String overview = movie.getOverview();
            String title = movie.getTitle();
            String release = movie.getRelease();
            double score = movie.getScore();
            int rate = (int)(score * 10);

            Glide.with(this)
                    .load(cover)
                    .apply(new RequestOptions().override(300, 300))
                    .into(imgCover);
            Glide.with(this)
                    .load(poster)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBarDetail.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBarDetail.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imgPoster);
            tvTitle.setText(title);
            tvOverview.setText(overview);
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try{
                Date date = parser.parse(release);
                SimpleDateFormat formatter= new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                String formatedDate=formatter.format(date);
                tvRelease.setText(formatedDate);
            }catch (ParseException e){
                e.printStackTrace();
            }
            progressBar.setProgress(rate);
            txtProgress.setText(rate + " %");
        } else {
            Result tv = getIntent().getParcelableExtra(EXTRA_MOVIE);
            String cover = "https://image.tmdb.org/t/p/w500" + tv.getPosterImage();
            String poster = "https://image.tmdb.org/t/p/original" + tv.getBackdropImage();
            String overview = tv.getOverview();
            String title = tv.getName();
            String release = tv.getFirstAir();
            double score = tv.getScore();
            int rate = (int)(score * 10);
            Glide.with(this)
                    .load(cover)
                    .apply(new RequestOptions().override(300, 300))
                    .into(imgCover);
            Glide.with(this)
                    .load(poster)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBarDetail.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBarDetail.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imgPoster);
            tvTitle.setText(title);
            tvOverview.setText(overview);
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try{
                Date date = parser.parse(release);
                SimpleDateFormat formatter= new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                String formatedDate=formatter.format(date);
                tvRelease.setText(formatedDate);
            }catch (ParseException e){
                e.printStackTrace();
            }
            progressBar.setProgress(rate);
            txtProgress.setText(rate + " %");
        }
    }
}
