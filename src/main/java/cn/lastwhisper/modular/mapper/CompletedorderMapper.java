package cn.lastwhisper.modular.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import cn.lastwhisper.modular.pojo.Completedorder;
@Mapper
public interface CompletedorderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insertCompletedorder (Completedorder  completedorder);
   Long getTotalOrders (Map<String, Object> map);
    Map<String,Object>  selectByPrimaryKey(Long orderId);
    List selectByAppPoiCode (Map<String, Object> map);
    int updateByPrimaryKeySelective(Completedorder record);
   List<Completedorder> selectOrderlistByPage(Completedorder completedorder);
    int updateByPrimaryKey(Completedorder record);
    int updateStatus  (@Param(value="orderId") String  orderId,@Param(value="status")String Status);
}
