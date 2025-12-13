package cn.lastwhisper.core.haixiang;
public class SfApiConfig {
	
	//替换成自己的appId
	private final String appId; //= "NYqU3V9P";
	
	//替换成自己的appKey
	private final String appKey ;//= "7qmWYb2V";
	
	private final String domainAddress; //= "http://yun.seaflysoft.com/";
	
	 public SfApiConfig(String appId, String appKey, String domainAddress) {
	        this.appId = appId;
	        this.appKey = appKey;
	        this.domainAddress = domainAddress;
	    }

	public String getAppId() {
		return appId;
	}


	public String getAppKey() {
		return appKey;
	}


	public String getDomainAddress() {
		return domainAddress;
	}



}
