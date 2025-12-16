package cn.lastwhisper.core.haixiang;


import cn.hutool.crypto.SecureUtil;
import cn.lastwhisper.core.util.EmailService;
import cn.lastwhisper.modular.pojo.Log;
import cn.lastwhisper.modular.service.LogService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class SfApiServiceImpl implements SfApiService{

	@Autowired
	private LogService logService;

	@Autowired
	private EmailService emailService;
	//导出会员信息
	@Override
	public SeaFlyResult getVipCards(SfApiConfig sfApiConfig,String page, String rows, String accountName) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountName", accountName);
		paramMap.put("timeStamp", String.valueOf(System.currentTimeMillis()));
		//查询条件
		paramMap.put("page", page);
		paramMap.put("rows", rows);

		SeaFlyResult result = SeaflyApi.doPost(sfApiConfig,"getvipcards.api", paramMap, SeaFlyResult.class);
		if (result.getStatus()== 300) {
			System.err.println("getvipcards.api : : "+result.getMsg());
			emailService.sendMailSimple("949092641@qq.com","导出会员信息" ,accountName+ result.getMsg());
		}
		return result;
	}


	//导入积分
	@Override
	public  SeaFlyResult jfSyn(SfApiConfig sfApiConfig,String accountName,Integer integral,Integer vipid) throws Exception{
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("accountName", accountName);
			paramMap.put("timeStamp", String.valueOf(System.currentTimeMillis()));
			Map<String, Object> data=new HashMap<String, Object>();
			data.put("billtype", 181);
			data.put("audit", true);//true：过账，false：草稿
			Map<String, Object> indexData = new HashMap<String, Object>();
			indexData.put("billid",0);//主表idInteger是（传0）
			indexData.put("billdate",datef());//单据日期datetime是
			indexData.put("billnumber","7DD_"+SecureUtil.md5(datef()));//单据编号String是
			indexData.put("billtype",181);//单据类型Integer是
			indexData.put("vipid",vipid);//vipidInteger否
			indexData.put("uid",2);//登录者id
			indexData.put("eid",2);//经手人
			indexData.put("addintegral", integral*-1);
			data.put("indexData", indexData);
			String[] detailData= new String[0];
			data.put("detailData", detailData);
			SeaFlyResult result = SeaflyApi.doPost(sfApiConfig,"inputbills.api", paramMap, data, SeaFlyResult.class);
			System.out.println(result.toString());
			Log log = new Log();
			log.setOperatedate(new Date());
			log.setOperateor(indexData.get("billnumber").toString());
			log.setOperateresult(String.valueOf(indexData.get("addintegral").toString()));
			log.setOperatetype(JSON.toJSONString(result.getMsg()));
			log.setIp(String.valueOf(vipid));
			logService.addLog(log);
			int  status= result.getStatus();
			if (status== 300) {
				emailService.sendMailSimple("949092641@qq.com","叮咚积分异常" ,accountName+"("+vipid+")"+ result.getMsg());
				emailService.mesPost("叮咚积分同步到海翔异常:"+result.getMsg()+",客户id:"+vipid);

				emailService.sendMailSimple("894488922@qq.com","叮咚积分异常" ,accountName+"("+vipid+")请查看，手动补单");
				emailService.mesPost("叮咚积分同步到海翔异常: {《请手动补单》 }"+",客户id:"+vipid);
			}else if (status == 200) {
				emailService.sendMailSimple("894488922@qq.com","叮咚积分订单" ,accountName+":"+ result.getMsg()+"叮咚积分同步到海翔订单:"+result.getMsg()+",客户id:"+vipid+",订单单号:"+indexData.get("billnumber").toString());
				emailService.mesPost("叮咚积分同步到海翔订单:"+result.getMsg()+",客户id:"+vipid+",订单单号:"+indexData.get("billnumber").toString());
			}else {
				emailService.sendMailSimple("949092641@qq.com","叮咚积分异常" ,accountName+"("+vipid+")"+ result);
				emailService.mesPost("叮咚积分同步到海翔异常:"+ result +",客户id:"+vipid);

				emailService.sendMailSimple("894488922@qq.com","叮咚积分异常" ,accountName+"("+vipid+")请查看，手动补单");
				emailService.mesPost("叮咚积分同步到海翔异常: {《请手动补单》 }"+",客户id:"+vipid);
			}
			return result;

		} catch (Exception e) {
			emailService.mesPost("叮咚积分同步到海翔异常:"+e.getMessage()+",客户id:"+vipid);
			emailService.sendMailSimple("949092641@qq.com","叮咚积分异常" ,accountName+"("+vipid+")"+ e.getMessage());


			emailService.sendMailSimple("894488922@qq.com","叮咚积分异常" ,accountName+"("+vipid+")请查看，手动补单");
			emailService.mesPost("叮咚积分同步到海翔异常: {《请手动补单》 }"+",客户id:"+vipid);
		}
		return null;
	}

	public static String  datef() {
		//获取日期
		//导 import java.util.Date; 下的包
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datef = sdf.format(date);
		return datef;
	}

}
