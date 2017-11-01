package com.example.admin.contactsapp.view.contact;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.admin.contactsapp.App;
import com.example.admin.contactsapp.R;
import com.example.admin.contactsapp.inject.DaggerMainActivityComponent;
import com.example.admin.contactsapp.inject.MainActivityModule;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DaggerMainActivityComponent
                .builder()
                .mainActivityModule(new MainActivityModule())
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .build()
                .inject(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactFragment createContactFragment = new ContactFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(
                        R.id.fragment,
                        createContactFragment,
                        createContactFragment.getTag()
                ).addToBackStack(null).commit();
            }
        });

    }
    private boolean isNetworkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(this.CONNECTIVITY_SERVICE); // 1
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // 2
        return networkInfo != null && networkInfo.isConnected(); // 3
    }
}
