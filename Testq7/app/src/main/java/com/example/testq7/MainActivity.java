package com.example.testq7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button s;
    ListView lv;
    ArrayList<HashMap<String,String>> cl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.Lv);
        s = findViewById(R.id.button);
        cl = new ArrayList<>();
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.androidhive.info/contacts/";
                new UrlStrHandler().execute(url);
            }
        });

    }
    public class UrlStrHandler extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            String json_response = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                InputStream n = new BufferedInputStream(con.getInputStream());
                json_response = StreamtoString(n);
                if(json_response!=null){
                    JSONObject obj = new JSONObject(json_response);
                    JSONArray arr = obj.getJSONArray("contacts");
                    for(int j=0;j<arr.length();j++){
                        JSONObject c = arr.getJSONObject(j);
                        String cid = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        HashMap<String,String> hm = new HashMap<>();
                        hm.put("id",cid);
                        hm.put("name",name);
                        hm.put("email",email);
                        cl.add(hm);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
                return null;
        }

        @Override
        protected void onPostExecute(String s){
            ListAdapter adapter = new SimpleAdapter(MainActivity.this,cl,R.layout.list_item,new String[]{"id","name","email"},new int[]{R.id.cid,R.id.cemail,R.id.cname});
            lv.setAdapter(adapter);
        }

        public String StreamtoString(InputStream s){
            StringBuilder sb = new StringBuilder();
            BufferedReader r= new BufferedReader(new InputStreamReader(s));
            String line;
            try{
                while((line=r.readLine())!=null){
                    sb.append(line).append("\n");
                }

            }catch (Exception e){

            }
            return sb.toString();
        }
    }

}