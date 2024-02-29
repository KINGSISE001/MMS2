package cn.lastwhisper.modular.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.lastwhisper.modular.pojo.o2o_operate_upload_price;

public interface o2o_operate_upload_priceMapper {
    int deleteByPrimaryKey(Integer id);
    List<o2o_operate_upload_price> selectUpdatePrice(@Param("poiCode") String  poiCode,@Param("start") Integer start,@Param("size")  Integer size);
    int UpdatePriceCount (@Param("poiCode") String poiCode);
    List<o2o_operate_upload_price> selectUpdateZheKouDiscount(@Param("poiCode") String  poiCode,@Param("start") Integer start,@Param("size")  Integer size);
   int  piLiangUpdateDiscountBaoPinCount(@Param("poiCode") String poiCode);
   List<o2o_operate_upload_price> selectUpdateBaoPinDiscount (@Param("poiCode") String  poiCode,@Param("start") Integer start,@Param("size")  Integer size);
   int  UpdateDiscountZheKouCount(@Param("poiCode") String poiCode);
   List<o2o_operate_upload_price> selectDanCiUpdateBaoPinDiscount (Map< String , Object > map );
   int  UpdateDanCiDiscountBaoPinCount(Map< String , Object > map);
   List<o2o_operate_upload_price> selectDanCiUpdateZheKouDiscount (Map< String , Object > map );
   int  UpdateDanCiDiscountZheKouCount(Map< String , Object > map);
    o2o_operate_upload_price selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(o2o_operate_upload_price record);
    int updateByPrimaryKey(o2o_operate_upload_price record);
    
    List<o2o_operate_upload_price> selectList ();
    
    /**
     * 
     *  Description:查询单次删除的折扣ID
     *  @author wangsheng  DateTime 2022年1月26日 下午3:53:49
     *  @param record
     *  @return  size
     */
    List<o2o_operate_upload_price> findDCZKId(Map<String, Object> maps);
    int  findDCZKIdCount(@Param("poiCode") String poiCode);
    /**
     * 
     *  Description:单次更新价格
     *  @author wangsheng  DateTime 2022年1月27日 下午12:04:34
     *  @param poiCode
     *  @param start
     *  @param size
     *  @return
     */
    List<o2o_operate_upload_price> findDanCiPriceUpdate(Map<String, Object> maps);
    int  findDanCiIsUpdateCount(@Param("poiCode") String poiCode);
    
    /**
     * 
     *  Description: 单次活动和爆品创建
     *  @author wangsheng  DateTime 2022年1月27日 下午1:36:37
     *  @param poiCode
     *  @return
     */
   int  DanCiUpdateDiscountZheKouCount(@Param("poiCode") String poiCode);
   int  DanCiUpdateDiscountBaoPinCount(@Param("poiCode") String poiCode);
   List<o2o_operate_upload_price> DanCiUpdateBaoPinDiscount(Map<String, Object> maps);
   List<o2o_operate_upload_price>DanCiUpdateZheKouDiscount(Map<String, Object> maps);
   
   /**
    * 
    *  Description:更新状态('批量') 
    *  @author wangsheng  DateTime 2022年2月24日 上午11:37:09
    *  @param map
    *  @return
    */
  int piLiangUpdateDiscountzhuangtai(
		  @Param("poiCode") String poiCode,
		  @Param("status") String status,
		  @Param("baoPin") String baoPin,
		  @Param("tjStatus") String  tjStatus
		  );
 /**
  * 
  *  Description:更新状态('重点','补品') 
  *  @author wangsheng  DateTime 2022年2月24日 上午11:37:09
  *  @param map
  *  @return
  */
  int danCiUpdateDiscountzhuangtai(
		  @Param("poiCode") String poiCode,
		  @Param("status") String status,
		  @Param("baoPin") String  baoPin,
		  @Param("tjStatus") String  tjStatus);
  
  List<o2o_operate_upload_price> danCiRenWuHuoQu();
  List<o2o_operate_upload_price> piLiangRenWuHuoQu();
}