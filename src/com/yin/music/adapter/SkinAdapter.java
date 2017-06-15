package com.yin.music.adapter;

import com.yin.music.activity.R;
import com.yin.music.constants.Constants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SkinAdapter extends BaseAdapter {
	private Context context;
	
	public SkinAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	public int getCount() {
		return Constants.image.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint({ "ViewHolder", "InflateParams" }) 
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		view=LayoutInflater.from(context).inflate(R.layout.skinitem, null);
		ImageView img=(ImageView) view.findViewById(R.id.skinItemImg);
		TextView tv=(TextView) view.findViewById(R.id.skinTextView);
		img.setImageResource(Constants.image[position]);
		tv.setText(Constants.name[position]);
		return view;
	}

}
