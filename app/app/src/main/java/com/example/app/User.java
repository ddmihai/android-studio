package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable {
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String login;
    public User() {
//        name=null;
//        lname=null;
//        mail=null;
//        password=null;
//        login=null;
    }
    public User(String fName, String lName, String email, String password, String login){
        this.fName=fName;
        this.lName=lName;
        this.email=email;
        this.password=password;
        this.login=login;
    }

    protected User(Parcel in) {
        fName = in.readString();
        lName = in.readString();
        email = in.readString();
        password = in.readString();
        login = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fName);
        dest.writeString(lName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(login);
    }
}
