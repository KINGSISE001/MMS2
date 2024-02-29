package cn.lastwhisper.modular.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import  cn.lastwhisper.modular.mapper.o2o_operate_monitor_goodsMapper;
import cn.lastwhisper.modular.mapper.o2o_operate_shopMapper;
import cn.lastwhisper.modular.mapper.dmingMapper;
import cn.lastwhisper.modular.mapper.sqmdMapper;
import cn.lastwhisper.modular.pojo.ShangPing;
import cn.lastwhisper.modular.pojo.dming;
import cn.lastwhisper.modular.pojo.o2o_operate_monitor_goods;
import cn.lastwhisper.modular.pojo.o2o_operate_shop;
import cn.lastwhisper.modular.pojo.sqmd;
import cn.lastwhisper.modular.service.MtProductService;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class MtProductServiceImpl implements MtProductService {

	@Autowired
	private dmingMapper Dmingmapper;
	@Autowired
	private o2o_operate_monitor_goodsMapper o2o_operate_monitor_goodsMapper;
	@Autowired 
	private o2o_operate_shopMapper o2o_operate_shopMapper;
	@Autowired 
	private sqmdMapper  sqmdMapper  ;
	@Override
	public EasyUIDataGridResult insertShangPing(List<ShangPing> lists) {
		Dmingmapper.insertShangPing(lists);
		return null;
	}

	@Override
	public dming selectProducts(String dm,int zhuangtai) {
		dming dming =new dming();
		dming.setDm(dm);
		dming.setZhuangtai(zhuangtai);
		System.err.println(dming.toString());
		return Dmingmapper.selectByPrimaryDm(dming);
	}

	@Override
	public int updateDmZhuangtai(String dm, int zhuangtai) {
		dming dming =new dming();
		dming.setDm(dm);
		dming.setZhuangtai(zhuangtai);
		
		return Dmingmapper.updateDmZhuangtai(dming);
	}

	@Override
	public EasyUIDataGridResult selectdming(dming dming, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<dming> list = Dmingmapper.selectdming(dming);
		PageInfo<dming> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int) pageInfo.getTotal());
		result.setRows(pageInfo.getList());
	 return result;
	}

	@Override
	public int adddming(dming dming) {
	
		return Dmingmapper.insertdm(dming);
	}

	@Override
	public int findupdate(dming dming) {
		// TODO Auto-generated method stub
		return  Dmingmapper.findupdate(dming);
	}

	@Override
	public GlobalResult selectShop() {
		Map<String, Object> map=new HashMap<String, Object>();
		o2o_operate_shop o2o_operate_shop=o2o_operate_shopMapper.selectshopid();
		if (o2o_operate_shop==null) {
			return GlobalResult.mt(400,"没有任务",0,null);
		}
		int shopId=o2o_operate_shop.getShopId();
		System.err.println(shopId);
		List <o2o_operate_monitor_goods>goods =o2o_operate_monitor_goodsMapper.selectByshopId(shopId);
		List<String> goodList=new ArrayList<String>();
		System.err.println(goods.size());
		for (o2o_operate_monitor_goods o2o_operate_monitor_goods : goods) {
			goodList.add(o2o_operate_monitor_goods.getName());
			System.out.println(o2o_operate_monitor_goods.getName());
		}
		map.put("list", goods);
		map.put("shops", o2o_operate_shop);
		map.put("lists", goodList);
		return GlobalResult.mt(200,"商品列表",goods.size(),map);
	}

	@Override
	public EasyUIDataGridResult insertShangPings(List<ShangPing> Lists) {
		Dmingmapper.insertShangPings(Lists);
	
		return null;
	}

	@Override
	public EasyUIDataGridResult insertMdxxi(List <sqmd>Lists) {
		try {
			sqmdMapper.insertMdxxi(Lists);
		} catch (BindingException e) {
			System.err.println("数据格式有误！");
			}catch (Exception e) {
		
		}
		
		return null;
	}

	@Override
	public GlobalResult selectDwCshi() {
		// TODO Auto-generated method stub
		return GlobalResult.ok(o2o_operate_shopMapper.selectdwsq());
	}

	


	@Override
	public GlobalResult updateDwsqZtai(int ztai, int dwid,String newdz) {
		int o=o2o_operate_shopMapper.updateDwsqZtai(ztai, dwid ,newdz);
		return null;
	}

	@Override
	public int updateypssuoZtai(String Id, String ztai, String bzhu) {
		return o2o_operate_monitor_goodsMapper.updateZtaiById(Id, ztai, bzhu);
	}

	@Override
	public Map<String, Object> findscdpxxs() {
		return sqmdMapper.findscdpxxss();
	}

	@Override
	public void updateDmXinXiZhuangtai(String dmid, String zhuangtai) {
		sqmdMapper.upDmXinXiZhuangtai(dmid,zhuangtai);
		
	}

	@Override
	public void upDmXinXis(Map<String, String> map) {
		// TODO Auto-generated method stub
		sqmdMapper.upDmXinXi(map);
		
	}
}
