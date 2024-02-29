package cn.lastwhisper.modular.mapper;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2o_operate_shop;

public interface o2o_operate_shopMapper {
    int deleteByPrimaryKey(Integer id);

    o2o_operate_shop selectshopid();

    int updateByPrimaryKeySelective(o2o_operate_shop record);

    int updateByPrimaryKey(o2o_operate_shop record);
    
    
    o2o_operate_shop selectdwsq();

	int updateDwsqZtai(@Param("ztai") int ztai,@Param("dwid") int dwid ,@Param("newdz")String newdz);

	
}