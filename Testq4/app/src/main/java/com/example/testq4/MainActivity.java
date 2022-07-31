package com.example.testq4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button btn = findViewById(R.id.button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent n = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(n,100);
//            }
//        });
//    }
//    @Override
//    protected void onActivityResult(int req,int res,@Nullable Intent data ){
//        super.onActivityResult(req,res,data);
//        Bitmap bt = (Bitmap) (data.getExtras().get("data"));
//        ImageView img = findViewById(R.id.imageView);
//        img.setImageBitmap(bt);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(n,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int req,int res,Intent data){
        super.onActivityResult(req,res,data);
        if(null!=data){
            Uri img = data.getData();
            String[] f = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(img,f,null,null,null);
            cursor.moveToFirst();
            int cl = cursor.getColumnIndex(f[0]);
            String f1 = cursor.getString(cl);
            ImageView im = findViewById(R.id.imageView);
            im.setImageBitmap(BitmapFactory.decodeFile(f1));
        }
    }
}