package cn.lastwhisper.modular.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.sqmd;

public interface sqmdMapper {
    int deleteByPrimaryKey(Integer id);

    sqmd selectByPrimaryKey(Integer id);
    int  insertMdxxi (@Param("lists")  List  <sqmd> lists);
    int updateByPrimaryKeySelective(sqmd record);

    int updateByPrimaryKey(sqmd record);
    Map<String, Object>findscdpxxss();
    
    void upDmXinXiZhuangtai(@Param("mdid")String dmid,@Param("zhuangtai")String zhuangtai);
    
    void upDmXinXi(Map<String , String > map);
}