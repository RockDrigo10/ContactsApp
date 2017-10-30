package com.example.admin.contactsapp.view.login;

import android.content.Context;

public class LoginActivityPresenter implements LoginActivityContract.Presenter{

    private static final String TAG = "LoginPresenter";
    private LoginActivityContract.View view;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(LoginActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

}
