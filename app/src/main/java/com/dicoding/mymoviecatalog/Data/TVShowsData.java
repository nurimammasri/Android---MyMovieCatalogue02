package com.dicoding.mymoviecatalog.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class TVShowsData implements Parcelable {

    private int poster;
    private String title;
    private String year;
    private String score;
    private String overview;
    private String topBilledCast;
    private String status;
    private String originalLanguage;
    private String runtime;
    private String genre;
    private String youtube;

    public TVShowsData() {

    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTopBilledCast() {
        return topBilledCast;
    }

    public void setTopBilledCast(String topBilledCast) {
        this.topBilledCast = topBilledCast;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(poster);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(score);
        dest.writeString(overview);
        dest.writeString(topBilledCast);
        dest.writeString(status);
        dest.writeString(originalLanguage);
        dest.writeString(runtime);
        dest.writeString(genre);
        dest.writeString(youtube);
    }

    private TVShowsData(Parcel in) {
        poster = in.readInt();
        title = in.readString();
        year = in.readString();
        score = in.readString();
        overview = in.readString();
        topBilledCast = in.readString();
        status = in.readString();
        originalLanguage = in.readString();
        runtime = in.readString();
        genre = in.readString();
        youtube = in.readString();
    }

    public static final Creator<TVShowsData> CREATOR = new Creator<TVShowsData>() {
        @Override
        public TVShowsData createFromParcel(Parcel in) {
            return new TVShowsData(in);
        }

        @Override
        public TVShowsData[] newArray(int size) {
            return new TVShowsData[size];
        }
    };

}
