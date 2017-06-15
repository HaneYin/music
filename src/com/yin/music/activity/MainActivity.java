package com.yin.music.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yin.music.adapter.DrawerAdapter;
import com.yin.music.adapter.ViewPagerAdapter;
import com.yin.music.constants.Constants;
import com.yin.music.fragment.MusicListFragment;
import com.yin.music.fragment.NetPlayFragment;
import com.yin.music.fragment.NowPlayingFragment;
import com.yin.music.model.Music;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private TextView tv_nowPlaying, tv_musicList, tv_netMusic, tv_showMusicName;
	private ImageButton play;
	private ImageView model;
	private ViewPager viewPager;
	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private boolean drawerStatus;
	private DrawerAdapter drawerAdapter;
	private ViewPagerAdapter viewPagerAdapter;
	private AudioManager audioManager;
	private Map<Integer, TextView> tvMap;//存储tv用以改变按钮字体颜色
	private String [] modelStr;//模式内容
	private int [] modelIcon;//模式图标
	private long time;
	
	private RelativeLayout mainLayout;//主页布局 切换皮肤
	private List<Music> localMusic;//接收传递进来的音乐列表
	private List<Fragment> fragList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);
		this.init();
		this.initAttribute();
		this.initData();
		this.initViewPager();
		this.anonymousListener();
		this.widgetListener();
	}
	
	private void init() {
		this.tv_nowPlaying = (TextView) findViewById(R.id.tvplay);
		this.tv_musicList = (TextView) findViewById(R.id.tvlist);
		this.tv_netMusic = (TextView) findViewById(R.id.tvsearch);
		this.tv_showMusicName = (TextView) findViewById(R.id.main_song_name);
		this.play = (ImageButton) findViewById(R.id.play);
		this.model = (ImageView) findViewById(R.id.model);
		this.viewPager = (ViewPager) findViewById(R.id.viewpager);
		this.drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
		this.drawerListView = (ListView) findViewById(R.id.drawer_menu_listview);
		this.mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
	}
	
	@SuppressWarnings({ "unchecked" })
	@SuppressLint("UseSparseArrays")
	private void initAttribute() {
		this.drawerStatus = false;
		this.drawerAdapter = new DrawerAdapter(getApplicationContext());
		this.viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		this.audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		this.localMusic = (List<Music>) getIntent().getSerializableExtra(Constants.MUSIC_LIST);
		this.fragList = new ArrayList<Fragment>();
	}
	
	private void initData() {
		this.tvMap = new HashMap<Integer, TextView>();
		this.tvMap.put(0, tv_musicList);
		this.tvMap.put(1, tv_nowPlaying);
		this.tvMap.put(2, tv_netMusic);
		this.modelStr = new String [] { "循环", "单曲", "随机" };
		this.modelIcon = new int [] { R.drawable.loop, R.drawable.only, R.drawable.random };
		this.time = 0;
	}
	
	private void initViewPager() {
		MusicListFragment musicListFragment = new MusicListFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(Constants.MUSIC_LIST, (Serializable) localMusic);
		musicListFragment.setArguments(bundle);
		this.fragList.add(musicListFragment);
		this.fragList.add(new NowPlayingFragment());
		this.fragList.add(new NetPlayFragment());
		this.viewPagerAdapter.setFragList(fragList);
		this.viewPager.setAdapter(viewPagerAdapter);
		this.viewPager.setOffscreenPageLimit(3);
	}
	
	
	
	@SuppressWarnings("deprecation")
	private void anonymousListener() {
		
		this.drawerLayout.setDrawerListener(new DrawerListener() {
			
			@Override
			public void onDrawerStateChanged(int arg0) {
				
			}
			
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				
			}
			
			@Override
			public void onDrawerOpened(View arg0) {
				drawerStatus = true;
				drawerListView.setAdapter(drawerAdapter);
				drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						drawerLayout.closeDrawer(Gravity.LEFT);
						switch (position) {
						case 0://搜索
							viewPager.setCurrentItem(2);
							break;
						case 1://音量
							audioManager.setStreamVolume(
									AudioManager.STREAM_MUSIC,
									audioManager.getStreamVolume(AudioManager.STREAM_MUSIC),1);
							break;
						case 2://主题
							int requestCode = 0;
							Intent intent = new Intent(MainActivity.this,SkinActivity.class);
							startActivityForResult(intent, requestCode);
							break;
						case 3://主页
							viewPager.setCurrentItem(0);
							break;
						case 4://网络歌曲
							Intent intent2 = new Intent(MainActivity.this,NetMusicActivity.class);
							startActivity(intent2);
						case 5://退出
							finish();
							break;
						default:
							break;
						}
					}
				});
			}
			
			@Override
			public void onDrawerClosed(View arg0) {
				drawerStatus = false;
			}
		});
		this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					changeTvColor(0);
					break;
				case 1:
					changeTvColor(1);
					break;
				case 2:
					changeTvColor(2);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if(resultCode == 1){
			int which = intent.getIntExtra(Constants.BACKGROUND_ITEM, -1);
			this.mainLayout.setBackgroundResource(Constants.image[which]);
		}
	}
	
	private void widgetListener() {
		this.tv_musicList.setOnClickListener(listener);
		this.tv_nowPlaying.setOnClickListener(listener);
		this.tv_netMusic.setOnClickListener(listener);
		this.model.setOnClickListener(listener);
	}
	
	private View.OnClickListener listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.tvlist:
				viewPager.setCurrentItem(0);
				changeTvColor(0);
				break;
			case R.id.tvplay:
				viewPager.setCurrentItem(1);
				changeTvColor(1);
				break;
			case R.id.tvsearch:
				viewPager.setCurrentItem(2);
				changeTvColor(2);
				break;
			case R.id.previous:
				break;
			case R.id.play:
				break;
			case R.id.next:
				break;
			case R.id.model:
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("模式选择")
						.setItems(modelStr,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										model.setImageResource(modelIcon[which]);
									}
								}).show();
				break;
			}
		}
	};
	
	/**
	 * 改变tv显示当前页颜色
	 * @param position
	 */
	private void changeTvColor(int position) {
		tvMap.get(position).setTextColor(Color.RED);
		for(Integer i : tvMap.keySet()) {
			if(i!=position)
				tvMap.get(i).setTextColor(Color.BLACK);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			if (this.drawerStatus) {
				drawerLayout.closeDrawer(Gravity.LEFT);
				return false;
			} else {
				if (System.currentTimeMillis() - time > 2000) {
					time = System.currentTimeMillis();
					Toast.makeText(getApplicationContext(), "再按一次退出",Toast.LENGTH_SHORT).show();
					return true;
				} else {
					finish();
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
}
