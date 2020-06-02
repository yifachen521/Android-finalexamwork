package com.cn.muyahai;

public class News {
	private String cityname;
	private String state;
	private String mintemp;
	private String maxtemp;
	private String nowtemp;
	private String windir;
	private String winpower;
	private String imageurl;
	public News(String cityname,String state,String mintemp,String maxtemp,String nowtemp,String windir,String winpower) {
		this.cityname=cityname;
		this.state = state;
		this.mintemp=mintemp;
		this.maxtemp=maxtemp;
		this.nowtemp=nowtemp;
		this.windir=windir;
		this.winpower=winpower;
	}
	public News() {
		// TODO Auto-generated constructor stub
	}

	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMintemp() {
		return mintemp;
	}
	public void setMintemp(String mintemp) {
		this.mintemp = mintemp;
	}
	public String getMaxtemp() {
		return maxtemp;
	}
	public void setMaxtemp(String maxtemp) {
		this.maxtemp = maxtemp;
	}
	public String getNowtemp() {
		return nowtemp;
	}
	public void setNowtemp(String nowtemp) {
		this.nowtemp = nowtemp;
	}
	public String getWindir() {
		return windir;
	}
	public void setWindir(String windir) {
		this.windir = windir;
	}
	public String getWinpower() {
		return winpower;
	}
	public void setWinpower(String winpower) {
		this.winpower = winpower;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}


}
