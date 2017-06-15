package com.yin.music.model;

import java.io.Serializable;

public class Music implements Serializable {
	
	private static final long serialVersionUID = -1964372894205377974L;
	
	private int id;
	private String title;
	private int time;
	private String url;
	private String strTime;
	private int isdeleted;

	public Music() {
		super();
	}

	public Music(String title, int time, String url, String strTime) {
		super();
		this.title = title;
		this.time = time;
		this.url = url;
		this.strTime = strTime;
	}
	
	public Music(int id, String title, int time, String url, String strTime) {
		super();
		this.id = id;
		this.title = title;
		this.time = time;
		this.url = url;
		this.strTime = strTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	@Override
	public boolean equals(Object o) {
		Music music = (Music) o;
		if(this.getTitle().equals(music.getTitle()) 
				&& this.getTime() == music.getTime()
				&& this.getUrl().equals(music.getUrl()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Music [id=" + id + ", title=" + title + ", time=" + time + ", url=" + url + ", strTime=" + strTime
				+ ", isdeleted=" + isdeleted + "]";
	}

}
