package com.example.writeContact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View view){
		ContentResolver resolver=getContentResolver();
		Uri uri=Uri.parse("content://com.android.contacts/raw_contacts");
		Uri datauri=Uri.parse("content://com.android.contacts/data");
		Cursor cursor=resolver.query(uri, new String[]{"_id"}, null, null, null);
		cursor.moveToLast();
		int id = cursor.getInt(0);
		int newId = id + 1;
		ContentValues values=new ContentValues();
		values.put("contact_id",newId);
		resolver.insert(uri, values);
		
		ContentValues phonevalues=new ContentValues();
		phonevalues.put("data1","8888");
		phonevalues.put("mimetype","vnd.android.cursor.item/phone_v2");
		phonevalues.put("raw_contact_id",newId);
		resolver.insert(datauri, phonevalues);
		
		
		ContentValues emailvalues=new ContentValues();
		emailvalues.put("data1","61759881@qq.com");
		emailvalues.put("mimetype","vnd.android.cursor.item/email_v2");
		emailvalues.put("raw_contact_id",newId);
		resolver.insert(datauri, emailvalues);
		
		ContentValues namevalues=new ContentValues();
		namevalues.put("data1","yudan");
		namevalues.put("mimetype","vnd.android.cursor.item/name");
		namevalues.put("raw_contact_id",newId);
		resolver.insert(datauri, namevalues);
		
	}
}
