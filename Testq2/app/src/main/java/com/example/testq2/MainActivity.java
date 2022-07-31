package com.example.testq2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo info){
        super.onCreateContextMenu(menu,v,info);
        menu.setHeaderTitle("Choose an option");
        getMenuInflater().inflate(R.menu.example_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.op1:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("One Selected");
                builder.setCancelable(true);
                AlertDialog d = builder.create();
                d.show();
                break;
            case R.id.op2:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Two Selected");
                builder1.setCancelable(true);
                AlertDialog d1 = builder1.create();
                d1.show();
                break;
            case R.id.op3:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setMessage("Three Selected");
                builder2.setCancelable(true);
                AlertDialog d2 = builder2.create();
                d2.show();
                break;

        }
        return super.onContextItemSelected(item);
    }
}