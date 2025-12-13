package cn.lastwhisper.modular.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.Completedorder;
import cn.lastwhisper.modular.pojo.Detail;
import cn.lastwhisper.modular.pojo.SettlementInformation;
import cn.lastwhisper.modular.pojo.ShangPing;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;

public interface OrderService {
	int insertCompletedorder (@Param("completedorder")Completedorder  completedorder);
	
	int insertDetail (List<Detail>  lists);
	
	int	updateStatus (long orderId,String Status);
	
	int insertsettlementinformation (@Param("settlementinformation") SettlementInformation  settlementinformation);
	
	Map selectorderinformation(@Param("AppPoiCode") String[] AppPoiCode ,int start, int size ,Long maxtime);

	EasyUIDataGridResult findOrderlistByPage(Completedorder completedorder,Integer page, Integer rows);
	
	EasyUIDataGridResult findDetailByOrderId(Long order);
	
	
}
