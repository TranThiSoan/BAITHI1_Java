package com.soantran.nhatky_hangngay;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {

	final String DATABASE_NAME = "mylist.db.sqlite";
	SQLiteDatabase database;
	
	ListView listView;
	ArrayList <NhatKy> list;
	AdapterNhatKy adapter;
	ImageView btnadd;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
    
        addControls();
        readData();
    }
    
    private void addControls(){
    	btnadd = (ImageView)findViewById(R.id.btnadd);
    	btnadd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,AddActivity.class);
				startActivity(intent);
		}
    		
    	});
    	listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<NhatKy>();
        adapter = new AdapterNhatKy(this, list);
        listView.setAdapter(adapter);
    }
    
    private void readData(){
    	 database = Database.initDatabase(this, DATABASE_NAME);
         Cursor cursor = database.rawQuery("SELECT * FROM mylist_data", null);
         list.clear();
         for(int i = 0; i < cursor.getCount(); i++){
             cursor.moveToPosition(i);
             int id = cursor.getInt(0);
             String ngay = cursor.getString(1);
             String noidung = cursor.getString(2);
             list.add(new NhatKy(id, ngay, noidung));
         }
         adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
