package cn.lastwhisper.modular.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2o_operate_monitor_goods;

public interface o2o_operate_monitor_goodsMapper {
    int deleteByPrimaryKey(Integer id);
/**
 * 
 *  Description: 取门店
 *  @author wangsheng  DateTime 2021年12月23日 下午5:47:07
 *  @param id
 *  @return   o2o_operate_monitor_goods
 */
   List<o2o_operate_monitor_goods>selectByshopId(@Param("shopId") Integer shopId);

    int updateByPrimaryKeySelective(o2o_operate_monitor_goods record);

    int updateByPrimaryKey(o2o_operate_monitor_goods record);
    
    
    int updateZtaiById(@Param("id") String Id,@Param("ztai")String ztai,@Param("bzhu")String bzhu);
    
}