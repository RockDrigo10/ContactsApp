package com.example.admin.contactsapp.inject.login;

import com.example.admin.contactsapp.view.login.LoginActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
//    @Singleton this is going to make the class as singleton
    LoginActivityPresenter providesLoginPresenter(){

        return new LoginActivityPresenter();
    }
}
