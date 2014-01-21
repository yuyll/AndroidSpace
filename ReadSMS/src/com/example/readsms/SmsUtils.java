package com.example.readsms;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.example.readsms.domain.SmsInfo;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.widget.Toast;




public class SmsUtils {
	public static void backUpSms(List<SmsInfo> smsInfos,Context context){
	try {
		XmlSerializer xs = Xml.newSerializer();
		File f=new File(Environment.getExternalStorageDirectory(),"backup.xml");
		FileOutputStream fos=new FileOutputStream(f);
		xs.setOutput(fos,"utf-8");
		xs.startDocument("utf-8",true);
		xs.startTag(null, "SMSS");
			for(SmsInfo info:smsInfos){
				xs.startTag(null, "SMS");
				xs.attribute(null, "id",info.getId()+"");
					xs.startTag(null,"body");
					xs.text(info.getBody()+"");
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
		Toast.makeText(context, "保存成功",Toast.LENGTH_SHORT).show();
	} catch (Exception e) {
		e.printStackTrace();
		Toast.makeText(context, "保存失败",Toast.LENGTH_SHORT).show();
		
	 }
	
	}
  }

