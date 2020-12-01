package com.example.app;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

public class Review implements Parcelable {
    Date reviewTime;
    String review;
    String reviewerMail;
    String eateryName;
    float rating;

    public Review(Date reviewTime, String review, String reviewerMail, String eateryName, float rating) {
        this.reviewTime = reviewTime;
        this.review = review;
        this.reviewerMail = reviewerMail;
        this.eateryName = eateryName;
        this.rating = rating;
    }
    public Review()
    {}

    protected Review(Parcel in) {
        review = in.readString();
        reviewerMail = in.readString();
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

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewerMail() {
        return reviewerMail;
    }

    public void setReviewerMail(String reviewerMail) {
        this.reviewerMail = reviewerMail;
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
        dest.writeString(reviewerMail);
        dest.writeString(eateryName);
        dest.writeFloat(rating);
    }
}
