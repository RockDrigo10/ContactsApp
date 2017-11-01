package com.example.admin.contactsapp.view.contact;

import android.content.Context;

import com.example.admin.contactsapp.App;
import com.example.admin.contactsapp.data.DatabaseHelper;
import com.example.admin.contactsapp.model.Contact;
import com.example.admin.contactsapp.model.Randomuser;
import com.example.admin.contactsapp.model.Result;

import java.io.IOException;
import java.util.List;

public class ContactViewPresenter implements ContactViewContract.Presenter {

    private ContactViewContract.View view;
    private Context context;

    @Override
    public void attachView(ContactViewContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

//    @Override
//    public void setContext(Context context) {
//        this.context = context;
//    }

    @Override
    public void saveContacts(Contact contact) {
        DatabaseHelper db = new DatabaseHelper(App.getContext());
        db.saveNewContact(contact);

    }

    @Override
    public void restCall() {
        final retrofit2.Call<Randomuser> randomuserCall = NetworkCall.getRandomUser();
        randomuserCall.enqueue(new retrofit2.Callback<Randomuser>() {
            @Override
            public void onResponse(retrofit2.Call<Randomuser> call, retrofit2.Response<Randomuser> response) {
                List<Result> user = response.body().getResults();
                try {
                    view.getRandomUserList(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Randomuser> call, Throwable t) {
            }
        });

    }


}
