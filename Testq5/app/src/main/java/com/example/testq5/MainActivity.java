package com.example.testq5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button email = findViewById(R.id.email);
        Button phone = findViewById(R.id.phone);
        Button sms = findViewById(R.id.sms);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCall();
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });
    }

    public void sendMail(){
        Intent n = new Intent(Intent.ACTION_SEND);
        n.setData(Uri.parse("mailto:"));
        n.setType("text/plain");
        n.putExtra(n.EXTRA_EMAIL,new String[]{"123@gmail.com"});
        n.putExtra(n.EXTRA_SUBJECT,"some subject");
        n.putExtra(n.EXTRA_TEXT,"Body");
        startActivity(n);
    }

    public void sendSms(){
        String number = "123456789";
        startActivity(new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",number,null)));
    }

    public void sendCall(){
        Intent n = new Intent(Intent.ACTION_DIAL);
        startActivity(n);
    }

}