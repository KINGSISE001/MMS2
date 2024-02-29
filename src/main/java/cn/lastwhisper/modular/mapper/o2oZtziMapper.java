package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2oZtzi;

public interface o2oZtziMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(o2oZtzi record);
    
    List<o2oZtzi> selectByPrimaryKey(Integer mdid);

    List<o2oZtzi> selectByName(@Param("names") String names);
    
    List<o2oZtzi> selectAll();

    /**
           * 依据ID更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(o2oZtzi record);

    int updateByPrimaryKey(o2oZtzi record);
}