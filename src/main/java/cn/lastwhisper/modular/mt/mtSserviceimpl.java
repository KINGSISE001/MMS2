package cn.lastwhisper.modular.mt;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sankuai.meituan.shangou.open.sdk.exception.SgOpenException;
import com.sankuai.meituan.shangou.open.sdk.request.ActRetailDiscountBatchDeleteRequest;
import com.sankuai.meituan.shangou.open.sdk.request.ActRetailDiscountBatchSaveRequest;
import com.sankuai.meituan.shangou.open.sdk.request.ActRetailDiscountListRequest;
import com.sankuai.meituan.shangou.open.sdk.request.MedicineDeleteRequest;
import com.sankuai.meituan.shangou.open.sdk.request.MedicineListRequest;
import com.sankuai.meituan.shangou.open.sdk.request.MedicinePriceRequest;
import com.sankuai.meituan.shangou.open.sdk.request.PoiUpdatePromoteInfoRequest;
import com.sankuai.meituan.shangou.open.sdk.response.SgOpenResponse;

import cn.lastwhisper.modular.pojo.medicine;
import cn.lastwhisper.modular.pojo.o2o_shop_retail;

import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtConfig;
import cn.lastwhisper.modular.vo.MtResult;



/** 
* @ClassName: mtSserviceimpl 
* @Description: TODO(美团操作方法) 
* @author ws
* @date 2021年12月18日 下午2:56:33 
*  
*/
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class mtSserviceimpl implements mtSservice{
	
	private static final Logger logger = LoggerFactory.getLogger(mtSserviceimpl.class);
	
	@Autowired
	MtConfig mtConfig;
	
	/**
	 * 
	 *  Description:测试poi_code
	 *  @author wangsheng  DateTime 2022年1月10日 下午6:22:05
	 *  @param AppPoiCode
	 * @return 
	 */
	@Override
	public  MtResult testpoi_code (String AppPoiCode){

		PoiUpdatePromoteInfoRequest request = new PoiUpdatePromoteInfoRequest(mtConfig.selectSystemParam(AppPoiCode));
		request.setApp_poi_code(AppPoiCode);
		
		SgOpenResponse sgOpenResponse;
		try {
		sgOpenResponse = request.doRequest();
		} catch (SgOpenException e) {
		e.printStackTrace();
		return MtResult.err(404,"未获取到数据");
		} catch (Exception e) {
		e.printStackTrace();
		return MtResult.err(404,"未获取到数据");
		}
		//发起请求时的sig，用来联系美团员工排查问题时使用
		String requestSig = sgOpenResponse.getRequestSig();
	
		//请求返回的结果，按照官网的接口文档自行解析即可
		String requestResult = sgOpenResponse.getRequestResult();

		
		JSONObject jsonObject=JSON.parseObject(requestResult);
		String date=jsonObject.getString("data");
		if (date.equals("ng")) {
			return MtResult.err(404,"门店编码错误");
		} if (date.equals("ok")) {
			return MtResult.err(200,"取到数据");
		}
		return MtResult.ok();
		}
	
	/**
	 * 
	* 
	* @Description: TODO(查询门店药品列表) 
	* @author wangsheng
	* @date 2021年12月16日 下午9:30:30 
	* @param 
	* @param maps
	* @return Map
	 */
@Override
		public  GlobalResult medicineBatchUpdate(String AppPoiCode,String poiname,Integer Limit,Integer Offset) {
			
			//组建请求参数,如有其它参数请补充完整
			MedicineListRequest medicineListRequest = new MedicineListRequest(mtConfig.selectSystemParam(AppPoiCode));
			medicineListRequest.setApp_poi_code(AppPoiCode);
			medicineListRequest.setLimit(Limit);
			medicineListRequest.setOffset(Offset);
			//发起请求
			SgOpenResponse sgOpenResponse;
			try {
			sgOpenResponse = medicineListRequest.doRequest();
			} catch (SgOpenException e) {
			e.printStackTrace();
			return   GlobalResult.build(400,"为获取到数据");
			} catch (IOException e) {
			e.printStackTrace();
			return   GlobalResult.build(400,"为获取到数据");
			}
			//发起请求时的sig，用来联系美团员工排查问题时使用
			String requestSig = sgOpenResponse.getRequestSig();
			//请求返回的结果，按照官网的接口文档自行解析即可
			String requestResult = sgOpenResponse.getRequestResult();
			JSONObject jsonObject=JSON.parseObject(requestResult);
			String date=jsonObject.getString("data");
			if (date.equals("ng")) {
				return GlobalResult.mt(404,"未获取到数据",0,null);
			}
			JSONArray datArray=jsonObject.getJSONArray("data");
			
			JSONObject extra_info =jsonObject.getJSONObject("extra_info");
			int total_count=extra_info.getIntValue("total_count");
			
			List<medicine> list=JSONObject.parseArray(datArray.toJSONString(), medicine.class);
			if (datArray.size()<=0&&total_count==0) {
				
				return GlobalResult.mt(404,"未获取到数据",total_count,list);
			}
			for (medicine medicine : list) {
				medicine.setPoiCode(AppPoiCode);
				medicine.setPoiName(poiname);
			if (medicine.getStock().equals("")) {
				medicine.setStock("0");
			}
			}

			return GlobalResult.mt(200,"成功",total_count,list);
			}

	/**
	 * 
	* 
	* @Description: TODO(【折扣】批量删除药品折扣商品和和爆品商品活动) 
	* @author wangsheng
	* @date 2021年12月16日 下午9:30:09 
	* @param 
	* @throws Exception
	 */
@Override
		public  MtResult ActRetailDiscountBatchDeleteRequest(String poiCode,String ItemIds ,Integer type) throws Exception {
			System.err.println(ItemIds);
			ActRetailDiscountBatchDeleteRequest actRetailDiscountBatchDeleteRequest = new ActRetailDiscountBatchDeleteRequest(mtConfig.selectSystemParam(poiCode));
			actRetailDiscountBatchDeleteRequest.setApp_poi_code(poiCode);
			actRetailDiscountBatchDeleteRequest.setItem_ids(ItemIds);
			actRetailDiscountBatchDeleteRequest.setAct_type(type);
			SgOpenResponse sgOpenResponse;
			try {
			sgOpenResponse = actRetailDiscountBatchDeleteRequest.doRequest();
			} catch (SgOpenException e) {
			e.printStackTrace();
			return MtResult.err(400,e.getMessage());
			} catch (Exception e) {
			e.printStackTrace();
			return MtResult.err(400,e.getMessage());
			}

			//请求返回的结果，按照官网的接口文档自行解析即可
			String requestResult = sgOpenResponse.getRequestResult();
			logger.info("折扣删除返回："+requestResult);
		
			JSONObject jsonObject=JSON.parseObject(requestResult);
			String date=jsonObject.getString("data");
			if (date.equals("ng")) {
				return MtResult.err(400,requestResult);
			} if (date.equals("ok")) {
				return MtResult.ok();
			}
			return MtResult.ok();
			
			}
	/**
	 * 
	* 
	* @Description: TODO(【折扣】查询门店药品折扣商品和爆品商品活动) 
	* @author wangsheng
	* @date 2021年12月16日 下午9:53:28 
	* @param 
	 * @return 
	* @throws Exception
	 */
@Override
		public  Map<String, Object> ActRetailDiscountListRequest(Map<String , Object> maps) throws Exception {
			Map<String , Object> map=new HashMap<String , Object>();
			ActRetailDiscountListRequest actRetailDiscountListRequest = new ActRetailDiscountListRequest(mtConfig.selectSystemParam((String) maps.get("AppPoiCode")));
			
			actRetailDiscountListRequest.setApp_poi_code((String) maps.get("AppPoiCode"));
			actRetailDiscountListRequest.setLimit((Integer) maps.get("Limit"));
			actRetailDiscountListRequest.setOffset((Integer) maps.get("Offset"));
			actRetailDiscountListRequest.setAct_type((Integer) maps.get("Act_type"));
			SgOpenResponse sgOpenResponse;
			try {
			sgOpenResponse = actRetailDiscountListRequest.doRequest();
			} catch (SgOpenException e) {
			e.printStackTrace();
			return map;
			} catch (Exception e) {
			e.printStackTrace();
			return map ;
			}
			//发起请求时的sig，用来联系美团员工排查问题时使用
			String requestSig = sgOpenResponse.getRequestSig();
			//请求返回的结果，按照官网的接口文档自行解析即可
			String requestResult = sgOpenResponse.getRequestResult();
			logger.info("查询折扣，返回："+requestResult);
			JSONObject jsonObject=JSON.parseObject(requestResult);
			JSONArray datArray=jsonObject.getJSONArray("data");
			List<o2o_shop_retail> list=JSONObject.parseArray(datArray.toJSONString(), o2o_shop_retail.class);
			
			for (o2o_shop_retail medicine : list) {
				medicine.setPoiCode((String) maps.get("AppPoiCode"));
				medicine.setActType( (Integer) maps.get("Act_type"));
			}
			map.put("list", list);
			return map;
			}
	/**
	 * 
	 *  Description:更新门店药品价格
	 *  @author wangsheng  DateTime 2021年12月18日 下午3:08:39
	 *  @param map
	 */
		
		@Override
		public MtResult medicinePrice(String AppPoiCode,String data){
		
			MedicinePriceRequest request = new MedicinePriceRequest(mtConfig.selectSystemParam(AppPoiCode));
			request.setApp_poi_code(AppPoiCode);
			request.setMedicine_data(data);
			SgOpenResponse sgOpenResponse;
			try {
			sgOpenResponse = request.doRequest();
			} catch (SgOpenException e) {
			e.printStackTrace();
			return MtResult.err(400,e.getMessage());
			} catch (Exception e) {
			e.printStackTrace();
			return MtResult.err(400,e.getMessage());
			}
			//发起请求时的sig，用来联系美团员工排查问题时使用
			//String requestSig = sgOpenResponse.getRequestSig();
			//请求返回的结果，按照官网的接口文档自行解析即可
			String requestResult = sgOpenResponse.getRequestResult();
			logger.info("更新门店药品价格，返回："+requestResult);
			JSONObject jsonObject=JSON.parseObject(requestResult);
			String date=jsonObject.getString("data");
			if (date.equals("ng")) {
				return MtResult.err(400,requestResult);
			} if (date.equals("ok")) {
				return MtResult.ok();
			}
			return MtResult.ok();
			}
		/**
		 * 
		 *  Description: 批量上传活动
		 *  @author wangsheng  DateTime 2022年1月27日 下午11:54:30
		 *  @param poiCode
		 *  @param type
		 *  @param data
		 *  @return
		 *  @throws Exception
		 */
		@Override
		public MtResult ActRetailDiscountBatchSave(String poiCode,Integer type,String data) throws Exception {
			
			ActRetailDiscountBatchSaveRequest actRetailDiscountBatchSaveRequest = new ActRetailDiscountBatchSaveRequest(mtConfig.selectSystemParam(poiCode));
			actRetailDiscountBatchSaveRequest.setApp_poi_code(poiCode);
			actRetailDiscountBatchSaveRequest.setAct_data(data);
			actRetailDiscountBatchSaveRequest.setAct_type(type);
			SgOpenResponse sgOpenResponse;
			try {
			sgOpenResponse = actRetailDiscountBatchSaveRequest.doRequest();
			} catch (SgOpenException e) {
			e.printStackTrace();
			return MtResult.err(400,e.getMessage());
			} catch (Exception e) {
			e.printStackTrace();
			return MtResult.err(400,e.getMessage());
			}
			//发起请求时的sig，用来联系美团员工排查问题时使用
			String requestSig = sgOpenResponse.getRequestSig();
			//请求返回的结果，按照官网的接口文档自行解析即可
			String requestResult = sgOpenResponse.getRequestResult();
			logger.info("批量上传活动，返回："+requestResult);
			JSONObject jsonObject=JSON.parseObject(requestResult);
			String date=jsonObject.getString("data");
			if (date.equals("ng")) {
				return MtResult.err(400,requestResult);
			} if (date.equals("ok")) {
				return MtResult.err(200,requestResult);
			}
			return MtResult.ok();
			}
		
		/**
		 * 
		 *  Description:根据商品编码删除药品
		 *  @author wangsheng  DateTime 2021年12月20日 下午3:43:01
		 */
		@Override
		public void medicineBatchDelete(String AppPoiCode,String MedicineCode) {
		//组建请求参数,如有其它参数请补充完整
		MedicineDeleteRequest medicineDeleteRequest = new MedicineDeleteRequest(mtConfig.selectSystemParam(AppPoiCode));
		medicineDeleteRequest.setApp_poi_code(AppPoiCode);
		medicineDeleteRequest.setApp_medicine_code(MedicineCode);
		//发起请求
		SgOpenResponse sgOpenResponse;
		try {
		sgOpenResponse = medicineDeleteRequest.doRequest();
		} catch (SgOpenException e) {
		e.printStackTrace();
		return;
		} catch (IOException e) {
		e.printStackTrace();
		return;
		}
		//发起请求时的sig，用来联系美团员工排查问题时使用
		String requestSig = sgOpenResponse.getRequestSig();
		//请求返回的结果，按照官网的接口文档自行解析即可
		String requestResult = sgOpenResponse.getRequestResult();
		logger.info("根据商品编码删除药品"+requestResult);
		}
}
