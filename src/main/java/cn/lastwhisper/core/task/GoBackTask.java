package cn.lastwhisper.core.task;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.lastwhisper.core.jifen.jiFens;
import cn.lastwhisper.modular.mapper.hxProductListMapper;
import cn.lastwhisper.modular.mapper.o2o_operate_upload_priceMapper;
import cn.lastwhisper.modular.mt.mtSservice;
import cn.lastwhisper.modular.pojo.hxProductList;
import cn.lastwhisper.modular.pojo.o2o_operate_upload_price;
import cn.lastwhisper.modular.service.MtService;
import cn.lastwhisper.modular.vo.MtResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * @author lastwhisper
 * @date 2020/2/16
 */
@Component
@EnableScheduling
@EnableAsync
public class GoBackTask implements ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(GoBackTask.class);
	private static ApplicationContext applicationContext;
	@Autowired
	private o2o_operate_upload_priceMapper o2o_operate_upload_priceMapper;
	@Autowired
	private MtService MtService;

	@Autowired
	private mtSservice mtSservice;

	@Autowired
	private jiFens jiFen;

	@Autowired
	private hxProductListMapper hxProductListMapper;



	public class MyTask implements Runnable {
		private Semaphore Semaphore;
		private hxProductList HX;

		public MyTask(Semaphore Semaphore, hxProductList HX) {
			this.Semaphore = Semaphore;
			this.HX = HX;
		}

		@Override
		public void run() {
			try {
				logger.info("尝试获取令牌");
				Semaphore.acquire();//占用
				logger.info("获取令牌");
				jiFen.listCard(HX);//同步海翔积分到数据库
				jiFen.findCard(HX);//叮咚积分查询,同步到库，无则创建会员用户
				jiFen.cyjftbu(HX);//差异积分同步
				jiFen.fujifengtongbu(HX); //负积分扣除
				logger.info("运行完毕,释放令牌");
				Semaphore.release();//释放信号
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			}

		}

	}
	
	@Scheduled(cron = "0 0 4 * * ? ")
	public void execute() {
		//定义线程个数
		final Semaphore s = new Semaphore(4);
		//线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<hxProductList> hxPLM = hxProductListMapper.selectProductlist();
		for (hxProductList hxProductList : hxPLM) {
			threadPool.execute(new MyTask(s,hxProductList));
		}
		threadPool.shutdown();
	}

	/**
	 * CronTrigger配置完整格式为： [秒][分][小时][日][月][周][年]
	 * (cron = "0/2 * * * * ?") //每两秒
	 *
	 * 每3小时重置mysql和redis
	 *
	 */
	@Scheduled(cron = "0 0/2 * * * ?")
	//@Async
	public void goDanCi() {
		logger.info("-------------> 单次调度线程名称：[{}]，执行频率：2分钟/次", Thread.currentThread().getName());
		List<o2o_operate_upload_price> danciList = o2o_operate_upload_priceMapper.danCiRenWuHuoQu();
		if (danciList.size() != 0) {
			for (o2o_operate_upload_price object : danciList) {
				System.err.println("单次任务：" + object.getPoiCode() + ":" + object.getPoiNmae());
				try {
					mddanciupdata(object.getPoiCode(), object.getPoiNmae());
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}

		// 清空缓存
		//flushRedis();
		// 重置mysql
		//resetDb();
	}

	/**
	 * CronTrigger配置完整格式为： [秒][分][小时][日][月][周][年]
	 * (cron = "0/2 * * * * ?") //每两秒
	 *
	 * 每3小时重置mysql和redis
	 *
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void goPiLiang() {
		logger.info("-------------> 批量调度线程名称：[{}]，执行频率：5分钟/次", Thread.currentThread().getName());
		List<o2o_operate_upload_price> piLiangList = o2o_operate_upload_priceMapper.piLiangRenWuHuoQu();
		if (piLiangList.size() != 0) {
			for (o2o_operate_upload_price object : piLiangList) {
				try {
					System.err.println("批量任务：" + object.getPoiCode() + ":" + object.getPoiNmae());
					dpqlupdata(object.getPoiCode(), object.getPoiNmae());
				} catch (Exception e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}

		// 清空缓存
		//flushRedis();
		// 重置mysql
		//resetDb();
	}

	public MtResult dpqlupdata(String poicode, String poiName) throws Exception {
		System.err.println("门店code:" + poicode + "," + poiName);
		if (poicode.equals("")) {
			return MtResult.err(400, "门店编码为空");
		} else {
			MtResult testpoicode = mtSservice.testpoi_code(poicode);
			if (testpoicode.getCode() == 404) {
				return testpoicode;
			} else if (testpoicode.getCode() == 200) {
				MtService.medicineBatchs(poicode, poiName);
				MtService.ShopRetailZheKou(poicode);
				MtService.ShopRetailBaoPing(poicode);
				MtService.RetailZheKouDiscountBatchDelete(poicode);
				MtService.RetailBaoPinDiscountBatchDelete(poicode);
				MtService.selectUpdatePrice(poicode);
				MtService.selectUpdateZheKouDiscount(poicode);
				MtService.selectUpdateBaoPinDiscount(poicode);
				//MtService.selectDanCiUpdateBaoPinDiscount(poicode);
				//MtService.selectDanCiUpdateZheKouDiscount(poicode);
				return MtResult.ok();
			}

		}
		return MtResult.ok();

	}

	public MtResult mddanciupdata(String poicode, String poiName) throws Exception {
		System.err.println("门店code:" + poicode);
		if (poicode.equals("")) {
			return MtResult.err(401, "门店编码为空");
		} else {

			MtResult testpoicode = mtSservice.testpoi_code(poicode);
			if (testpoicode.getCode() == 404) {
				return testpoicode;
			} else if (testpoicode.getCode() == 200) {
				//o2o_operate_upload_priceMapper.danCiUpdateDiscountzhuangtai(poicode,String.valueOf(1),String.valueOf(1),"0");
				//o2o_operate_upload_priceMapper.danCiUpdateDiscountzhuangtai(poicode,String.valueOf(1),String.valueOf(0),"0");
				int count = MtService.findDanCiIsUpdateCount(poicode);
				if (count <= 0) {
					logger.info("没有价格可更新");
					MtResult result0 = MtService.danCiZheKouHuoDongUpdate(poicode);
					logger.info("单次更新折扣活动" + result0.toString());
					MtResult result1 = MtService.danCiBaoPinHuoDongUpdate(poicode);
					logger.info("单次更新爆品活动" + result1.toString());
					MtService.ShopRetailZheKou(poicode);
					MtService.ShopRetailBaoPing(poicode);
					UpdateDiscountzhuangtai(poicode);
					return MtResult.ok();
				} else {
					MtResult result2 = MtService.danCiDeleteZheKou(poicode);
					logger.info("单次删除折扣活动" + result2.toString());
					MtResult result3 = MtService.danCiUpdatePrice(poicode);
					logger.info("单次更新价格" + result3.toString());
					MtResult result4 = MtService.danCiZheKouHuoDongUpdate(poicode);
					logger.info("单次更新折扣活动" + result4.toString());
					MtResult result5 = MtService.danCiBaoPinHuoDongUpdate(poicode);
					logger.info("单次更新爆品活动" + result5.toString());

					logger.info("重新下载商品和折扣");
					MtService.medicineBatchs(poicode, poiName);
					MtService.ShopRetailZheKou(poicode);
					MtService.ShopRetailBaoPing(poicode);

					UpdateDiscountzhuangtai(poicode);
				}
				return MtResult.ok();
			}
		}

		return MtResult.ok();
	}

	public void UpdateDiscountzhuangtai(String poicode) {
		o2o_operate_upload_priceMapper.danCiUpdateDiscountzhuangtai(poicode, String.valueOf(2), String.valueOf(1), "0");
		o2o_operate_upload_priceMapper.danCiUpdateDiscountzhuangtai(poicode, String.valueOf(2), String.valueOf(0), "0");
	}

	private void flushRedis() {
		try {
			System.err.println("清空缓存");
		} catch (Exception ignored) {
		}
	}

	private void resetDb() {
		logger.info("数据库重置开始");
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection conn = sqlSession.getConnection();
		ScriptRunner runner = new ScriptRunner(conn);
		Resources.setCharset(StandardCharsets.UTF_8); //设置字符集,不然中文乱码插入错误
		runner.setLogWriter(null);//设置是否输出日志
		// 绝对路径读取
		// 从class目录下直接读取
		Reader read;
		try {
			read = Resources.getResourceAsReader("reset.sql");
			runner.runScript(read);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			runner.closeConnection();
		}
		logger.info("数据库重置完毕");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
}
