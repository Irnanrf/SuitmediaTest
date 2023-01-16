package com.hacktiv8.suitmediatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView lblName,txtSelectedUser;
    Button btnChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Second Screen");
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lblName = findViewById(R.id.lblName);
        txtSelectedUser = findViewById(R.id.txtSelectedUser);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("userName");
            if(value == null){

            } else {
                lblName.setText(value);
            }

            String dataUser = extras.getString("dataNamaUser");
            if(dataUser == null){

            } else {
                txtSelectedUser.setText(dataUser);
            }
        }

        btnChoose = findViewById(R.id.btnChoose);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("userName", lblName.getText().toString());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}