package com.yin.music.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yin.music.adapter.TopListMusicAdapter;
import com.yin.music.constants.Constants;
import com.yin.music.model.TopListMusic;
import com.yin.music.util.ShowApiUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NetMusicActivity extends Activity {
	
	private static final String LOG_TAG = "NetMusicActivity";
	
	private ListView listView;
	private SharedPreferences sp;
	private boolean hasDataInBase;
	private TopListMusicAdapter topListMusicAdapter;
	private LinearLayout layout;
	private List<TopListMusic> topListMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_music_layout);
		this.init();
		this.initOperate();
		
		if (isNetConneted()) {
			//网络连接
			Log.i(LOG_TAG, "net connected");
			//是否可以从数据库取数据
			if (hasDataInBase) {
				
			} else {
				new NetMusicAsync().execute();
			}
			
		} else {
			//TODO 无网络
			
		}
		
	}
	
	private void startListener() {
		this.listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Log.i(LOG_TAG, position + " ; " + topListMusic.size());
				Intent intent = new Intent(NetMusicActivity.this, NetMusicListActivity.class);
				intent.putExtra(Constants.NET_MUSIC_LIST, (Serializable) topListMusic.get(position).getNetMusicList());
				startActivity(intent);
			}
		});
	}
	
	private void init() {
		this.layout = (LinearLayout) findViewById(R.id.netMusicLayout);
		this.listView = (ListView) findViewById(R.id.net_music_list);
		this.sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
	}
	
	private void initOperate() {
		this.layout.setBackgroundResource(R.drawable.zhuti1);
		this.hasDataInBase = this.sp.getBoolean(Constants.NETMUSICBASE, false);
		this.topListMusicAdapter = new TopListMusicAdapter(getApplicationContext());
		this.listView.setAdapter(topListMusicAdapter);
		
		this.topListMusic =  new ArrayList<TopListMusic>();
	}
	
	/**
	 * 获取网络歌曲排行
	 * @author yin
	 *
	 */
	class NetMusicAsync extends AsyncTask<Void, List<TopListMusic>, Void> {
		Map<Integer, String> areaType = Constants.getAreaType();
		Map<Object, Object> params = new HashMap<Object, Object>();
		
		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... param) {
			//遍历地区id查询地区排行榜
			for (Integer key : areaType.keySet()) {
				params.clear();
				params.put("topid", key);
				String resource = ShowApiUtils.getResource(Constants.HOT_LIST, params);
				Log.i(LOG_TAG, resource);
				TopListMusic topMusic = ShowApiUtils.getTopList(resource, key, areaType.get(key));
				if (topMusic != null && topMusic.getNetMusicList() != null) {
					topListMusic.add(topMusic);
					publishProgress(topListMusic);
				}
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(List<TopListMusic>... values) {
			topListMusicAdapter.setTopMusicList(values[0]);
			topListMusicAdapter.notifyDataSetChanged();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Log.i(LOG_TAG, "排行榜加载完成");
			startListener();
		}
		
	}
	
	//根据榜单类型获取歌曲
	/*class NetMusicAsync extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			//TODO 可优化，若数据库有数据，则取数据
			Integer value = params[0];
			Map<String, String> paramMap = null;
			if (value == Constants.HOTLIST) {
				paramMap = new HashMap<String, String>();
				String result = ShowApiUtils.getResource(Constants.HOT_LIST, paramMap);
			} else if (value == Constants.GETMUSICBYTITLE) {
				
			} else if (value == Constants.GETLRCBYMID) {
				
			} else {
				Toast.makeText(getApplicationContext(), "未知的错误", 1000);
			}
			return null;
		}
		
	}*/
	
	/**
	 * 是否存在网络连接
	 * @return
	 */
	private boolean isNetConneted() {
		ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		State net = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		State wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		return (net == State.CONNECTED || wifi == State.CONNECTED);
	}
}
