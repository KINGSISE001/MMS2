package cn.lastwhisper.modular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lastwhisper.modular.mapper.o2oZtziMapper;
import cn.lastwhisper.modular.pojo.o2oZtzi;
import cn.lastwhisper.modular.service.ztService;
import cn.lastwhisper.modular.vo.GlobalResult;
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class ztServiceimpl implements ztService {

	@Autowired
	private o2oZtziMapper o2oZtziMapper;
	
	
	@Override
	public GlobalResult addZtxxi(o2oZtzi o2oZtzi) {
		
		if (!o2oZtziMapper.selectByPrimaryKey(o2oZtzi.getMdid()).isEmpty()) {
			return GlobalResult.build(402, "此门店已提交", o2oZtzi);
		}
		return o2oZtziMapper.insertSelective(o2oZtzi)>0?GlobalResult.ok():GlobalResult.build(404,"新增失败");
	}


	@Override
	public GlobalResult findAll() {
		
		return GlobalResult.ok(o2oZtziMapper.selectAll());
	}


	@Override
	public GlobalResult selectByName(String names) {
		// TODO Auto-generated method stub
		return GlobalResult.ok(o2oZtziMapper.selectByName(names));
	}


	@Override
	public GlobalResult updateZtaiById(o2oZtzi record) {
		// TODO Auto-generated method stub
		return GlobalResult.ok(o2oZtziMapper.updateByPrimaryKeySelective(record));
	}

}
