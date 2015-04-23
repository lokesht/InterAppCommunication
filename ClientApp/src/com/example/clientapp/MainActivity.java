package com.example.clientapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView text;
	private EditText etMessageBox;

	String packageName = "com.example.serverapp";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init(savedInstanceState);
	}

	public void init(Bundle savedInstanceState) {
		text = (TextView) findViewById(R.id.tv_show_text);
		etMessageBox = (EditText) findViewById(R.id.et_get_name);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onGet(View v) {
		loadFile();
	}

	private void readFile(String fullpath) {
		File f = new File(fullpath);
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(f);

			int read = -1;
			StringBuffer strBuff = new StringBuffer();
			while ((read = fis.read()) != -1) {
				strBuff.append((char) read);
			}

			etMessageBox.setText(strBuff);
			text.setText("Found String");
			
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			text.setText(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			text.setText(e.toString());
		}
		finally{
			if(fis!=null)
				try {
					fis.close();
				} catch (IOException e) {
					text.setText(e.toString());
					e.printStackTrace();
				}
		}

	}

	public void loadFile() {
		PackageManager packageManage = getPackageManager();
		try {
			ApplicationInfo appInfo = packageManage.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
			String fullPath = appInfo.dataDir + "/files/server.text";
			readFile(fullPath);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			text.setText(e.toString());
		}
	}
}
