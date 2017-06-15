package com.yin.music.model;

import java.util.List;

public class TopListMusic {

	private int id;
	private int areaId;
	private String areaName;
	private List<NetMusic> netMusicList;

	public TopListMusic() {
		super();
	}

	public TopListMusic(int id, int areaId, String areaName, List<NetMusic> netMusicList) {
		super();
		this.id = id;
		this.areaId = areaId;
		this.areaName = areaName;
		this.netMusicList = netMusicList;
	}

	public TopListMusic(int areaId, String areaName, List<NetMusic> netMusicList) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.netMusicList = netMusicList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<NetMusic> getNetMusicList() {
		return netMusicList;
	}

	public void setNetMusicList(List<NetMusic> netMusicList) {
		this.netMusicList = netMusicList;
	}

}
