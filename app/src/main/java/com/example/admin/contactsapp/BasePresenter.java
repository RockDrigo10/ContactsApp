package com.example.admin.contactsapp;


public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();

}
