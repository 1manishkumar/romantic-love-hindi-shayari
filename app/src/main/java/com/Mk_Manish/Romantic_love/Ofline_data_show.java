package com.Mk_Manish.Romantic_love;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Ofline_data_show extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
