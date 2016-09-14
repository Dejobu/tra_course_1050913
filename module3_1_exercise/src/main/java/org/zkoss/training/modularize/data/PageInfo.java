package org.zkoss.training.modularize.data;

public class PageInfo {
	private String includePath;
	private String bookmark;

	public PageInfo(String path, String bookmark) {
		super();
		this.includePath = path;
		this.bookmark = bookmark;
	}

	public String getIncludePath() {
		return includePath;
	}

	public String getBookmark() {
		return bookmark;
	}
}
