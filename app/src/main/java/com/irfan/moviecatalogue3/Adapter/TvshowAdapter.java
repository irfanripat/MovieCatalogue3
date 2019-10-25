package com.irfan.moviecatalogue3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.irfan.moviecatalogue3.Activity.DetailActivity;
import com.irfan.moviecatalogue3.Model.Result;
import com.irfan.moviecatalogue3.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.TvshowViewHolder> {

    private Context context;
    private ArrayList<Result> listMovie;

    public TvshowAdapter(ArrayList<Result> listMovie, Context context) {
        this.context = context;
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public TvshowAdapter.TvshowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new TvshowViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull TvshowAdapter.TvshowViewHolder tvshowViewHolder, final int i) {
        tvshowViewHolder.bind(listMovie.get(i), context);

        tvshowViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, DetailActivity.class);
                detailIntent.putExtra(DetailActivity.EXTRA_MOVIE, listMovie.get(i));
                detailIntent.putExtra("isMovie",false);
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class TvshowViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvRelease, tvOverview;
        ImageView imgPoster;



        public TvshowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_name);
            tvRelease = itemView.findViewById(R.id.tv_release);
            tvOverview = itemView.findViewById(R.id.tv_desc);
            imgPoster = itemView.findViewById(R.id.img_photo);
        }

        void bind(Result result, Context context) {

            tvTitle.setText(result.getName());
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try{
                Date date = parser.parse(result.getFirstAir());
                SimpleDateFormat formatter= new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
                String formatedDate=formatter.format(date);
                tvRelease.setText(formatedDate);
            }catch (ParseException e){
                e.printStackTrace();
            }
            if (result.getOverview().length() == 0) {
                result.setOverview("Maaf belum tersedia dalam bahasa indonesia");
                tvOverview.setText(result.getOverview());
            } else {
                tvOverview.setText(result.getOverview());
            }
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w154" + result.getPosterImage())
                    .into(imgPoster);
        }
    }
}
