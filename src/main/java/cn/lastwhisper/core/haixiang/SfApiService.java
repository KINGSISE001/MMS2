package cn.lastwhisper.core.haixiang;

public interface SfApiService {
	
	SeaFlyResult getVipCards(SfApiConfig sfApiConfig,String page, String rows, String accountName) throws Exception;
	SeaFlyResult jfSyn(SfApiConfig sfApiConfig,String accountName,Integer integral,Integer vipid) throws Exception;
	
}
