package com.example.app;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

public class Review implements Parcelable {
    String review;
    String reviewerID;
    String eateryName;
    float rating;
    int likes;
    int dislikes;
    String path;

    public Review( String review, String reviewerID, String eateryName, float rating, int likes, int dislikes, String path) {
        this.review = review;
        this.reviewerID = reviewerID;
        this.eateryName = eateryName;
        this.rating = rating;
        this.likes = likes;
        this.dislikes = dislikes;
        this.path=path;

    }

    public String getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(String reviewerID) {
        this.reviewerID = reviewerID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Review()
    {}

    protected Review(Parcel in) {
        review = in.readString();
        reviewerID = in.readString();
        eateryName = in.readString();
        rating = in.readFloat();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    public String getEateryName() {
        return eateryName;
    }

    public void setEateryName(String eateryName) {
        this.eateryName = eateryName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(review);
        dest.writeString(reviewerID);
        dest.writeString(eateryName);
        dest.writeFloat(rating);
    }
}
