package com.yin.music.activity;

import com.yin.music.adapter.SkinAdapter;
import com.yin.music.constants.Constants;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class SkinActivity extends Activity {
	
	private GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skinlayout);
		this.gridView=(GridView) findViewById(R.id.skinGridView);
		this.gridView.setAdapter(new SkinAdapter(getApplicationContext()));
		this.gridView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("ShowToast") @Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int resultCode = 1;
				Intent data = new Intent();
				data.putExtra(Constants.BACKGROUND_ITEM, arg2);
				setResult(resultCode, data);
				Toast.makeText(getApplicationContext(), "主题"+(arg2+1)+" 应用成功", 1000).show();
				finish();
			}
		});
	}
}
