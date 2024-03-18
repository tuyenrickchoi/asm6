package com.example.contactapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.contactapp.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;

    private AppDatabase appDatabase;
    private ContactDao contactDao;
    private SearchView searchView;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String strResult = intent.getStringExtra("data_result");
                       // contactList.add(strResult);
                        contactAdapter.notifyDataSetChanged();
                    }

                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);



//        searchView = findViewById(R.id.view_search);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterList(newText);
//                return true;
//            }
//        });

        binding.rvContact.setLayoutManager(new LinearLayoutManager(this));
        contactList = new ArrayList<Contact>();
        contactAdapter = new ContactAdapter(contactList);
        binding.rvContact.setAdapter(contactAdapter);

        binding.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , MainActivity2.class);
                startActivity(intent);
            }
        });


        contactList.add(new Contact("Nguyen Van A", "0905224245", "a@gmail.com"));
        contactList.add(new Contact("Nguyen Van B", "0905222545", "b@gmail.com"));
        contactList.add(new Contact("Nguyen Van C", "0905621545", "c@gmail.com"));


        contactAdapter.notifyDataSetChanged();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase = AppDatabase.getInstance(getApplicationContext());
                contactDao = appDatabase.contactDao();

                contactDao.insert(new Contact("Nguyen Van A", "0905224245", "a@gmail.com"));

            }
        });



    }

    private void filterList(String newtext) {
        ArrayList<Contact> filteredList = new ArrayList<>();
        for (Contact item : contactList) {
            if (item.getName().toLowerCase().contains(newtext.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            contactAdapter.setFilteredList(filteredList);
        }
    }
}