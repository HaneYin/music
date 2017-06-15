package com.yin.music.activity;

import java.util.List;

import com.yin.music.adapter.NetMusicDetailAdapter;
import com.yin.music.constants.Constants;
import com.yin.music.model.NetMusic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class NetMusicListActivity extends Activity {
	
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_music_list_detail);
		this.listView = (ListView) findViewById(R.id.listView1);
		List<NetMusic> netMusicList = (List<NetMusic>) getIntent().getSerializableExtra(Constants.NET_MUSIC_LIST);
		this.listView.setAdapter(new NetMusicDetailAdapter(getApplicationContext(), netMusicList));
	}
}
