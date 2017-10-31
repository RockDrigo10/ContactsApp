package com.example.admin.contactsapp.view.contact;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.admin.contactsapp.R;
import com.example.admin.contactsapp.data.DatabaseHelper;
import com.example.admin.contactsapp.model.Contact;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityFragment extends Fragment {

    ArrayList<Contact> contacts = new ArrayList<>();
    //ListView lvContactList;
    ArrayList<AdapterItems> listContacts = new ArrayList<>();
    MyCustomAdapter adapter;
    TextView tvEmpty;
    SwipeMenuListView lvContactList;
    Unbinder unbinder;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        lvContactList = view.findViewById(R.id.lvContactList);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivityFragment.this.getActivity());
        contacts = databaseHelper.getContact();
        for (int i = 0; i < contacts.size(); i++) {
            listContacts.add(new AdapterItems(
                    contacts.get(i).firstName + " " + contacts.get(i).lastName,
                    contacts.get(i).phoneNumber, contacts.get(i).photo));
        }
        if (contacts.size() == 0)
            tvEmpty.setVisibility(View.VISIBLE);

        adapter = new MyCustomAdapter(listContacts);
        lvContactList.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "edit" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        MainActivityFragment.this.getActivity());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(240,255,255)));
                // set item width
                editItem.setWidth(170);
                // set item title
                editItem.setIcon(R.drawable.ic_mode_edit_black_24dp);
                // add to menu
                menu.addMenuItem(editItem);
                // create "call" item
                SwipeMenuItem callItem = new SwipeMenuItem(
                        MainActivityFragment.this.getActivity());
                // set item background
                callItem.setBackground(new ColorDrawable(Color.rgb(240,255,255)));
                // set item width
                callItem.setWidth(170);
                // set a icon
                callItem.setIcon(R.drawable.ic_call_black_48dp);
                // add to menu
                menu.addMenuItem(callItem);
            }
        };

        // set creator
        lvContactList.setMenuCreator(creator);

        lvContactList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        EditContactFragment editContactFragment = new EditContactFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("CURRENT_CONTACT", contacts.get(position));
                        editContactFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.fragment, editContactFragment).addToBackStack(null).commit();
                        break;
                    case 1:
                        Toast.makeText(MainActivityFragment.this.getActivity(), "World", Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getActivity().getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_listview_contact, null);

            Contact contact;
            contact = contacts.get(position);
            final AdapterItems s = listnewsDataAdpater.get(position);
            TextView tvFullName = myView.findViewById(R.id.tvFullName);
            tvFullName.setText(s.FullName);
            TextView tvPhoneNumber = myView.findViewById(R.id.tvPhoneNumber);
            tvPhoneNumber.setText(s.PhoneNumber);
            CircleImageView ivShowContactImage = myView.findViewById(R.id.ivShowContactImage);
            byte[] contactPhoto = contact.getPhoto();
            Bitmap bitmap = BitmapFactory.decodeByteArray(contactPhoto, 0, contactPhoto.length);
            ivShowContactImage.setImageBitmap(bitmap);

            return myView;
        }

    }

}
