package org.yndongyong.mvp.sample.data;


import java.io.Serializable;

public class AppFileItemEntity implements Serializable{

	public static final transient String KEY = AppFileItemEntity.class.getSimpleName();
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String url;
	private boolean bold;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	@Override
	public String toString() {
		return "AppFileItemEntity [id=" + id + ", title=" + title + ", url=" + url
				+ ", bold=" + bold + "]";
	}

	
	
}
