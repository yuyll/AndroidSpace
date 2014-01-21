package com.yu.phonecal;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText ed;
	private Button btn;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn=(Button) findViewById(R.id.btn_call);
		ed=(EditText) findViewById(R.id.et_number);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		
				String num=ed.getText().toString().trim();
				if(TextUtils.isEmpty(num)){
					Toast.makeText(MainActivity.this,"ºÅÂë²»ÄÜÎª¿Õ",100).show();
					return;
				}
				Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+num) );
				startActivity(intent);
				
			}
		});
	}
}
