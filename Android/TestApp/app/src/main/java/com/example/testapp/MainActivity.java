package com.example.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    private int clickNumber;

    public int getClickNumber() {
        return this.clickNumber;
    }

    public void setClickNumber(int clickNumber) {
        this.clickNumber = clickNumber;
    }

    public void incrementClickNumber() {
        this.clickNumber++;
    }

    public void clickButtonApp(View view) {
        EditText editField = findViewById(R.id.editField);
        //incrementClickNumber();
        //Log.i("Test Number: ", String.valueOf(getClickNumber()));
        Log.i("Text: ", editField.getText().toString());
        Toast.makeText(MainActivity.this, "Unlocked furnace!", Toast.LENGTH_LONG).show();
    }

    public void clickLogIn(View view) {
        EditText fieldUsername = findViewById(R.id.editUsername);
        EditText fieldPassword = findViewById(R.id.editPassword);
        Log.i("Username", fieldUsername.getText().toString());
        Log.i("Password", fieldPassword.getText().toString());
    }

}
