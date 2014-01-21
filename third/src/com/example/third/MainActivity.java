package com.example.third;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.database.ContentObserver;
import android.view.Menu;

public class MainActivity extends Activity {

	public static final Uri messageuri=Uri.parse("content://aaa.bbb.ccc");
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getContentResolver().registerContentObserver(messageuri, true, new MyObserver(new Handler()));
		
	}
	
	private class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			System.out.println("第三个应用，观察到数据库变化");
		}
		
		
	}

}
