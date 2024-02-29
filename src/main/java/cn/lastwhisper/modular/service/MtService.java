package cn.lastwhisper.modular.service;

import java.util.List;

import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;

public interface MtService {
	/**
	 * 
	 *  Description:拉取商品(删除)
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:06:29
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	public MtResult medicineBatch(String AppPoiCode,String poiname) throws Exception;
	/**
	 * 
	 *  Description:拉取商品(不删除)
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:06:29
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	public MtResult medicineBatchs(String AppPoiCode ,String poiname) throws Exception;
	
	/**
	 * 
	 *  Description:拉取折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:00
	 *  @param AppPoiCode
	 * @return 
	 *  @throws Exception
	 */
	public GlobalResult ShopRetailZheKou(String AppPoiCode) throws Exception;
	/**
	 * 
	 *  Description:拉取爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:00
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	public GlobalResult ShopRetailBaoPing(String AppPoiCode)throws Exception;
	public void ActRetailDiscount() throws Exception;
	/**
	 *
	 *  Description:删除折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:57
	 *  @param poiCode
	 *  @throws Exception
	 */
	public MtResult RetailZheKouDiscountBatchDelete(String poiCode) throws Exception;
	/**
	 *
	 *  Description:删除爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:57
	 *  @param poiCode
	 *  @throws Exception
	 */
	public MtResult RetailBaoPinDiscountBatchDelete(String poiCode) throws Exception;
	/**
	 * 
	 *  Description:更新折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	public void selectUpdateZheKouDiscount(String poiCode) throws Exception;
	/**
	 * 
	 *  Description:更新门店价格
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:09:51
	 *  @param poiCode
	 *  @throws Exception
	 */
	public void selectUpdatePrice (String poiCode)throws Exception;
	
	/**
	 * 
	 *  Description:创建或更新爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	public void selectUpdateBaoPinDiscount(String poiCode) throws Exception;
	
	/**
	 * 
	 *  Description:单次删除折扣商品
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	public void selectDanCiUpdateZheKouDiscount(String poiCode) throws Exception;
	
	
	/**
	 * 
	 *  Description:单次删除爆品商品
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	public void selectDanCiUpdateBaoPinDiscount(String poiCode) throws Exception;
	/**
	 * 
	 *  Description: 临时删除药品的方法
	 *  @author wangsheng  DateTime 2021年12月20日 下午3:50:36
	 *  @return
	 */
	public List medicineBatchDeletes () ;
	/**
	 * 
	 *  Description: 更新个别价格（删除折扣）
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	public MtResult  danCiDeleteZheKou(String poiCode) throws Exception;
	
	/**
	 * 
	 *  Description: 更新个别价格
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	public MtResult  danCiUpdatePrice(String poiCode) throws Exception;
	
	/**
	 * 
	 *  Description: 单次（活动创建）
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return MtResult
	 *  @throws Exception
	 */
	public MtResult danCiZheKouHuoDongUpdate(String poiCode)throws Exception;
	public MtResult danCiBaoPinHuoDongUpdate(String poiCode) throws Exception;
	
	/**
	 * 单次价格更新计数
	 *  Description:
	 *  @author wangsheng  DateTime 2022年1月27日 下午2:16:33
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	int findDanCiIsUpdateCount(String poiCode)throws Exception;
	
}
