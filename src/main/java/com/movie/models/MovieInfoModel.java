package com.movie.models;

public class MovieInfoModel {
	
	//State
	private int mid;
	private String bdate;
	private int tid;
	private int sid;
	
	//Getters and setters
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	//To String
	@Override
	public String toString() {
		return "MovieInfoModel [mid=" + mid + ", bdate=" + bdate + ", tid=" + tid + ", sid=" + sid + "]";
	}
	
	
}
