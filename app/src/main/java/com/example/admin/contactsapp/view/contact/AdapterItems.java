package com.example.admin.contactsapp.view.contact;

public class AdapterItems {

    public String FullName;
    public String PhoneNumber;
    public byte[] Photo;
    public String Pic;

    public AdapterItems(String fullName, String phoneNumber, byte[] photo, String pic) {
        this.FullName = fullName;
        this.PhoneNumber = phoneNumber;
        this.Photo = photo;
        this.Pic = pic;
    }
}
