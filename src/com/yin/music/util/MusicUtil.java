package com.yin.music.util;

import java.util.ArrayList;
import java.util.List;

import com.yin.music.constants.Constants;
import com.yin.music.model.Music;
import com.yin.music.sqlite.MySqlite;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

public class MusicUtil {
	
	private static final String LOG_TAG = "MusicUtil";
	
	private static MySqlite sqlite;
	private static Context context;
	
	public static void initDataBase(Context con) {
		if(context == null) context = con;
		if(sqlite == null) sqlite = new MySqlite(context);
		boolean existTable = sqlite.tabIsExist(Constants.TABLE_NAME);
		if(!existTable) sqlite.createTable();
	}
	
	public static List<Music> scanLocalMusic() {
		List<Music> localMusic = new ArrayList<Music>();
		Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		if(cursor != null && cursor.getCount() > 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
				String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
				String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
				String strTime = changeTime(duration);
				localMusic.add(new Music(title, duration, url, strTime));
				Log.i(LOG_TAG, title + ";" + duration + ";" + url);
			}
			Log.i(LOG_TAG, localMusic.size()+" 首歌曲");
		}
		if(cursor != null) cursor.close();
		return localMusic;
	}
	
	public static void addLocalMusic(List<Music> localMusic) {
		sqlite.addMusicLists(localMusic);
	}
	
	public static void manageMusic(List<Music> localMusic) {
		sqlite.manageMusic(localMusic);
	}
	
	public static List<Music> getDataBaseMusic() {
		return sqlite.getAllMusic();
	}
	
	/**
	 * 时间转换
	 * @param time
	 * @return
	 */
	private static String changeTime(int time) {
		int minute = time / 1000 / 60;
		int s = time / 1000 % 60;
		String mm = null, ss = null;
		if (minute < 10) {
			mm = "0" + minute;
		} else {
			mm = minute + "";
		}
		if (s < 10) {
			ss = "0" + s;
		} else {
			ss = "" + s;
		}
		return mm + ":" + ss;
	}
	
}
