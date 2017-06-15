package com.yin.music.adapter;

import java.util.List;

import com.yin.music.activity.R;
import com.yin.music.model.Music;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Music> localMusic;
	private int clickRowNum;
	
	public MusicListAdapter(Context context, List<Music> localMusic) {
		super();
		this.context = context;
		this.localMusic = localMusic;
	}
	
	public void setClickRowNum(int rowNum) {
		this.clickRowNum = rowNum;
	}
	
	public void setLocalMusic(List<Music> localMusic) {
		this.localMusic = localMusic;
	}

	@Override
	public int getCount() {
		return localMusic.size();
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
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.music_list_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.music_list_item_title);
			holder.time = (TextView) convertView.findViewById(R.id.music_list_item_time);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
//		if(position == clickRowNum) {
//			convertView.setBackgroundColor(Color.GREEN);
//		}else{
//			convertView.setBackgroundColor(Color.TRANSPARENT);
//		}
		
		String titleName = localMusic.get(position).getTitle();
		String showTime = localMusic.get(position).getStrTime();
		holder.title.setText(titleName);
		holder.time.setText(showTime);
		return convertView;
	}
	
	class ViewHolder {
		public TextView title;
		public TextView time;
	}

}
