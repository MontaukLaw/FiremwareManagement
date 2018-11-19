package com.wulala.entity;

public class FileInfo {
	private String filePath;
	private Long fileSize;
	private String fileMD5;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileMD5() {
		return fileMD5;
	}

	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}

	@Override
	public String toString() {
		return "FileInfo [filePath=" + filePath + ", fileSize=" + fileSize + ", fileMD5=" + fileMD5 + "]";
	}

}
