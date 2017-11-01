package com.example.admin.contactsapp.inject;

import com.example.admin.contactsapp.inject.app.AppComponent;
import com.example.admin.contactsapp.view.contact.ContactActivity;
import com.example.admin.contactsapp.view.contact.ContactFragment;
import com.example.admin.contactsapp.view.contact.MainActivityFragment;

import dagger.Component;

@UserScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(ContactActivity mainActivity);
    void inject(MainActivityFragment mainActivityFragment);
    void inject(ContactFragment contactFragment);
}
