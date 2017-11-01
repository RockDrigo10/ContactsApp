package com.example.admin.contactsapp;

import android.app.Application;
import android.content.Context;

import com.example.admin.contactsapp.inject.app.AppComponent;
import com.example.admin.contactsapp.inject.app.AppModule;
import com.example.admin.contactsapp.inject.app.DaggerAppComponent;


public class App extends Application {

    private AppComponent appComponent;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setUpDaggerApp();

    }

    private void setUpDaggerApp() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}