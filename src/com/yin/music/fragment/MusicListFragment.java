package com.yin.music.fragment;

import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yin.music.activity.R;
import com.yin.music.adapter.MusicListAdapter;
import com.yin.music.constants.Constants;
import com.yin.music.model.Music;
import com.yin.music.util.MusicUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MusicListFragment extends Fragment {
	
	private Bundle bundle;
	private MusicListAdapter musicListAdapter;
	private List<Music> localMusic;
	private PullToRefreshListView mPullRefreshListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.music_list_fragment, container,false);
		this.mPullRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);
		this.init();
		this.listViewListener();
		return view;
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		this.bundle = getArguments();
		this.localMusic = (List<Music>) bundle.getSerializable(Constants.MUSIC_LIST);
		this.musicListAdapter = new MusicListAdapter(getActivity(), localMusic);
		this.mPullRefreshListView.setAdapter(musicListAdapter);
	}
	
	private void listViewListener() {
		this.mPullRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				musicListAdapter.setClickRowNum(position);
//				musicListAdapter.notifyDataSetChanged();
			}
		});
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				
				new PullAsync().execute();
			}
		});
	}
	
	class PullAsync extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			localMusic.clear();
			localMusic = MusicUtil.scanLocalMusic();
			MusicUtil.manageMusic(localMusic);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			musicListAdapter.setLocalMusic(localMusic);
			musicListAdapter.notifyDataSetChanged();
			mPullRefreshListView.onRefreshComplete();
		}
		
	}
}
