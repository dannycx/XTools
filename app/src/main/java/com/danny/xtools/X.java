package com.x.xbase.module;

import android.content.Context;

import com.google.gson.Gson;

public class X {

    private static Context context;
    private static XModuleLoader loader;
    private  static Gson gson = new Gson();

    public static synchronized void init(Context context) {
        context = context.getApplicationContext();
        loader = new XModuleLoader(context);
    }

    public static Context getContext() {
        return context;
    }

    public static Gson gson() {
        return gson;
    }

    public static <T extends XModule> T module(Class<T> cls) {
        return loader.getModule(cls);
    }

}
