package cn.lastwhisper.core.task;


import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.scheduling.annotation.SchedulingConfigurer;

import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 
 *  Class Name: ScheduleConfig.java
 *  Description: 自定义线程池
 *  @author wangshneg  DateTime 2022年2月25日 下午1:05:30 
 *  @company tlzy 
 *  @email 949092641@qq.com  
 *  @version 1.0
 *  corePoolSize： 线程池核心线程数
*maximumPoolSize：线程池最大数
*keepAliveTime： 空闲线程存活时间
*unit： 时间单位
*workQueue： 线程池所使用的缓冲队列
*threadFactory：线程池创建线程使用的工厂
*handler： 线程池对拒绝任务的处理策略
 */
@Configuration
public class ScheduleConfig  implements SchedulingConfigurer{


		@Bean
		  public ScheduledExecutorService concurrentTaskScheduler(){
		    ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);
		    executorService.setMaximumPoolSize(1);
		    executorService.setRejectedExecutionHandler(new ScheduledThreadPoolExecutor.CallerRunsPolicy());
		    return executorService;
		  }
	

	
	
	  @Override
	  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	    taskRegistrar.setScheduler(concurrentTaskScheduler());
	  }
	  
	  

}
