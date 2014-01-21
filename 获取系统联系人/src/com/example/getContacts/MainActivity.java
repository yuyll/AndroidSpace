package com.example.getContacts;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View view){
		ContentResolver resolver=getContentResolver();
		Uri uri=Uri.parse("content://com.android.contacts/raw_contacts");
		Uri datauri=Uri.parse("content://com.android.contacts/data");
		Cursor cursor=resolver.query(uri, null, null, null, null);

		while(cursor.moveToNext()){
			String id=cursor.getString(cursor.getColumnIndex("contact_id"));
			System.out.println(id);
			Cursor datacursor=resolver.query(datauri, null, "raw_contact_id=?", new String[]{id}, null);
			while(datacursor.moveToNext()){
				String data=datacursor.getString(datacursor.getColumnIndex("data1"));
				String type=datacursor.getString(datacursor.getColumnIndex("mimetype"));
				System.out.println("data="+data);
				System.out.println("type="+type);
			}
			datacursor.close();
			System.out.println("----------------------");
		}
		cursor.close();
		
		
	}
	


}
