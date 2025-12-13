package cn.lastwhisper.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;


import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import cn.lastwhisper.modular.vo.EmailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;



import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



@Service
public class EmailServiceImpls implements EmailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpls.class);


	@Resource
	EmailInfo emailInfo;

	/**
	 * @方法名: sendMailSimple
	 * @参数名：@param Title  邮件主题
	 * @参数名：@param content 邮件内容
	 * @参数名：@param to     收件人Email地址
	 * @描述语: 发送邮件
	 */
	@Override
	public void sendMailSimple(String to, String Title, String content) throws Exception {

		final Properties props = new Properties();

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.checkserveridentity","true");
		props.put("mail.smtp.ssl.protocols","TLSv1.2");



		JavaMailSenderImpl sender = new JavaMailSenderImpl();

		sender.setHost("smtp.qq.com");
		sender.setPort(465);
		sender.setUsername(emailInfo.getUserName());
		sender.setPassword(emailInfo.getPassword()); //不是你登录邮箱的密码
		sender.setJavaMailProperties(props);


		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
			helper.setFrom(emailInfo.getUserName()); // 发送人
			helper.setTo(to); // 收件人  
			helper.setSubject(Title); // 标题
			helper.setText(buildContent(Title,content),true); // 内容
			sender.send(message);
			System.out.println("发送完毕！");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String buildContent(String title,String txt) throws IOException {
        //加载邮件html模板
	
		
		File file = ResourceUtils.getFile("classpath:mailtemplate.xml");
	    FileInputStream fis = new FileInputStream(file);
		
    
       
    
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
           
            fileReader = new BufferedReader(new InputStreamReader(fis));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
        	LOGGER.info("发送邮件读取模板失败{}", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                	fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), title,txt);
    }
	/**
	 * 
	 *  Description:发送短信
	 *  userid:192
account:tianli1
password:123321asd
mobile:18629265793
content:【卓信康】测试！
sendTime:
action:send
extno:
	 *  @author wangsheng  DateTime 2022年10月18日 上午11:58:53
	 */

	public void mesPost(String mes) {
		OkHttpClient mOkHttpClient = new OkHttpClient();
		FormBody.Builder fBB = new FormBody.Builder();
		fBB.add("userid","192");
		fBB.add("account", "tianli1");
		fBB.add("password","123321asd");
		fBB.add("mobile", "17392961810");
		fBB.add("content", "【卓信康】 "+mes+",退订回T");
		fBB.add("sendTime", "");
		fBB.add("action", "send");
		fBB.add("extno", "");
        Request request = new Request.Builder()
                .post(fBB.build())
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url("http://114.55.88.173:9988/sms.aspx")
                .build();
   
        try (Response response = mOkHttpClient.newCall(request).execute()) {
            System.out.println(response.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	
}
