package com.example.admin.contactsapp.inject.contacts;

import com.example.admin.contactsapp.view.contact.ContactViewPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {
    @Provides
    ContactViewPresenter providesMainActivityPresenter(){
        return new ContactViewPresenter();
    }

}
