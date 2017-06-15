package com.yin.music.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yin.music.activity.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {
	
	private List<String> content;
	private List<Integer> icon;
	private Context context;
	
	public DrawerAdapter(Context context) {
		super();
		this.context = context;
		this.content = new ArrayList<String>();
		this.icon = new ArrayList<Integer>();
		this.content.add("搜索");
		this.content.add("音量");
		this.content.add("皮肤");
		this.content.add("主页");
		this.content.add("排行");
		this.content.add("退出");
		this.icon.add(R.drawable.d_search);
		this.icon.add(R.drawable.d_setting);
		this.icon.add(R.drawable.d_skin);
		this.icon.add(R.drawable.d_home);
		this.icon.add(R.drawable.d_home);
		this.icon.add(R.drawable.d_exist);
	}

	@Override
	public int getCount() {
		return content.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=LayoutInflater.from(context).inflate(R.layout.drawer_listview_item, null);
		ImageView img=(ImageView) convertView.findViewById(R.id.d_image);
		TextView tv=(TextView) convertView.findViewById(R.id.d_item_textview);
		if(icon!=null){
			img.setImageResource(icon.get(position));
		}
		tv.setText(content.get(position));
		return convertView;
	}

}
