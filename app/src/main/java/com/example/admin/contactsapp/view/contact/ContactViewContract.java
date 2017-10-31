package com.example.admin.contactsapp.view.contact;

import android.content.Context;

import com.example.admin.contactsapp.BasePresenter;
import com.example.admin.contactsapp.BaseView;
import com.example.admin.contactsapp.model.Contact;
import com.example.admin.contactsapp.model.Result;

import java.util.List;

public interface ContactViewContract {

    interface View extends BaseView {
        void getRandomUserList(List<Result> randomUserList);
    }


    interface Presenter extends BasePresenter<ContactViewContract.View> {
        void saveContacts(Contact contact);
        void setContext(Context context);
        void restCall();

    }
}
