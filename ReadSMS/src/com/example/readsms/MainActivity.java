package com.example.readsms;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.URIResolver;

import com.example.readsms.domain.SmsInfo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
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
		Uri uri=Uri.parse("content://sms/");
		ContentResolver resovler=getContentResolver();
		Cursor cursor=resovler.query(uri, new String[]{"address","type","body","date"}, null, null, null);
		List<SmsInfo> smsInfos=new ArrayList<SmsInfo>();
		while(cursor.moveToNext()){
			String address=cursor.getString(0);
			int type=cursor.getInt(1);
			String body=cursor.getString(2);
			long date=cursor.getLong(3);
			System.out.println("address:"+address);
			System.out.println("type:"+type);
			System.out.println("body:"+body);
			SmsInfo smsInfo=new SmsInfo(date,type, body, address);
			smsInfos.add(smsInfo);
			
		}
		cursor.close();
		SmsUtils.backUpSms(smsInfos, this);
	}
	

}
