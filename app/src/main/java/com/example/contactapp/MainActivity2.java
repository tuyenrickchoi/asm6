package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactapp.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    private TextView btnSave;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtPhone;
    private EditText edtEmail;
    private ImageView btnClose;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_contact);

        btnSave = findViewById(R.id.btn_save);
        edtFirstName = findViewById(R.id.edt_fname);
        edtLastName = findViewById(R.id.edt_lname);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);

        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipFN = edtFirstName.getText().toString();
                String ipP = edtPhone.getText().toString();
                String ipEm = edtEmail.getText().toString();

                onClickBackToMain(ipFN,ipP,ipEm);
            }
        });
    }

    private void onClickBackToMain(String ipFN, String ipP, String ipEm) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("data_result", ipFN + ipP + ipEm);
//        intent.putExtra("data_result", ipP);
//        intent.putExtra("data_result", ipEm);
        setResult(RESULT_OK, intent);
        finish();
    }
}