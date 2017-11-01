package com.example.admin.contactsapp.inject;

import com.example.admin.contactsapp.view.contact.ContactViewPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    ContactViewPresenter providesMainActivityPresenter(){
        return new ContactViewPresenter();
    }
}
