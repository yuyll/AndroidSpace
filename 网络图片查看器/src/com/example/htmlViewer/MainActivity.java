package com.example.htmlViewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	protected static final int HTMLVIEW = 1;
	private ImageView iv;
	private EditText et;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == HTMLVIEW) {
				Bitmap bitmap=(Bitmap) msg.obj;
				iv.setImageBitmap(bitmap);
			}

		}

	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		et = (EditText) findViewById(R.id.et_path);

	}

	public void click(View view) {
		new Thread() {
			public void run() {
				try {
					String path = et.getText().toString().trim();
					Message message = new Message();
					message.what = HTMLVIEW;
					URL url = new URL(path);
					HttpURLConnection conn =(HttpURLConnection)url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; qdesk 2.4.1264.203; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)");
					conn.setReadTimeout(5000);					
					InputStream is = conn.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					message.obj = bitmap;
					handler.sendMessage(message);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();

	}

}
