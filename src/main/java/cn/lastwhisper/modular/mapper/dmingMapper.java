package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.ShangPing;
import cn.lastwhisper.modular.pojo.dming;

public interface dmingMapper {
    int deleteByPrimaryKey(Integer id);

    dming selectByPrimaryKey(Integer id);
    dming selectByPrimaryDm(dming record);

    int updateByPrimaryKeySelective(dming record);
    List<dming> selectdming(dming dming);
    int updateByPrimaryKey(dming record);
    
    int insertdm(dming record);
    int findupdate(dming dming);
   int updateDmZhuangtai(dming record);
   
   int  insertShangPing (@Param("lists")  List<ShangPing>  lists);
   
   int  insertShangPings (@Param("lists")  List<ShangPing>  lists);
   
  
}