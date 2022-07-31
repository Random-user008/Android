package com.example.testq1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText phone  = findViewById(R.id.phone);
        Button save = findViewById(R.id.save);
        Button toast = findViewById(R.id.msg);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MainActivity.this,SecondActivity.class);
                String na = name.getText().toString();
                String em = email.getText().toString();
                String ph = phone.getText().toString();
                n.putExtra("Name",na);
                n.putExtra("Email",em);
                n.putExtra("Phone",ph);
                startActivity(n);
            }
        });

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This is a Toast!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}