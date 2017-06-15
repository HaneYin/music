package com.yin.music.adapter;

import java.util.List;

import com.yin.music.activity.R;
import com.yin.music.model.NetMusic;
import com.yin.music.model.TopListMusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TopListMusicAdapter extends BaseAdapter {
	
	private Context context;
	private List<TopListMusic> topMusicList;
	
	public TopListMusicAdapter(Context context) {
		super();
		this.context = context;
	}

	public TopListMusicAdapter(Context context, List<TopListMusic> topMusicList) {
		super();
		this.context = context;
		this.topMusicList = topMusicList;
	}
	
	public void setTopMusicList(List<TopListMusic> topMusicList) {
		this.topMusicList = topMusicList;
	}

	@Override
	public int getCount() {
		return topMusicList != null ? topMusicList.size() : 0;
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.net_music_list_item, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.areaView);
			holder.areaName = (TextView) convertView.findViewById(R.id.areaName);
			holder.top1 = (TextView) convertView.findViewById(R.id.top1);
			holder.top2 = (TextView) convertView.findViewById(R.id.top2);
			holder.top3 = (TextView) convertView.findViewById(R.id.top3);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		TopListMusic topListMusic = topMusicList.get(position);
		String areaName = topListMusic.getAreaName();
		List<NetMusic> netMusicList = topListMusic.getNetMusicList();
		holder.areaName.setText(areaName);
		if (netMusicList != null && netMusicList.size() > 3) {
			holder.top1.setText(netMusicList.get(0).getSingername() + " - " + netMusicList.get(0).getSongName());
			holder.top2.setText(netMusicList.get(1).getSingername() + " - " + netMusicList.get(1).getSongName());
			holder.top3.setText(netMusicList.get(2).getSingername() + " - " + netMusicList.get(2).getSongName());
		}
		return convertView;
	}
	
	class ViewHolder {
		public ImageView imageView;
		public TextView areaName;
		public TextView top1;
		public TextView top2;
		public TextView top3;
	}

}
