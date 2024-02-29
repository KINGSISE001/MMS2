package cn.lastwhisper.modular.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lastwhisper.modular.mt.mtSservice;
import cn.lastwhisper.modular.service.MtService;
import cn.lastwhisper.modular.service.impl.MtServiceImpl;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;

@RequestMapping("mts")
@Controller
public class MtController {
	private static final Logger log = LoggerFactory.getLogger(MtController.class);
	@Autowired
	private  MtService MtService;
	
	@Autowired
	private  mtSservice mtSservice;
	
	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
	public void test() throws Exception {
		//MtService.medicineBatch("5430_2705942");
		MtService.ShopRetailZheKou("5430_2705942");
		MtService.ShopRetailBaoPing("5430_2705942");
	}
	@RequestMapping(value = "/dpqlupdata", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult dpqlupdata(@RequestParam(value = "poicode", required = true, defaultValue = "") String poicode,@RequestParam(value = "poiname", required = true, defaultValue = "")String poiname)throws Exception {
		System.err.println("门店code:"+poicode+","+poiname);
		if (poicode.equals("")) {
			return MtResult.err(400, "门店编码为空");
		}else {
			MtResult testpoicode=mtSservice.testpoi_code(poicode);
			if (testpoicode.getCode()==404) {
				return testpoicode;
			} else if(testpoicode.getCode()==200) {
				MtService.medicineBatchs(poicode,poiname);
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
	
	@RequestMapping(value = "/mdqlxzai", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult mdqlxzai(
			@RequestParam(value = "poicode", required = true, defaultValue = "") String poicode,@RequestParam(value = "poiname", required = true, defaultValue = "")String poiname)throws Exception {
		System.err.println("门店code:"+poicode+","+poiname);
		if (poicode.equals("")) {
			return MtResult.err(401, "门店编码为空");
		}else {
			return MtService.medicineBatch(poicode,poiname);
		
		}
		
										
	}
	/**
	 * 门店更新商品下载
	 */
	@RequestMapping(value = "/mdzlxzai", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult mdzlxzai(
			@RequestParam(value = "poicode", required = true, defaultValue = "") String poicode,@RequestParam(value = "poiname", required = true, defaultValue = "")String poiname)throws Exception {
		System.err.println("门店code:"+poicode+","+poiname);
		if (poicode.equals("")) {
			return MtResult.err(401, "门店编码为空");
		}else {
			return MtService.medicineBatchs(poicode,poiname);
		
		}	
	}
	
	/**
	 * 门店折扣下载
	 */
	@RequestMapping(value = "/mdzkxzai", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult mdzkxzai(
			@RequestParam(value = "poicode", required = true, defaultValue = "") String poicode,@RequestParam(value = "poiname", required = true, defaultValue = "")String poiname)throws Exception {
		System.err.println("门店code:"+poicode+","+poiname);
		if (poicode.equals("")) {
			return MtResult.err(401, "门店编码为空");
		}else {
			
			MtResult testpoicode=mtSservice.testpoi_code(poicode);
			if (testpoicode.getCode()==404) {
				return testpoicode;
			} else if(testpoicode.getCode()==200) {
				MtService.ShopRetailZheKou(poicode);
				MtService.ShopRetailBaoPing(poicode);
				return MtResult.ok();
			}

		}
		return MtResult.ok();	
	}
	
	/**
	 * 门店单次价格和活动更新
	 */
	@RequestMapping(value = "/mddanciupdata", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult mddanciupdata(
			@RequestParam(value = "poicode", required = true, defaultValue = "") String poicode,@RequestParam(value = "poiname", required = true, defaultValue = "")String poiname)throws Exception {
		System.err.println("门店code:"+poicode+","+poiname);
		if (poicode.equals("")) {
			return MtResult.err(401, "门店编码为空");
		}else {
			
			MtResult testpoicode=mtSservice.testpoi_code(poicode);
			if (testpoicode.getCode()==404) {
				return testpoicode;
			} else if(testpoicode.getCode()==200) {

				int count=	MtService.findDanCiIsUpdateCount(poicode);
				if (count<=0) {
					log.info("没有价格可更新");
					MtResult result0=MtService.danCiZheKouHuoDongUpdate(poicode);
					log.info("单次更新折扣活动"+result0.toString());
					MtResult result1=MtService.danCiBaoPinHuoDongUpdate(poicode);
					log.info("单次更新爆品活动"+result1.toString());
					return MtResult.ok();	
				}else {
					MtResult result2=MtService.danCiDeleteZheKou(poicode);
					log.info("单次删除折扣活动"+result2.toString());
					MtResult result3=MtService.danCiUpdatePrice(poicode);
					log.info("单次更新价格"+result3.toString());
					MtResult result4=MtService.danCiZheKouHuoDongUpdate(poicode);
					log.info("单次更新折扣活动"+result4.toString());
					MtResult result5=MtService.danCiBaoPinHuoDongUpdate(poicode);
					log.info("单次更新爆品活动"+result5.toString());
				}
				return MtResult.ok();	
			}
		}
		log.info("重新下载商品和折扣");
		MtService.medicineBatch(poicode,poiname);
		MtService.ShopRetailZheKou(poicode);
		MtService.ShopRetailBaoPing(poicode);
		return MtResult.ok();	
	}

	
}
