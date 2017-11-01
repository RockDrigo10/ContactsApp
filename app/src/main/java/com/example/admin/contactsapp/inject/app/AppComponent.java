package com.example.admin.contactsapp.inject.app;

import com.example.admin.contactsapp.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void insert(App app);
}
