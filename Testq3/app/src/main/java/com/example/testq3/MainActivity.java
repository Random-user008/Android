package com.example.testq3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,pass,old,newN,delN;
    dbAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        old  = findViewById(R.id.old);
        newN = findViewById(R.id.newN);
        delN = findViewById(R.id.delete);
        dba  = new dbAdapter(this);
    }

    public void addUser(View v){
        String n,p;
        n = name.getText().toString();
        p = pass.getText().toString();
        long n1 = dba.insert(n,p);
    }

    public void getData(View v){
        String st = dba.getData();
        Toast.makeText(this, st, Toast.LENGTH_LONG).show();
    }

    public void updateUser(View v){
        String n,p;
        n = old.getText().toString();
        p = newN.getText().toString();
        int n1 = dba.update(n,p);
    }
    public void deleteUser(View v){
        String n,p;
        n = delN.getText().toString();
//        p = pass.getText().toString();
        long n1 = dba.delete(n);
    }

}