package com.example.admin.contactsapp.view.login;

import android.content.Context;

import com.example.admin.contactsapp.BasePresenter;
import com.example.admin.contactsapp.BaseView;

public interface LoginActivityContract {

    interface View extends BaseView {
    }


    interface Presenter extends BasePresenter<View> {
        void setContext(Context context);

    }

}
