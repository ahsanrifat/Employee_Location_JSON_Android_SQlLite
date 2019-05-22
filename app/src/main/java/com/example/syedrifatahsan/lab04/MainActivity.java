package com.example.syedrifatahsan.lab04;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button bt;

    public static ListView lv;
    public static ArrayList<String> aListItem;
    public static ArrayAdapter<String> aListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.btnID);
        lv=findViewById(R.id.listViewID);
        aListItem=new ArrayList<>();
        aListAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,aListItem);
    }
    public void fetch(View v){

            fetchJSON process = new fetchJSON();
            process.execute();


    }
    public void addData(View v){
        if(aListItem.isEmpty()){
            Toast.makeText(this, "First Fetch to Add", Toast.LENGTH_SHORT).show();
        }else{

            SQlLite sQlLite=new SQlLite(this);
            SQLiteDatabase sqLiteDatabase=sQlLite.getWritableDatabase();

            for(int i=0;i<fetchJSON.empList.size();i++) {
                long check=sQlLite.insertData(fetchJSON.empList.get(i).name,fetchJSON.empList.get(i).loc1,fetchJSON.empList.get(i).loc2);
                if(check==-1){
                    Log.d("dbpera", "insert e jhamela");
                }
                Log.d("dbpera", "insert hoye gelo"+fetchJSON.empList.get(i).name);
            }

        }
    }
}
