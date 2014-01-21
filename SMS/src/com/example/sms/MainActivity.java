package com.example.sms;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	private EditText num;
	private EditText content;
	private Button send;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		send=(Button) findViewById(R.id.send);
		num=(EditText) findViewById(R.id.phone_num);
		content=(EditText) findViewById(R.id.sms_content);
		send.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		String phoneNum=num.getText().toString().trim();		
		String phoneContent=content.getText().toString().trim();
		SmsManager sms=SmsManager.getDefault();
		ArrayList<String> sl=sms.divideMessage(phoneContent);
		for(String strCon:sl){
			sms.sendTextMessage(phoneNum, null,strCon, null, null);
		}
		
		
	}



}
