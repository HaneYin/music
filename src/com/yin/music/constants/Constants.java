package com.yin.music.constants;

import java.util.HashMap;
import java.util.Map;

import com.yin.music.activity.R;

public class Constants {
	//图片皮肤id
	public static final int [] image = { R.drawable.zhuti1, R.drawable.zhuti2, R.drawable.zhuti3, R.drawable.zhuti4, R.drawable.zhuti5, R.drawable.zhuti6 };
	//主题名称
	public static final String [] name = {"主题一","主题二","主题三","主题四","主题五","主题六"};
	public static final String SP_NAME = "music_sc_record";//SharedPreferences存储的文件名
	
	public static final String SCANMUSIC = "scan_music";//记录是否需要扫描本地音乐
	public static final String NETMUSICBASE = "net_music_base";//记录是否需要从数据库取排行
	
	public static final String TABLE_NAME = "music";
	
	public static final String BACKGROUND_ITEM = "which";
	public static final String NET_MUSIC_LIST = "net_music_list";
	
	public static final String MUSIC_LIST = "music_list";
	
	public static final String HOT_LIST = "https://route.showapi.com/213-4";
	public static final String GET_MUSIC_BY_TITLE = "https://route.showapi.com/213-1";
	public static final String GET_LRC_BY_MID = "https://route.showapi.com/213-2";
	public static final String SHOWAPI_APPID = "16438";
	public static final String SHOWAPI_SIGN = "a591ace5fb3b4d2aab6b558af9e9a75b";
	
	public static final Integer HOTLIST = 1;
	public static final Integer GETMUSICBYTITLE = 2;
	public static final Integer GETLRCBYMID = 3;
	
	public static Map<Integer, String> getAreaType() {
		Map<Integer, String> area = new HashMap<Integer, String>();
		area.put(26, "热歌");
		area.put(5, "内地");
		area.put(6, "港台");
		area.put(18, "民谣");
		area.put(19, "摇滚");
		area.put(23, "销量");
		area.put(3, "欧美");
		area.put(16, "韩国");
		area.put(17, "日本");
		return area;
	}
}
