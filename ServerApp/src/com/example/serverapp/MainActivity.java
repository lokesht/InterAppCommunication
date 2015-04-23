package com.example.serverapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init(savedInstanceState);
	}

	public void init(Bundle savedInstanceState) {
		text = (TextView) findViewById(R.id.tv_show_text);
		etMessageBox = (EditText) findViewById(R.id.et_add_name);
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

	public void onSave(View v) {
		writeFile();
	}

	public void writeFile() {
		// File file = null;
		FileOutputStream fos = null;
		String status = etMessageBox.getText().toString();

		try {
			// file = getFilesDir();
			fos = openFileOutput("server.text", Context.MODE_PRIVATE);

			fos.write(status.getBytes());

			text.setBackgroundColor(Color.GREEN);
			text.setText(status + " Added TO file");

		} catch (FileNotFoundException e) {
			text.setBackgroundColor(Color.RED);
			text.setText(e.toString());

		} catch (IOException e) {
			text.setBackgroundColor(Color.RED);
			text.setText(e.toString());
		}

	}
}
