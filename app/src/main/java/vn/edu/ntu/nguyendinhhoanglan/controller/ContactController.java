package vn.edu.ntu.nguyendinhhoanglan.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nguyendinhhoanglan.model.Contact;

public class ContactController extends Application implements IContactController {
    List<Contact> contacts = new ArrayList<>();

    public ContactController() {
        contacts.add(new Contact("01", "Jonathan Joestar", "4/4/1868","0111111111", "Britain"));
        contacts.add(new Contact("02", "Joseph Joestar", "27/9/1920","0222222222", "Britain"));
        contacts.add(new Contact("03", "Kujo Jotaro", "29/2/1970","0333333333", "Japan"));
        contacts.add(new Contact("04", "Higashikata Josuke", "29/2/1983","0444444444", "Japan"));
        contacts.add(new Contact("05", "Giorno Giovanna", "16/4/1985","0555555555", "Italy"));
        contacts.add(new Contact("06", "Jolyne Cujoh", "","0666666666", "America"));
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }
}
