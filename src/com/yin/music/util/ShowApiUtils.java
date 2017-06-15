package com.yin.music.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.yin.music.constants.Constants;
import com.yin.music.model.NetMusic;
import com.yin.music.model.TopListMusic;

public class ShowApiUtils {
	
	private static final String LOG_TAG = "ShowApiUtils";
	
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static void addHeader(StringBuffer uri) {
		uri.append("showapi_appid=" + Constants.SHOWAPI_APPID 
					+ "&showapi_sign=" + Constants.SHOWAPI_SIGN
					+ "&showapi_timestamp=" + DATEFORMAT.format(new Date()));
	}
	
	private static void addParams(StringBuffer uri, Map<Object, Object> params) throws Exception {
		for (Object s : params.keySet()) {
			uri.append("&" + s + "=" + URLEncoder.encode(params.get(s).toString(), "UTF-8"));
		}
		
	}
	
	public static String getResource(String addr, Map<Object, Object> params) {
		StringBuffer uri = new StringBuffer(addr + "?");
		StringBuffer result = new StringBuffer();
		try {
			addHeader(uri);
			addParams(uri, params);
			URL url = new URL(uri.toString());
			URLConnection uc = url.openConnection();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			if(br != null) br.close();
			if(is != null) is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	public static TopListMusic getTopList(String resource, int areaId, String areaName) {
		TopListMusic topListMusic = null;
		List<NetMusic> netMusicList = null;
		try {
			JSONObject obj = new JSONObject(resource);
			JSONObject showapi_res_body = obj.getJSONObject("showapi_res_body");
			JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
			if (pagebean != null) {
				topListMusic = new TopListMusic();
				netMusicList = new ArrayList<NetMusic>();
				
				JSONArray arrays = pagebean.getJSONArray("songlist");
				for (int i=0;i<arrays.length();i++) {
					JSONObject musicObj = arrays.getJSONObject(i);
					String songName = musicObj.getString("songname");
					String seconds = musicObj.getString("seconds");
					String albummid = musicObj.getString("albummid");
					String songid = musicObj.getString("songid");
					String singerid = musicObj.getString("singerid");
					String albumpic_big = musicObj.getString("albumpic_big");
					String albumpic_small = musicObj.getString("albumpic_small");
					String downUrl = musicObj.getString("downUrl");
					String mUrl = musicObj.getString("url");
					String singername = musicObj.getString("singername");
					String albumid = musicObj.getString("albumid");
					netMusicList.add(new NetMusic(songName, seconds, albummid, songid, singerid, albumpic_big, albumpic_small, downUrl, mUrl, singername, albumid));
				}
				topListMusic.setNetMusicList(netMusicList);
				topListMusic.setAreaId(areaId);
				topListMusic.setAreaName(areaName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topListMusic;
	}
}
