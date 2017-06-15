package com.yin.music.sqlite;

import java.util.ArrayList;
import java.util.List;

import com.yin.music.model.Music;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlite extends SQLiteOpenHelper {
	public MySqlite(Context context) {
		super(context, "music.db", null, 1);
	}

	public MySqlite(Context context, String name, CursorFactory factory, int version) {
		super(context, "music.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}
	
	public void createTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "create table music(id integer primary key autoincrement,title varchar not null,time integer not null,url varchar not null,strtime varchar not null,isdeleted integer not null)";
		db.execSQL(sql);
		db.close();
	}
	
	public void addMusic(Music music) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "insert into music (title,time,url,strtime,isdeleted) values (?,?,?,?,?)";
		db.execSQL(sql,new Object[] { music.getTitle(), music.getTime(), music.getUrl(), music.getStrTime(), 0 });
		db.close();
	}

	public void deleteFromUrl(String song_url) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "update music set isdeleted=1 where url=?";
		db.execSQL(sql, new String[] { song_url});
		db.close();
	}
	
	public void addMusicLists(List<Music> musicList) {
		List<String> sqls = new ArrayList<String>();
		for(Music m : musicList) {
			sqls.add("insert into music (title,time,url,strtime,isdeleted) values ('" + m.getTitle() + "','" + m.getTime() + "','" + m.getUrl() + "','" + m.getStrTime() + "','0')");
		}
		SQLiteDatabase db = this.getWritableDatabase();
		db.beginTransaction();
		for(String sql : sqls){
			db.execSQL(sql);
		}
		db.setTransactionSuccessful();
		db.endTransaction();
		db.close();
	}
	
	public List<Music> getAllMusic(){
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select * from music where isdeleted = 0";
		Cursor cursor = db.rawQuery(sql, null);
		List<Music> list = new ArrayList<Music>();
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			String title = cursor.getString(cursor.getColumnIndex("title"));
			int duration = cursor.getInt(cursor.getColumnIndex("time"));
			String url = cursor.getString(cursor.getColumnIndex("url"));
			String strTime = cursor.getString(cursor.getColumnIndex("strtime"));
			list.add(new Music(id, title, duration, url, strTime));
		}
		db.close();
		return list;
	}

	public boolean tabIsExist(String tabName) {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = this.getReadableDatabase();
			String sql = "select count(*) as c from sqlite_master where type ='table' and name ="
					+ "'" + tabName.trim() + "'";
			cursor = db.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			
		}
		db.close();
		return false;
	}

	public void deleteMusic(Music music) {
		String url=music.getUrl();
		deleteFromUrl(url);
	}

	public void manageMusic(List<Music> musicList){
		List<Music> localMusic = new ArrayList<Music>();
		localMusic.addAll(musicList);
		List<Music> baseMusic=getAllMusic();
		List<Music> reserveMusic = new ArrayList<Music>();
		if(baseMusic==null || baseMusic.size()<=0){
			for(Music m : localMusic){
				addMusic(m);
			}
		}else{
			for(Music m : localMusic){
				if(baseMusic.contains(m)){
					reserveMusic.add(m);
				}
			}
			for(Music m : reserveMusic){
				baseMusic.remove(m);
				localMusic.remove(m);
			}
			for(Music m : localMusic){
				addMusic(m);
			}
			for(Music m : baseMusic){
				deleteMusic(m);
			}
		}
	}
}
