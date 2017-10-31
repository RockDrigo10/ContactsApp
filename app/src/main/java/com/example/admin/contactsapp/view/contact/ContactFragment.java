package com.example.admin.contactsapp.view.contact;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.contactsapp.R;
import com.example.admin.contactsapp.model.Contact;
import com.example.admin.contactsapp.model.Result;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactFragment extends Fragment implements ContactViewContract.View {
    public static final int CAMERA_REQUEST = 1888;
    private ContactViewPresenter contactPresenter;
    private Contact contact;
    ArrayList<Contact> contacts;
    @BindView(R.id.ivContactImage)
    ImageView ivContactImage;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etCompany)
    EditText etCompany;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnSave)
    Button btnSave;
    Unbinder unbinder;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etCompany = view.findViewById(R.id.etCompany);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        ivContactImage = view.findViewById(R.id.ivContactImage);
        ivContactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        contactPresenter = new ContactViewPresenter();
        contactPresenter.attachView(this);
        contactPresenter.setContext(ContactFragment.this.getActivity());
        contacts = new ArrayList<>();
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    contact = new Contact(
                            etFirstName.getText().toString().trim(), etLastName.getText().toString().trim(),
                            etPhone.getText().toString().trim(), etCompany.getText().toString().trim(),
                            imageViewToByte());
                    contactPresenter.saveContacts(contact);
                    Intent intent = new Intent(ContactFragment.this.getActivity(), ContactActivity.class);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(ContactFragment.this.getActivity(),
                            "Incorrect Phone Number Format", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactFragment.this.getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private byte[] imageViewToByte() {
        Bitmap bitmap = ((BitmapDrawable) ivContactImage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] array = stream.toByteArray();
        return array;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap picture = (Bitmap) data.getExtras().get("data");
        ivContactImage.setImageBitmap(picture);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getRandomUserList(List<Result> randomUserList) {

    }
}

