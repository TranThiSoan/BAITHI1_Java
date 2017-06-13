package com.soantran.nhatky_hangngay;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AdapterNhatKy extends BaseAdapter {

	Activity context;
	ArrayList<NhatKy>list;
	
	public AdapterNhatKy(Activity context, ArrayList<NhatKy> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.listview_row, null);
		TextView txtid=(TextView) row.findViewById(R.id.txtid);
		TextView txtngay=(TextView) row.findViewById(R.id.txtngay);		
		TextView txtnoidung=(TextView) row.findViewById(R.id.txtnoidung);
		Button btnsua = (Button) row.findViewById(R.id.btnsua);
		Button btnxoa = (Button) row.findViewById(R.id.btnxoa);
		
		final NhatKy nk = list.get(position);
		txtid.setText(nk.id + "");
		txtngay.setText(nk.ngay );
		txtnoidung.setText(nk.noidung );
		
		btnsua.setOnClickListener(new View.OnClickListener(){
			

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, UpdateActivity.class);
				intent.putExtra("ID",nk.id);
				context.startActivity(intent);				
			}			
		});
		btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(nk.id);
                    }				
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
		
		
		return row;
	}

	private void delete(int idNhatKy) {
		SQLiteDatabase database = Database.initDatabase(context,"mylist.db.sqlite");
        database.delete("mylist_data", "ID = ?", new String[]{idNhatKy + ""});
        list.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM mylist_data", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ngay = cursor.getString(1);
            String noidung = cursor.getString(2);

            list.add(new NhatKy(id, ngay, noidung));
        }
        notifyDataSetChanged();
		
	}
}
