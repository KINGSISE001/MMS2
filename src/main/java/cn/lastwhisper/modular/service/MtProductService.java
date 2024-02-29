package cn.lastwhisper.modular.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.ShangPing;
import cn.lastwhisper.modular.pojo.dming;
import cn.lastwhisper.modular.pojo.sqmd;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;

public interface MtProductService {

	EasyUIDataGridResult insertShangPing(List<ShangPing> Lists);
	
	EasyUIDataGridResult insertShangPings(List<ShangPing> Lists);
	
	EasyUIDataGridResult insertMdxxi(List  <sqmd>Lists);
	
	GlobalResult selectShop();
	GlobalResult selectDwCshi();
	public GlobalResult  updateDwsqZtai (int ztai,int dwid,String newdz) ;
	
	
	dming selectProducts(String dm,int zhuangtai);
	int updateDmZhuangtai(String dm,int zhuangtai);
	public EasyUIDataGridResult selectdming(dming dming,Integer page, Integer rows);
	int findupdate(dming dming);
	int adddming(dming  dming);
	/**
	 * 
	 *  Description:状态修改
	 *  @author wangsheng  DateTime 2022年3月14日 下午9:19:32
	 *  @param Id
	 *  @param ztai
	 *  @param bzhu
	 *  @return
	 */
	int updateypssuoZtai(String Id,String ztai,String bzhu );
	
	
	Map<String , Object> findscdpxxs();
	
	void updateDmXinXiZhuangtai(String dmid, String zhuangtai);
	
	
	void upDmXinXis(Map<String , String > map);
}
