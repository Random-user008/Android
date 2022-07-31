package com.example.testq3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class dbAdapter {

    dbhelper help;

    public dbAdapter(Context context)
    {
        help = new dbhelper(context);
    }


    public long insert(String name,String pass){
        SQLiteDatabase dbb = help.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(dbhelper.name,name);
        v.put(dbhelper.password,pass);
        long d = dbb.insert(dbhelper.tname,null,v);
        return d;
    }

    public String getData(){
        SQLiteDatabase dbb = help.getWritableDatabase();
        StringBuffer bf = new StringBuffer();
        String [] columns = {dbhelper.UID,dbhelper.name,dbhelper.password};
        Cursor cursor = dbb.query(dbhelper.tname,columns,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") int uid = cursor.getInt(cursor.getColumnIndex(dbhelper.UID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(dbhelper.name));
            @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex(dbhelper.password));
            bf.append(uid+" "+name+" "+pass);
        }
        return bf.toString();
    }

    public long delete(String name){
        SQLiteDatabase db = help.getWritableDatabase();
        String[] where = {name};
        long n = db.delete(dbhelper.tname,dbhelper.name+"=?",where);
        return n;
    }

    public int update(String u,String n){
        SQLiteDatabase db = help.getWritableDatabase();
        String[] where = {u};
        ContentValues v = new ContentValues();
        v.put(dbhelper.name,n);
        int p = db.update(dbhelper.tname,v,dbhelper.name+"=?",where);
        return p;
    }

    static class dbhelper extends SQLiteOpenHelper{
        private static final String dbname = "DBName";
        private static final int dv = 1;
        private static final String UID = "_id";
        private static final String tname = "TName";
        private static final String name = "Name";
        private static final String password = "Password";
        private static final String createTable = "CREATE TABLE "+tname+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+name+" VARCHAR(255),"+password+" VARCHAR(255));";
        private static final String deleteTable = "DROP TABLE IF EXISTS "+tname;
        private Context context;

        public dbhelper(Context context) {
            super(context, dbname, null, dv);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(createTable);
            }catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            try{
                db.execSQL(deleteTable);
                onCreate(db);
            }catch (Exception e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
