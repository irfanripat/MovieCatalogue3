package com.irfan.moviecatalogue3.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("vote_average")
    @Expose
    private double score;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropImage;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("release_date")
    @Expose
    private String release;

    @SerializedName("first_air_date")
    @Expose
    private String firstAir;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String posterImage;

    public int getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease() {
        return release;
    }

    public String getFirstAir() {
        return firstAir;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterImage() {
        return posterImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.score);
        dest.writeString(this.backdropImage);
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.release);
        dest.writeString(this.firstAir);
        dest.writeString(this.overview);
        dest.writeString(this.posterImage);
    }

    public Result() {
    }

    protected Result(Parcel in) {
        this.id = in.readInt();
        this.score = in.readDouble();
        this.backdropImage = in.readString();
        this.title = in.readString();
        this.name = in.readString();
        this.release = in.readString();
        this.firstAir = in.readString();
        this.overview = in.readString();
        this.posterImage = in.readString();
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}


