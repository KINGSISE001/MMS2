package cn.lastwhisper;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.lastwhisper.core.util.EmailService;
import cn.lastwhisper.core.util.EmailServiceImpls;






public class test {
	public static void main(String[] args) throws Exception {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		EmailService SfApiDemos= context.getBean(EmailService.class);
		//BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml") ;
		 //EmailServiceImpls simpleOrderManager = (EmailServiceImpls) beanFactory.getBean("emailService");
		SfApiDemos.sendMailSimple("949092641@qq.com", "测试" ,"测试");

	//SfApiDemos.listCard();
		
		 
	}
	 
	 
}
