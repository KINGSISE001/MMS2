package cn.lastwhisper.modular.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2oZtzi;
import cn.lastwhisper.modular.vo.GlobalResult;

public interface ztService {

	public  GlobalResult addZtxxi(o2oZtzi o2oZtzi ) ;
	
	public  GlobalResult findAll() ;
	
	public  GlobalResult selectByName(String names);
	
	public  GlobalResult updateZtaiById(o2oZtzi record);
	
}
