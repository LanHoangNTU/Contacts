package vn.edu.ntu.nguyendinhhoanglan.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.edu.ntu.nguyendinhhoanglan.controller.ContactController;
import vn.edu.ntu.nguyendinhhoanglan.controller.IContactController;
import vn.edu.ntu.nguyendinhhoanglan.model.Contact;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvContacts;
    List<Contact> contacts;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
    }

    private void addViews() {
        rvContacts = findViewById(R.id.rvContacts);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        IContactController controller = (IContactController) getApplication();
        contacts = controller.getAllContacts();
        adapter = new ContactAdapter(contacts);
        rvContacts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnu_contact, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnuAdd:
                addContact();
            case R.id.mnuExit:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addContact() {
        Intent intent = new Intent(this, UpdateContact.class);
        startActivity(intent);
    }
//------------------------------------------------------
    //----                 ViewHolder                   ----
    //------------------------------------------------------

    private class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtBirth, txtPhone;
        ImageView imvEdit;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtBirth = this.itemView.findViewById(R.id.txtBirth);
            txtPhone = this.itemView.findViewById(R.id.txtNumbers);
            imvEdit = this.itemView.findViewById(R.id.imgEdit);
        }

        public void bind(Contact contact){
            txtName.setText(contact.getName());
            txtBirth.setText(contact.getDateOfBirth());
            txtPhone.setText(contact.getPhone());
        }
    }

    //------------------------------------------------------
    //----                  Adapter                     ----
    //------------------------------------------------------

    private class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{
        List<Contact> contacts;

        public ContactAdapter(List<Contact> contacts) {
            this.contacts = contacts;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view =inflater.inflate(R.layout.contact, parent, false);

            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
            holder.bind(contacts.get(position));
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }
    }
}
