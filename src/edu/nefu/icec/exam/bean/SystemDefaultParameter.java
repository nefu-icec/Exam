package edu.nefu.icec.exam.bean;

public class SystemDefaultParameter 
{
	public static final String CACHE_PATH="cache";
	public static final String ANSWER_PATH = "answer";
	public static final String FILEEXTENSION = "rar";
	public static final String TEMPLATE_PATH = "template";
	private String externalStoragePath;
	private boolean enableExternalStorage;
	
	
	public String getExternalStoragePath() {
		return externalStoragePath;
	}
	public boolean isEnableExternalStorage() {
		return enableExternalStorage;
	}
	public void setExternalStoragePath(String externalStoragePath) {
		this.externalStoragePath = externalStoragePath;
	}
	public void setEnableExternalStorage(boolean enableExternalStorage) {
		this.enableExternalStorage = enableExternalStorage;
	}

	
}
