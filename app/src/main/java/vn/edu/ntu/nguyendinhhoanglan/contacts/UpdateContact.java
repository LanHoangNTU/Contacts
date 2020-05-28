package vn.edu.ntu.nguyendinhhoanglan.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nguyendinhhoanglan.controller.IContactController;
import vn.edu.ntu.nguyendinhhoanglan.model.Contact;

public class UpdateContact extends AppCompatActivity{
    EditText edtID, edtName, edtBirth, edtPhone, edtAddress;
    Button btnConfirm, btnCancel;
    List<Contact> contacts = new ArrayList<>();
    List<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        addViews();
        addEvents();
    }

    private void addViews() {
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtUpName);
        edtBirth = findViewById(R.id.edtUpBirthday);
        edtPhone = findViewById(R.id.edtUpPhone);
        edtAddress = findViewById(R.id.edtUpAddress);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        IContactController controller = (IContactController) getApplication();
        contacts = controller.getAllContacts();

        for (Contact c: contacts) {
            ids.add(c.getId());
        }
    }

    private void addEvents() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(existedID())
                    Toast.makeText(UpdateContact.this, "Invalid Id", Toast.LENGTH_SHORT).show();
                else if(invalidDate() || invalidPhoneNumber())
                    Toast.makeText(UpdateContact.this, "Invalid date or phone number", Toast.LENGTH_SHORT).show();
                else {
                    confirm();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private boolean existedID(){
        String id = edtID.getText().toString();
        if(id.length() < 1)
            return true;
        for (String i:
             ids) {
            if(i.equals(id))
                return true;
        }
        return false;
    }

    private boolean invalidDate(){
        String date = edtBirth.getText().toString();
        if(date.length() < 7)
            return true;
        return false;
    }

    private boolean invalidPhoneNumber(){
        String phone = edtPhone.getText().toString();
        if(phone.length() != 10)
            return true;
        return false;
    }

    private void confirm(){
        Intent intent = new Intent(this, MainActivity.class);
        Contact contact = new Contact();
        contact.setId(edtID.getText().toString());
        contact.setName(edtName.getText().toString());
        contact.setDateOfBirth(edtBirth.getText().toString());
        contact.setPhone(edtPhone.getText().toString());
        contact.setAddress(edtAddress.getText().toString());
        contacts.add(contact);
        startActivity(intent);
        onBackPressed();
    }

    private void cancel(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        onBackPressed();
    }
}
