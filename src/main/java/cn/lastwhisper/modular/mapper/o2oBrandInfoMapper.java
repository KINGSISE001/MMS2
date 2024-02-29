package cn.lastwhisper.modular.mapper;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2oBrandInfo;

public interface o2oBrandInfoMapper {
    int deleteByPrimaryKey(String appname);

    int insert(o2oBrandInfo record);

    int insertSelective(o2oBrandInfo record);

    o2oBrandInfo selectByPrimaryKey(@Param("poicode") String poicode);

    int updateByPrimaryKeySelective(o2oBrandInfo record);

    int updateByPrimaryKey(o2oBrandInfo record);
}