package cn.lastwhisper.core.util;

public interface EmailService {

	void sendMailSimple(String to, String subject, String content) throws Exception ;
	
	void mesPost(String mes);
}
