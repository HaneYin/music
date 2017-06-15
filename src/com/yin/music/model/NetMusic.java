package com.yin.music.model;

import java.io.Serializable;

public class NetMusic implements Serializable {

	private static final long serialVersionUID = 5141119487337643902L;

	private int id;
	private int topSongId;
	private String songName;
	private String seconds;
	private String albummid;
	private String songid;
	private String singerid;
	private String albumpic_big;
	private String albumpic_small;
	private String downUrl;
	private String url;
	private String singername;
	private String albumid;

	public NetMusic(String songName, String seconds, String albummid, String songid, String singerid,
			String albumpic_big, String albumpic_small, String downUrl, String url, String singername, String albumid) {
		super();
		this.songName = songName;
		this.seconds = seconds;
		this.albummid = albummid;
		this.songid = songid;
		this.singerid = singerid;
		this.albumpic_big = albumpic_big;
		this.albumpic_small = albumpic_small;
		this.downUrl = downUrl;
		this.url = url;
		this.singername = singername;
		this.albumid = albumid;
	}

	public NetMusic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTopSongId() {
		return topSongId;
	}

	public void setTopSongId(int topSongId) {
		this.topSongId = topSongId;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getAlbummid() {
		return albummid;
	}

	public void setAlbummid(String albummid) {
		this.albummid = albummid;
	}

	public String getSongid() {
		return songid;
	}

	public void setSongid(String songid) {
		this.songid = songid;
	}

	public String getSingerid() {
		return singerid;
	}

	public void setSingerid(String singerid) {
		this.singerid = singerid;
	}

	public String getAlbumpic_big() {
		return albumpic_big;
	}

	public void setAlbumpic_big(String albumpic_big) {
		this.albumpic_big = albumpic_big;
	}

	public String getAlbumpic_small() {
		return albumpic_small;
	}

	public void setAlbumpic_small(String albumpic_small) {
		this.albumpic_small = albumpic_small;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSingername() {
		return singername;
	}

	public void setSingername(String singername) {
		this.singername = singername;
	}

	public String getAlbumid() {
		return albumid;
	}

	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}

	@Override
	public String toString() {
		return "NetMusic [id=" + id + ", topSongId=" + topSongId + ", songName=" + songName + ", seconds=" + seconds
				+ ", albummid=" + albummid + ", songid=" + songid + ", singerid=" + singerid + ", albumpic_big="
				+ albumpic_big + ", albumpic_small=" + albumpic_small + ", downUrl=" + downUrl + ", url=" + url
				+ ", singername=" + singername + ", albumid=" + albumid + "]";
	}

}
