package com.example.serilize;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;

import com.example.writeXml.SmsInfo;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn;
	private List<SmsInfo> smsinfos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button) findViewById(R.id.save);
		smsinfos = new ArrayList<SmsInfo>();
		Random random = new Random();
		long number = 15800000000L;
		for (int i = 0; i < 10; i++) {
			smsinfos.add(new SmsInfo(System.currentTimeMillis(), random
					.nextInt(2) + 1, "短信内容", Long.toString(number), i));
			
		}
	}

	public void save(View v) {
		try {
			XmlSerializer xs = Xml.newSerializer();
			File f=new File(Environment.getExternalStorageDirectory(),"backup.xml");
			FileOutputStream fos=new FileOutputStream(f);
			xs.setOutput(fos,"utf-8");
			xs.startDocument("utf-8",true);
			xs.startTag(null, "SMSS");
				for(SmsInfo info:smsinfos){
					xs.startTag(null, "SMS");
						xs.startTag(null,"body");
						xs.attribute(null, "id",info.getId()+"");
						xs.endTag(null, "body");
						
						xs.startTag(null,"date");
						xs.text(Long.toString(info.getDate()));
						xs.endTag(null, "date");
						
						xs.startTag(null,"type");
						xs.text(info.getType()+"");
						xs.endTag(null, "type");
						
						xs.startTag(null,"address");
						xs.text(info.getAddress());
						xs.endTag(null, "address");
					xs.endTag(null,"SMS");
				}				
			xs.endTag(null, "SMSS");
			xs.endDocument();
			fos.close();
			Toast.makeText(this, "保存成功",Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "保存失败",Toast.LENGTH_SHORT).show();
			
		}
		
	}

}
