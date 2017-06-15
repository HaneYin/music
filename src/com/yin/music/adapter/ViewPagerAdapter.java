package com.yin.music.adapter;

import java.util.ArrayList;
import java.util.List;

import com.yin.music.fragment.MusicListFragment;
import com.yin.music.fragment.NetPlayFragment;
import com.yin.music.fragment.NowPlayingFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragList;
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
//		this.fragList = new ArrayList<Fragment>();
//		this.fragList.add(new MusicListFragment());
//		this.fragList.add(new NowPlayingFragment());
//		this.fragList.add(new NetPlayFragment());
	}
	
	public void setFragList(List<Fragment> fragList) {
		this.fragList = fragList;
	}

	@Override
	public Fragment getItem(int arg0) {
		return this.fragList.get(arg0);
	}

	@Override
	public int getCount() {
		return this.fragList.size();
	}

}
