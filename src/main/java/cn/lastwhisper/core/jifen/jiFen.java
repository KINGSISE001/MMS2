package cn.lastwhisper.core.jifen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.PageUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.lastwhisper.core.haixiang.JsonUtils;

import cn.lastwhisper.core.haixiang.SfApiConfig;
import cn.lastwhisper.core.haixiang.SfApiService;
import cn.lastwhisper.core.util.Signature;
import cn.lastwhisper.modular.controller.MtProductController;
import cn.lastwhisper.modular.mapper.HxVipCardsMapper;
import cn.lastwhisper.modular.mapper.hxProductListMapper;
import cn.lastwhisper.modular.pojo.HxVipCards;
import cn.lastwhisper.modular.pojo.Log;
import cn.lastwhisper.modular.pojo.hxProductList;
import cn.lastwhisper.modular.service.LogService;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;
@Service
public class jiFen implements jiFens {
	Logger log=  LoggerFactory.getLogger(jiFen.class);
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private HxVipCardsMapper hxVipCardsMapper;
	
	@Autowired
	private SfApiService SfApiService;
	
	@Autowired 
	private hxProductListMapper hxProductListMapper;
	
	
	/**
	 * 
	 *  Description:积分充值
	 *  @author wangsheng  DateTime 2022年6月10日 上午10:49:20
	 *  @param jifens
	 *  @param user
	 *  @return
	 */
	public  JSONObject chongZhi(int jifens,String user,int  company,int user_id,String qqkey) {
		JSONObject bodysJson=null;
		try {
		TreeMap<String, Object > tMap= new TreeMap<>();
		tMap.put("company", company);//合作公司编号
		tMap.put("money",jifens);//充值积分数
		tMap.put("member", user);//要充值的用户
		tMap.put("time", System.currentTimeMillis()/1000);//当前时间戳【实时更新】
		tMap.put("user_id", user_id);//您在企叮咚的注册邀请码
		Log log = new Log();
		log.setOperatedate(new Date());
		log.setOperateor(user);
		log.setOperateresult(String.valueOf(jifens));
		log.setOperatetype("充值积分到叮咚");
		log.setIp(String.valueOf(company));
		logService.addLog(log);
		String url="http://7ddapi.7dingdong.com/apidh_store/recharge?";
		String signString=Signature.getSign(tMap,qqkey);
		//tMap.put("sign", signString);
		url=url+Signature.getKeyAndValueStr(tMap,signString);
		HttpResponse getHttpResponse =HttpRequest.get(url)
				.header("Content-Type", "application/json;charset=utf8")
				.header("Accept","application/json;charset=utf8")
				.execute();
		bodysJson= JSONObject.parseObject(Signature.unicode2String(getHttpResponse.body()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bodysJson;
		
	}

	/**
	 * 
	 *  Description:积分扣除
	 *  @author wangsheng  DateTime 2022年6月10日 上午10:49:20
	 *  @param jifens
	 *  @param user
	 *  @return
	 */
	public  JSONObject fuJiFenChongZhi(int jifens,String user,int  company,int user_id,String qqkey) {
		JSONObject bodysJson=null;
		try {
		TreeMap<String, Object > tMap= new TreeMap<String, Object>();
		tMap.put("company", company);//合作公司编号
		tMap.put("money",jifens);//充值积分数
		tMap.put("member", user);//要充值的用户
		tMap.put("time", System.currentTimeMillis()/1000);//当前时间戳【实时更新】
		tMap.put("user_id", user_id);//您在企叮咚的注册邀请码
		Log logs = new Log();
		logs.setOperatedate(new Date());
		logs.setOperateor(user);
		logs.setOperateresult(String.valueOf(jifens));
		logs.setOperatetype("叮咚积分扣除");
		logs.setIp(String.valueOf(company));
		logService.addLog(logs);
		String url="http://7ddapi.7dingdong.com/apidh_store/reduceIntegral?";
		String signString=Signature.getSign(tMap,qqkey);
		//tMap.put("sign", signString);
		url=url+Signature.getKeyAndValueStr(tMap,signString);
		System.out.println(url);
		HttpResponse getHttpResponse =HttpRequest.get(url)
				.header("Content-Type", "application/json;charset=utf8")
				.header("Accept","application/json;charset=utf8")
				.execute();
		bodysJson= JSONObject.parseObject(Signature.unicode2String(getHttpResponse.body()));
		log.info("扣除积分返回{}",bodysJson.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bodysJson;
		
	}
	/**
	 * 
	 *  Description:积分查询
	 *  @author wangsheng  DateTime 2022年6月2日 下午5:17:57
	 * @return 
	 */
	public   JSONObject chaXun(String user,String qqkey,int  company,int user_id) {
		TreeMap<String, Object > tMap= new TreeMap<String, Object>();
		tMap.put("company", company);//合作公司编号
		tMap.put("user_name",user);//查询的用户
		tMap.put("time", System.currentTimeMillis()/1000);//当前时间戳【实时更新】
		tMap.put("user_id", user_id);//您在企叮咚的注册邀请码
		String url="http://7ddapi.7dingdong.com/apidh_store/userCharge?";
		String signString=Signature.getSign(tMap,qqkey);
		//tMap.put("sign", signString);
		url=url+Signature.getKeyAndValueStr(tMap,signString);
		HttpResponse getHttpResponse =HttpRequest.get(url)
				.header("Content-Type", "application/json;charset=utf8")
				.header("Accept","application/json;charset=utf8")
				.execute();
		JSONObject bodysJson= JSONObject.parseObject(getHttpResponse.body());
		return bodysJson;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public EasyUIDataGridResult findproductlistByPage(hxProductList productList, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<hxProductList> hxProductLists=hxProductListMapper.selectProductlistByName(productList);
		PageInfo<hxProductList> pageInfo = new PageInfo<>(hxProductLists);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int) pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public GlobalResult productUpdata(hxProductList productList) {
		if (productList == null) {
			return new GlobalResult(400, "信息为空，修改失败！", 400);
		}
		Integer integer = hxProductListMapper.updateByPrimaryKeySelective(productList);
		if (integer == 0) {
			return new GlobalResult(400, "信息更新失败", null);
		} else {
			return new GlobalResult(200, "信息更新成功", null);
		}
	}

	

	/**
	 * 
	 *  Description:同步海翔积分到数据库
	 *  @author wangsheng  DateTime 2022年6月10日 下午1:46:14
	 * @return 
	 */
	
	@Override
	public boolean listCard(hxProductList hx) {
		try {
			hxVipCardsMapper.updatehuangtai();//初始化状态
		SfApiConfig sfApiConfig=new SfApiConfig(hx.getAppid(),hx.getAppkey(), hx.getDomainaddress());
		JSONObject jsonObject = JSONObject
				.parseObject(JsonUtils.objectToJson(SfApiService.getVipCards(sfApiConfig, "1", "1", hx.getAccountname()).getData()));
		int total = jsonObject.getIntValue("total");
		int totalPage = PageUtil.totalPage(total, 200) + 1;
		for (int i = 1; i < totalPage; i++) {
			JSONObject jsonObject2 = JSONObject
					.parseObject(JsonUtils.objectToJson(SfApiService.getVipCards(sfApiConfig, String.valueOf(i), "200", hx.getAccountname()).getData()));
			JSONArray array = jsonObject2.getJSONArray("rows");
			if (array.size() > 0) {
				List<HxVipCards> listCards = new ArrayList<HxVipCards>(25);
				for (int j = 0; j < array.size(); j++) {
					JSONObject object = array.getJSONObject(j);
					HxVipCards Vip = JSONObject.toJavaObject(object, HxVipCards.class);
					
						Vip.setUUID(SecureUtil.md5(Vip.getVipid()+String.valueOf(hx.getUserId())));
						Vip.setCompanyId(hx.getUserId());
						log.info("{}查询到的积分{}--{}--",Vip.getTel(),Vip.getIntergral(),Vip.getVipid());
				 listCards.add(Vip);
				}
				int s = hxVipCardsMapper.insertVipCards(listCards);
				if (s > 0) {
					listCards.clear();
				}
			}
		}
		
	} catch (Exception e) {
		log.error(e.getMessage());
		
	}
		return true;
	}

	/**
	 * 
	 *  Description:叮咚积分查询,同步到库，无则创建会员用户
	 *  @author wangsheng  DateTime 2022年6月10日 下午2:18:32
	 * @return 
	 */
	@Override
	public boolean findCard(hxProductList hxp) {
		try {
			int count = hxVipCardsMapper.selectCount(hxp.getUserId());
			int totalPage = PageUtil.totalPage(count, 200);
			for (int i = 0; i < totalPage; i++) {
				int[] pageNo = PageUtil.transToStartEnd(i, 200);
				System.err.println("共" + totalPage + "页,第" + (i + 1) + "页.开始位置:" + pageNo[0] + ",结束位置:" + pageNo[1]);
				List<HxVipCards> listsCards = hxVipCardsMapper.selectAll(hxp.getUserId(),pageNo[0], 200);
				log.info("查询条数：{}",listsCards.size());
				for (HxVipCards hxVipCards : listsCards) {
					JSONObject jiFen = chaXun(hxVipCards.getTel(), hxp.getDdkey(), hxp.getCompany(), hxp.getUserId());
					log.info("查询：{}",jiFen.toJSONString());
					int status = jiFen.getIntValue("status");
					if (status == 400 && jiFen.getString("message").contains("未查询到该用户信息")) {
						if (hxVipCards.getIntergral()>0){
							JSONObject info= chongZhi(hxVipCards.getIntergral(), hxVipCards.getTel(), hxp.getCompany(), hxp.getUserId(),hxp.getDdkey());
							log.info("充值：{}",info.toJSONString());
						}
						log.info("充值：{}","0");
					} else if (status == 200) {
						JSONObject dataJson = jiFen.getJSONObject("data");
						hxVipCardsMapper.updateByVipId(hxVipCards.getVipid(), dataJson.getIntValue("integral"), dataJson.getIntValue("user_id"));
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return true;
	}

	/**
	 * 
	 *  Description:差异积分同步（Difference integral synchronization）
	 *  @author wangsheng  DateTime 2022年6月11日 下午4:54:35
	 * @return 
	 */
	@Override
	public boolean cyjftbu(hxProductList hxp) {
		try {
			int count = hxVipCardsMapper.selectJfCount(hxp.getUserId());
			if (count >= 0) {
				int totalPage = PageUtil.totalPage(count, 200);
				for (int i = 0; i < totalPage; i++) {
					int[] pageNo = PageUtil.transToStartEnd(i, 200);
					List<HxVipCards> listsCards = hxVipCardsMapper.selectJf(hxp.getUserId(),pageNo[0], 200);
					for (HxVipCards hxVipCards : listsCards) {
						JSONObject  info =chongZhi(hxVipCards.getJf(), hxVipCards.getTel(), hxp.getCompany(), hxp.getUserId(),hxp.getDdkey());
						log.info("充值的{}---{}---{}",hxVipCards.getTel(),hxVipCards.getJf(),info.toJSONString());
					}
				}
			} else {
				log.info("无可同步的积分");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return true;
	}
	
	@Override
	public void integralCallback(String  storeid,String ptuserid,Integer integral) {
		try {
	hxProductList hx=	hxProductListMapper.selectProductlistByUserId(storeid);
	if (!(hx==null)) {
		log.info(hx.toString());
		HxVipCards hxs=hxVipCardsMapper.selectVipid(ptuserid, storeid);
		if (!(hxs==null)) {
			int vipid=	hxs.getVipid();
			log.info("查询到的id{}",vipid);
			SfApiConfig sfApiConfig=new SfApiConfig(hx.getAppid(),hx.getAppkey(), hx.getDomainaddress());
			SfApiService.jfSyn(sfApiConfig, hx.getAccountname(),integral,vipid);
		}
		
	}
		}catch (Exception e) {
			
		}
	}
	@Override
	public EasyUIDataGridResult SelectErrIntegral(String storeid, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<HxVipCards> list = hxVipCardsMapper.selectByCompanyId(Integer.valueOf(storeid));
		PageInfo<HxVipCards> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int) pageInfo.getTotal());
		result.setRows(pageInfo.getList());
	 return result;

	}
	@Override
	public boolean fujifengtongbu(hxProductList hxp) {
	
		List<HxVipCards> list = hxVipCardsMapper.selectByCompanyId(hxp.getUserId());
		for (HxVipCards hxVipCards : list) {
		JSONObject  info =fuJiFenChongZhi(hxVipCards.getJf()*-1, hxVipCards.getTel(), hxp.getCompany(), hxp.getUserId(),hxp.getDdkey());
			log.info("扣除：{}",info.toJSONString());

		}
		return true;
	}
	

}
