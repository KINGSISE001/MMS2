/**  
 * @Title:  RoleService.java   
 * @Package cn.lastwhisper.service   
 * @Description: TODO(用一句话描述该文件做什么)
 * @author:     最后的轻语_dd43   
 * @date:   2019年4月6日 下午2:45:32   
 * @version V1.0 
 */
package cn.lastwhisper.core.util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.junit.Test;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cn.lastwhisper.modular.pojo.User;
import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

/**
 * 
 * @ClassName:  UserUtils   
 * @Description:获取当前登录的用户  
 * @author:     最后的轻语_dd43
 * @date:       2019年4月5日
 */
public class UserUtils {
	/**
	 * 
	 * @Title: getSubjectUser   
	 * @Description: 获取shiro中登录的用户
	 * @return
	 */
	public static User getSubjectUser() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	/**
	 * 
	 * @Title: removeSubjectUser   
	 * @Description: 从shiro中移除登录的用户
	 * @author: 最后的轻语_dd43
	 */
	public static void removeSubjectUser() {
		SecurityUtils.getSubject().logout();
	}
	/**
	 * 
	 * @Title: getRequest   
	 * @Description: 获取当前的request
	 * @author: 最后的轻语_dd43    
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
	}
	
	/**
	 * 获取IP地址的方法
	 * 
	 * @return
	 */
	public static String getIpAddress() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 
	 * @Title: getSessionUser   
	 * @Description: 获取session中登录的用户（开启shiro后失效）
	 * @return
	 */
	@Deprecated
	public static User getSessionUser() {
		return (User)getRequest().getSession().getAttribute("user");
	}
	
	/**
	 * 
	 * @Title: setSessionUser   
	 * @Description: 将当前登录的用户信息放入session（开启shiro后失效）
	 * @param user
	 */
	@Deprecated
	public static void setSessionUser(User user) {
		getRequest().getSession().setAttribute("user", user);
	}
	/**
	 * 
	 * @Title: removeSessionUser   
	 * @Description: 从session中移除user（开启shiro后失效）
	 * @author: 最后的轻语_dd43
	 */
	@Deprecated
	public static void removeSessionUser() {
		getRequest().getSession().removeAttribute("user");
	}
	
	@Test
	public void shouldAnswerWithTrue() {
	    //数据源
	    HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
	    hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/permission?characterEncoding=UTF-8");
	    hikariConfig.setUsername("root");
	    hikariConfig.setPassword("123456");
	    //设置可以获取tables remarks信息
	    hikariConfig.addDataSourceProperty("useInformationSchema", "true");
	    hikariConfig.setMinimumIdle(2);
	    hikariConfig.setMaximumPoolSize(5);
	    DataSource dataSource = new HikariDataSource(hikariConfig);
	    //生成配置
	    EngineConfig engineConfig = EngineConfig.builder()
	            //生成文件路径
	            .fileOutputDir("/Users/lengleng")
	            //打开目录
	            .openOutputDir(true)
	            //文件类型
	            .fileType(EngineFileType.WORD)
	            //生成模板实现
	            .produceType(EngineTemplateType.freemarker).build();

	    //忽略表
	    ArrayList<String> ignoreTableName = new ArrayList<>();
	   // ignoreTableName.add("completedorder");
	 //   ignoreTableName.add("detail");
	    //忽略表前缀
	    ArrayList<String> ignorePrefix = new ArrayList<>();
	    ignorePrefix.add("l");
	    ignorePrefix.add("m");
	    ignorePrefix.add("r");
	    ignorePrefix.add("u");
	    //忽略表后缀
	    ArrayList<String> ignoreSuffix = new ArrayList<>();
	    ignoreSuffix.add("_test");
	    ProcessConfig processConfig = ProcessConfig.builder()
	            //忽略表名
	            .ignoreTableName(ignoreTableName)
	            //忽略表前缀
	            .ignoreTablePrefix(ignorePrefix)
	            //忽略表后缀
	            .ignoreTableSuffix(ignoreSuffix).build();
	    //配置
	    Configuration config = Configuration.builder()
	            //版本
	            .version("1.0.0")
	            //描述
	            .description("订单字段说明")
	            //数据源
	            .dataSource(dataSource)
	            //生成配置
	            .engineConfig(engineConfig)
	            //生成配置
	            .produceConfig(processConfig).build();
	    //执行生成
	    new DocumentationExecute(config).execute();
	
}
}