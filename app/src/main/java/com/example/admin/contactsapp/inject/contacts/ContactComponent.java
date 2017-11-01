package com.example.admin.contactsapp.inject.contacts;

import com.example.admin.contactsapp.view.contact.MainActivityFragment;

import dagger.Component;

@Component(modules = ContactModule.class)
public interface ContactComponent {
    void inject(MainActivityFragment contactView);

}
