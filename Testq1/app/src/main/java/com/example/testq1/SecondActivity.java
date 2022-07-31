package com.example.testq1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent n = getIntent();
        String name = n.getStringExtra("Name");
        String em = n.getStringExtra("Email");
        String ph = n.getStringExtra("Phone");
        TextView t = findViewById(R.id.res);
        t.setText("Name: "+name+"\nEmail: "+em+"\nPhone: "+ph);
    }
}