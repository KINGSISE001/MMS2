package cn.lastwhisper.modular.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cn.lastwhisper.modular.mapper.CompletedorderMapper;
import cn.lastwhisper.modular.mapper.DetailMapper;
import cn.lastwhisper.modular.mapper.SettlementInformationMapper;
import cn.lastwhisper.modular.pojo.Completedorder;
import cn.lastwhisper.modular.pojo.Detail;
import cn.lastwhisper.modular.pojo.SettlementInformation;
import cn.lastwhisper.modular.pojo.ShangPing;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.service.OrderService;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class OrderServicelmpl implements OrderService{

	
	@Autowired
	private CompletedorderMapper completedorderMapper;
	@Autowired
	private DetailMapper detailMapper;
	@Autowired
	private SettlementInformationMapper settlementinformationMapper;
	
	@Override
	public int insertCompletedorder(Completedorder completedorder) {
		// TODO Auto-generated method stub
		return completedorderMapper.insertCompletedorder(completedorder);
	}


	@Override
	public int insertDetail(List<Detail> lists) {
		// TODO Auto-generated method stub
		return detailMapper.insertDetail(lists);
	}


	@Override
	public int insertsettlementinformation(SettlementInformation settlementinformation) {
	 
		return settlementinformationMapper.insertSettlementInformation(settlementinformation);
	}


	
	@Override
	public Map selectorderinformation(String[] AppPoiCode,int start, int size ,Long maxtime) {
	List orderList = new ArrayList() ; 
	Map<String, Object> map = new HashMap<String, Object>();
	
	
	Map<String, Object> orderMap = new HashMap<String, Object>();
	orderMap.put("start", start );
	orderMap.put("size",size);
	orderMap.put("appPoiCode", AppPoiCode);
	orderMap.put("maxtime", maxtime);
	Long TotalOrders = completedorderMapper.getTotalOrders (orderMap);
	List<Completedorder> list=completedorderMapper.selectByAppPoiCode(orderMap);
	 for (int i = 0; i <list.size(); i++) {
	    System.err.println(list.get(i).getOrderId());
		Map<String, Object> maps = completedorderMapper.selectByPrimaryKey(list.get(i).getOrderId());
		maps.put("detils", detailMapper.selectByPrimaryKey(list.get(i).getOrderId()));
		orderList.add(maps);
	}
	 map.put("Total",TotalOrders);	
	 map.put("orderList", orderList);	
	 map.put("start", start );
	 map.put("size", size);
		return map;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public EasyUIDataGridResult findOrderlistByPage(Completedorder completedorder, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<Completedorder> list = completedorderMapper.selectOrderlistByPage(completedorder);
		PageInfo<Completedorder> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal((int) pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}


	@Override
	public EasyUIDataGridResult findDetailByOrderId(Long order) {
		List<Detail> listdeDetails =detailMapper.selectByPrimaryKey(order);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(listdeDetails.size());
		result.setRows(listdeDetails);
		return result;
	}


	@Override
	public int updateStatus(long orderId, String Status) {
		if (Status!=null) {
			String s = String.valueOf(orderId);
		int e	=completedorderMapper.updateStatus(s, Status);
			
				return e;
			
		}
		return 0;
		
	}


	}
