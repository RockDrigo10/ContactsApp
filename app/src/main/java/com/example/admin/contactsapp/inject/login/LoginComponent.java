package com.example.admin.contactsapp.inject.login;

import com.example.admin.contactsapp.view.login.LoginActivity;

import dagger.Component;

@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginView);

}
