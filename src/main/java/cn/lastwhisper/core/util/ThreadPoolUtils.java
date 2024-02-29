package cn.lastwhisper.core.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
public class ThreadPoolUtils {
	 private static final ExecutorService threadPool;

	    static {
	        int corePoolSize = Runtime.getRuntime().availableProcessors();
	        int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2;
	        long keepAliveTime = 5;
	        TimeUnit keepAliveTimeUnit = TimeUnit.MINUTES;
	        int queSize = 100_000;
	        ThreadFactory threadFactory = new ThreadFactoryBuilder()
	                .setNameFormat("线程执行！")
	                .build();

	        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
	                keepAliveTime, keepAliveTimeUnit, new ArrayBlockingQueue<>(queSize)
	                , threadFactory);
	    }


	    /**
	     * 获取线程池
	     * @return 线程池
	     */
	    public ExecutorService getThreadPool() {
	        return threadPool;
	    }
}
