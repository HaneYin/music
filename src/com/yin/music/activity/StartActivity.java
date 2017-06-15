package com.yin.music.activity;

import java.io.Serializable;
import java.util.List;

import com.yin.music.constants.Constants;
import com.yin.music.model.Music;
import com.yin.music.util.MusicUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class StartActivity extends Activity {
	
	private static final String LOG_TAG = "StartActivity";
	
	private Intent intent;
	private SharedPreferences sp;
	private Editor editor;
	private List<Music> localMusic;
	private boolean needScanMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.init();
		this.initData();
		//TODO 预读取文件
		new StartAsync().execute(this.needScanMusic);
	}
	
	private void init() {
		this.intent = new Intent(StartActivity.this, MainActivity.class);
		this.sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
	}
	
	private void initData() {
		this.needScanMusic = this.sp.getBoolean(Constants.SCANMUSIC, true);
		MusicUtil.initDataBase(getApplicationContext());
	}
	
	class StartAsync extends AsyncTask<Boolean, Void, Void>{

		@Override
		protected Void doInBackground(Boolean... params) {
			boolean needScanMusic = params[0];
			if(needScanMusic) {
				localMusic = MusicUtil.scanLocalMusic();
				MusicUtil.addLocalMusic(localMusic);
				editor = sp.edit();
				editor.putBoolean(Constants.SCANMUSIC, false);
				editor.commit();
			}else{
				//直接从数据库读取列表数据
				localMusic = MusicUtil.getDataBaseMusic();
				Log.i(LOG_TAG, "数据库存在" + localMusic.size() + "首歌曲");
				Log.i(LOG_TAG, localMusic.toString());
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			intent.putExtra(Constants.MUSIC_LIST, (Serializable) localMusic);
			startActivity(intent);
			finish();
		}
		
	}
}
