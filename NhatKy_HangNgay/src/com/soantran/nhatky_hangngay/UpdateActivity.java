package com.soantran.nhatky_hangngay;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends Activity {

	final String DATABASE_NAME = "mylist.db.sqlite";
    int id = -1;
	Button btnluu, btnhuy;
	EditText editngay, editnoidung;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        addControl();
        addEvents();
        initUI();
    }
	
	private void addControl() {
		btnluu = (Button) findViewById(R.id.btnluu);
		btnhuy = (Button) findViewById(R.id.btnhuy);
		editngay = (EditText) findViewById(R.id.editngay);
		editnoidung = (EditText) findViewById(R.id.editnoidung);
	}
	private void addEvents(){
		btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                update();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancel();
            }
        });
		
	}

	private  void initUI() {
		Intent intent = getIntent();
		int id = intent.getIntExtra("ID", -1);
		SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
		Cursor cursor = database.rawQuery("SELECT * FROM mylist_data WHERE ID = ? ",new String[]{id + ""});
        cursor.moveToFirst();
        String ngay = cursor.getString(1);
        String noidung = cursor.getString(2);
        editngay.setText(ngay);
        editnoidung.setText(noidung);
		
	}
	
	
	private void update(){
		String ngay = editngay.getText().toString();
		String noidung = editnoidung.getText().toString();
	
		ContentValues contentValues = new ContentValues();
		contentValues.put("NGAY",ngay);
		contentValues.put("NOIDUNG", noidung);
		
		SQLiteDatabase database = Database.initDatabase(this, "mylist.db.mysqlite");
		database.update("mylist_data",contentValues,"id = ? ",new String[]{id + ""});
		Intent intent =  new Intent(this, MainActivity.class);
		startActivity(intent);
		
	}
	
	private void cancel(){
		Intent intent =  new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
