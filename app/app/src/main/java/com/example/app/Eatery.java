package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;

public class Eatery implements Parcelable, Comparable<Eatery> {
    private String name;
    private String url;
    private String description;
    private String location;
    private String serving;
    private String type;
    private float rating;
    private int ratingNr;

    public Eatery() {
    }

    public Eatery(String name, String url, String description, String location, String serving, String type, float rating, int ratingNr) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.location = location;
        this.serving = serving;
        this.type = type;
        this.rating = rating;
        this.ratingNr = ratingNr;
    }

    protected Eatery(Parcel in) {
        name = in.readString();
        url = in.readString();
        description = in.readString();
        location = in.readString();
        serving = in.readString();
        type = in.readString();
        rating = in.readFloat();
        ratingNr = in.readInt();
    }

    public static final Creator<Eatery> CREATOR = new Creator<Eatery>() {
        @Override
        public Eatery createFromParcel(Parcel in) {
            return new Eatery(in);
        }

        @Override
        public Eatery[] newArray(int size) {
            return new Eatery[size];
        }
    };

    public int getRatingNr() {
        return ratingNr;
    }

    public void setRatingNr(int ratingNr) {
        this.ratingNr = ratingNr;
    }

    public int compareTo(Eatery e) {
        return this.getName().compareTo(e.getName());
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating += rating;
        ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(serving);
        dest.writeString(type);
        dest.writeFloat(rating);
        dest.writeInt(ratingNr);
    }
}
