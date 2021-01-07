package com.danny.xtools;

import android.content.Context;

import com.google.gson.Gson;

public class X {

    private static Context context;
    private  static Gson gson = new Gson();

    public static synchronized void init(Context context) {
        context = context.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static Gson gson() {
        return gson;
    }

}
