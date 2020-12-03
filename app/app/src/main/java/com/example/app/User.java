package com.example.app;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String login;
    private int type;
    private String url;
    private String uid;

    public User() {
//        name=null;
//        lname=null;
//        mail=null;
//        password=null;
//        login=null;
    }


    public User(String fName, String lName, String email, String password, String login, String url, int type) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.login = login;
        this.url = url;
        this.type = type;
    }

    protected User(Parcel in) {
        fName = in.readString();
        lName = in.readString();
        email = in.readString();
        password = in.readString();
        login = in.readString();
        type = in.readInt();
        url = in.readString();
        uid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fName);
        dest.writeString(lName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(login);
        dest.writeInt(type);
        dest.writeString(url);
        dest.writeString(uid);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean equals(User u) {
        if (!this.fName.equals(u.fName))
            return false;
        if (!this.lName.equals(u.lName))
            return false;
        if (!this.login.equals(u.login))
            return false;
        if (!this.email.equals(u.email))
            return false;
        if (!this.password.equals(u.password))
            return false;
        if (this.type != (u.type))
            return false;
        if (!this.url.equals(u.url))
            return false;
        return true;
    }
}
