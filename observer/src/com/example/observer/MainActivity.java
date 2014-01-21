package com.example.observer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver resolver=getContentResolver();		
		Uri uri=Uri.parse("content://sms/");		
		resolver.registerContentObserver(uri, true, new MyObserver(new Handler()));
	}
	private class MyObserver extends ContentObserver{
		public MyObserver(Handler handler) {
			super(handler);
		}
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Toast.makeText(MainActivity.this, "数据的内容变化了", 1).show();
			Uri uri=Uri.parse("content://sms/");
			ContentResolver resolver=getContentResolver();
			Cursor cursor=resolver.query(uri, new String[]{"address","date","type","body"}, null, null, null);
			cursor.moveToFirst();
			String address=cursor.getString(0);
			String date=cursor.getString(1);
			String type=cursor.getString(2);
			String body=cursor.getString(3);
			System.out.println("address:"+address);
			System.out.println("date:"+date);
			System.out.println("type:"+type);
			System.out.println("body:"+body);
			cursor.close();
		}
		
	}
}
