package com.example.testq6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    //6a
//    String id = "c_1";
//    CharSequence num = "someNum";
//    Context context = MainActivity.this;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button btn = findViewById(R.id.button);
//        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NotificationChannel nc = new NotificationChannel(id,num,nm.IMPORTANCE_HIGH);
//                nm.createNotificationChannel(nc);
//
//                Intent n = new Intent(context,MainActivity.class);
//                TaskStackBuilder sb = TaskStackBuilder.create(context);
//                sb.addParentStack(MainActivity.class);
//                sb.addNextIntent(n);
//
//                PendingIntent p = sb.getPendingIntent(0,PendingIntent.FLAG_IMMUTABLE);
//
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(context,id);
//
//                Notification ncc =  builder.setContentTitle("New notification")
//                        .setContentText("Hello there")
//                        .setTicker("Text!")
//                        .setSmallIcon(R.drawable.ic_launcher_background)
//                        .setAutoCancel(true)
//                        .setContentIntent(p)
//                        .build();
//
//                nm.notify(1,ncc);
//            }
//        });


    //6b
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(n,1);
            }
        });
    }
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                try (Cursor c = managedQuery(contactData, null, null, null, null)) {
                    if (c.moveToFirst()) {
                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                        @SuppressLint("Range")
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                            phones.moveToFirst();
                            @SuppressLint("Range")
                            String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Toast.makeText(getApplicationContext(), cNumber, Toast.LENGTH_SHORT).show();
                            phones.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}