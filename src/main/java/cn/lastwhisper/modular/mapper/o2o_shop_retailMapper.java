package cn.lastwhisper.modular.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2o_shop_retail;

public interface o2o_shop_retailMapper {
    int deleteByPrimaryKey(Long itemId);
    int  deleteBaoPinByPrimaryPoiCode(@Param("poiCode") String poiCode);
  int  deleteZheKouByPrimaryPoiCode(@Param("poiCode") String poiCode);
    int deleteByPrimaryPoiCode(@Param("poiCode") String poiCode);
    
    int InsertShopRetail (@Param("lists") List<o2o_shop_retail> lists);
    o2o_shop_retail selectByPrimaryKey(Long itemId);
    int findZheKouRetailAllcount(@Param("poiCode") String poiCode);
    List  findZheKouRetailAll(Map<String ,Object> map);
    
    int findBaoPinRetailAllcount(@Param("poiCode") String poiCode);
    List  findBaoPinRetailAll(Map<String ,Object> map);
    

    
    int updateByPrimaryKeySelective(o2o_shop_retail record);

    int updateByPrimaryKey(o2o_shop_retail record);
}