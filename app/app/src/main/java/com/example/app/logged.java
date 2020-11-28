package com.example.app;

import android.app.Application;

public class logged extends Application {
    private User logged;

    public User getLogged() {
        return logged;
    }

    public void setLogged(User logged) {
        this.logged = logged;
    }
}
