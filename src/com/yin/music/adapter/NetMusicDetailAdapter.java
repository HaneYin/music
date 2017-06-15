package com.yin.music.adapter;

import java.util.List;

import com.yin.music.model.NetMusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NetMusicDetailAdapter extends BaseAdapter {
	
	private Context context;
	private List<NetMusic> netMusicList;
	
	public void setNetMusicList(List<NetMusic> netMusicList) {
		this.netMusicList = netMusicList;
	}
	
	public NetMusicDetailAdapter(Context context, List<NetMusic> netMusicList) {
		super();
		this.context = context;
		this.netMusicList = netMusicList;
	}

	@Override
	public int getCount() {
		return netMusicList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		NetMusic netMusic = this.netMusicList.get(position);
		String singerName = netMusic.getSingername();
		String song = netMusic.getSongName();
		tv.setText(singerName + " - " +song);
		return convertView;
	}

}
