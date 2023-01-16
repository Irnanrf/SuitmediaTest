package com.hacktiv8.suitmediatest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtPalindrome;
    Button btnCheck, btnNext;

    public static boolean isPalindrome(String str)
    {
        String text = "";

        boolean ans = false;
        for (int i = str.length() - 1; i >= 0; i--) {
            text = text + str.charAt(i);
        }

        if (str.equals(text)) {
            ans = true;
        }
        return ans;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtPalindrome = findViewById(R.id.txtPalindrome);

        btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = txtPalindrome.getText().toString();
                inputText = inputText.toLowerCase();
                if (inputText.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Wrong Input").setMessage("Palindrome text must be filled!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                } else {
                    boolean checkPalindrome = isPalindrome(inputText);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Check Palindrome").setMessage(String.valueOf(checkPalindrome))
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create();
                    builder.show();
                }


            }
        });

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("userName", txtName.getText().toString());
                startActivity(i);
            }
        });
    }
}