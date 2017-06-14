package com.soantran.nhatky_hangngay;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity  extends Activity{

	final String DATABASE_NAME = "mylist.db.sqlite";
	ImageView btnluu, btnhuy;
	EditText editngay, editnoidung;
	protected void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_add);
		
		addControl();
		addEvents();
	}
	private void addControl() {
		btnluu = (ImageView) findViewById(R.id.btnluu);
		btnhuy = (ImageView) findViewById(R.id.btnhuy);
		editngay = (EditText) findViewById(R.id.editngay);
		editnoidung = (EditText) findViewById(R.id.editnoidung);
	}
	private void addEvents(){
		btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });		
	}
	
	private void insert(){
		String ngay = editngay.getText().toString();
		String noidung = editnoidung.getText().toString();
	
		ContentValues contentValues = new ContentValues();
		contentValues.put("NGAY",ngay);
		contentValues.put("NOIDUNG", noidung);
		
		SQLiteDatabase database = Database.initDatabase(this, "mylist.db.sqlite");
		database.insert("mylist_data",null,contentValues);
		Intent intent =  new Intent(this, MainActivity.class);
		startActivity(intent);
		
	}
	
	private void cancel(){
		Intent intent =  new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
