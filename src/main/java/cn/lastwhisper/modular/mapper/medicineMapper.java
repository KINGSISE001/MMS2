package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.medicine;

public interface medicineMapper {
	int InsertMedicine (@Param("lists") List<medicine> lists);
	
    int deleteByPrimaryKey(Integer id);
    int findallcount(@Param("poiCode") String poiCode);
    int deleteByPrimaryPoiCode(@Param("poiCode") String poiCode);
    medicine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(medicine record);

    int updateByPrimaryKey(medicine record);
}