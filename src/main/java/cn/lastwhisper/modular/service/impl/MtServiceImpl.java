package cn.lastwhisper.modular.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import cn.lastwhisper.modular.mapper.medicineMapper;
import cn.lastwhisper.modular.mapper.o2o_operate_upload_priceMapper;
import cn.lastwhisper.modular.mapper.o2o_shop_retailMapper;
import cn.lastwhisper.modular.mt.mtSservice;
import cn.lastwhisper.modular.pojo.medicine;
import cn.lastwhisper.modular.pojo.o2o_operate_upload_price;
import cn.lastwhisper.modular.pojo.o2o_shop_retail;
import cn.lastwhisper.modular.service.MtService;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
@Service
public class MtServiceImpl implements MtService {
	private static final Logger logger = LoggerFactory.getLogger(MtServiceImpl.class);

	@Autowired
	private medicineMapper medicineMapper;
	@Autowired
	private  o2o_shop_retailMapper o2o_shop_retailMapper;
	@Autowired
	private o2o_operate_upload_priceMapper o2o_operate_upload_priceMapper;
	@Autowired
	private mtSservice mtSservice;
	/**
	 * 
	 *  Description:拉取商品
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:06:29
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	@Override
	public MtResult medicineBatch(String AppPoiCode,String poiname) throws Exception {
		MtResult testpoicode=mtSservice.testpoi_code(AppPoiCode);
		if (testpoicode.getCode()==404) {
			logger.info("门店码：>"+AppPoiCode+":门店编码错误"); 
			return testpoicode;
		}
		int deletecount=medicineMapper.deleteByPrimaryPoiCode(AppPoiCode);
		logger.info("删除计数："+deletecount);
		int Offset=0;
		int Limit=200;
		GlobalResult mts=mtSservice.medicineBatchUpdate(AppPoiCode,poiname,Limit,Offset);
		int total_count =mts.getCount();
		
		int pagetotal=total_count/Limit+1;
	for (int i = 0; i <pagetotal; i++) {
		GlobalResult data=mtSservice.medicineBatchUpdate(AppPoiCode ,poiname,Limit,Offset);
		if (data.getStatus()==404) {
			logger.info("门店码：err>"+AppPoiCode+":"+data.getMsg());
			continue;
		}
		
		List<medicine> list=(List<medicine>) data.getData();
	if (list.size()==0) {
			return  MtResult.ok();
		}
		medicineMapper.InsertMedicine(list);
	
		logger.info("门店码：>"+AppPoiCode+":"+list.size());
		Offset=Offset+200;
	}
	return MtResult.ok();

	}
	/**
	 * 
	 *  Description:拉取商品（不删除）
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:06:29
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	@Override
	public MtResult medicineBatchs(String AppPoiCode ,String poiname) throws Exception {
		MtResult testpoicode=mtSservice.testpoi_code(AppPoiCode);
		if (testpoicode.getCode()==404) {
			logger.info("门店码：>"+AppPoiCode+":门店编码错误"); 
			return testpoicode;
		}
		int Offset=0;
		int Limit=200;
		GlobalResult mts=mtSservice.medicineBatchUpdate(AppPoiCode,poiname,Limit,Offset);
		int total_count =mts.getCount();
		int pagetotal=total_count/Limit+1;
	for (int i = 0; i <pagetotal; i++) {
		GlobalResult data=mtSservice.medicineBatchUpdate(AppPoiCode,poiname,Limit,Offset);
		if (data.getStatus()==404) {
			logger.info("门店码：err>"+AppPoiCode+":"+data.getMsg());
			continue;
		}
		List<medicine> list=(List<medicine>) data.getData();
		for (medicine medicine : list) {
			medicine.setUpdateTime();
		}
		if (list.size()==0) {
			return  MtResult.ok();
		}
		medicineMapper.InsertMedicine(list);
		logger.info("门店码：>"+AppPoiCode+":"+list.size());
		Offset=Offset+200;
	}
	return MtResult.ok();

	}
	/**
	 * 
	 *  Description:拉取折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:00
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	@Override
	public GlobalResult ShopRetailZheKou(String AppPoiCode) throws Exception {
	
	int deletecount=o2o_shop_retailMapper.deleteZheKouByPrimaryPoiCode(AppPoiCode);
	logger.info("删除计数："+deletecount);
	int count=	medicineMapper.findallcount(AppPoiCode);	
	logger.info("门店商品计数："+count);
	Map<String, Object> mapsMap1=new HashMap<String,Object>();
	mapsMap1.put("AppPoiCode",AppPoiCode);
	mapsMap1.put("Limit", 200);
	mapsMap1.put("Act_type", 1001);
	int pagetotal=count/200+1;
	int Offset=0;
	for (int i = 0; i < pagetotal; i++) {
		
		mapsMap1.put("Offset", Offset);
		Map<String, Object> map2=mtSservice.ActRetailDiscountListRequest(mapsMap1);
		List<o2o_shop_retail> list1=(List<o2o_shop_retail>) map2.get("list")	;
		if (list1.size()>0) {
			o2o_shop_retailMapper.InsertShopRetail(list1);
		} else {
			logger.info("折扣活动完成");
			break;
		}
		Offset=Offset+200;
	}
	return GlobalResult.ok();
	}
	/**
	 * 
	 *  Description:拉取爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:32
	 *  @param AppPoiCode
	 *  @throws Exception
	 */
	@Override
	public GlobalResult ShopRetailBaoPing(String AppPoiCode) throws Exception {
		
		int deletecount=o2o_shop_retailMapper.deleteBaoPinByPrimaryPoiCode(AppPoiCode);
		logger.info("删除计数："+deletecount);
		int count=	medicineMapper.findallcount(AppPoiCode);
		logger.info("门店商品计数："+count);
		Map<String, Object> mapsMap1=new HashMap<String,Object>();
		mapsMap1.put("AppPoiCode", AppPoiCode);
		mapsMap1.put("Limit", 200);
		mapsMap1.put("Act_type", 56);
		int pagetotal=count/200+1;
		int Offset=0;
		for (int i = 0; i < pagetotal; i++) {
			
			mapsMap1.put("Offset", Offset);
			Map<String, Object> map2=mtSservice.ActRetailDiscountListRequest(mapsMap1);
			List<o2o_shop_retail> list1=(List<o2o_shop_retail>) map2.get("list")	;
			if (list1.size()>0) {
				o2o_shop_retailMapper.InsertShopRetail(list1);
			} else {
				logger.info("爆品活动完成");
				break;
			}
			Offset=Offset+200;
		}
		return GlobalResult.ok();
		}
	@Override
	public void ActRetailDiscount() throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 *
	 *  Description:删除美团店铺折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:57
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public MtResult RetailZheKouDiscountBatchDelete(String poiCode) throws Exception {
		int count=o2o_shop_retailMapper.findZheKouRetailAllcount(poiCode);
		System.err.println("折扣计数"+count);
		if (count<=0) {
			logger.info("未查询到折扣");
		return	MtResult.err(400,"未查询到折扣活动");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int start =0;
		int size =100;
		int page=count/size+1;
		for (int i = 0; i <page; i++) {
			map.put("start", start );
			map.put("size",size);
			map.put("poiCode", poiCode);	
		List<o2o_shop_retail> list=	o2o_shop_retailMapper.findZheKouRetailAll(map);
		if (list.size()==0) {
			return MtResult.ok();
		}
		String item_ids="";
		for (o2o_shop_retail retail : list) {
			item_ids=item_ids+retail.getItemId()+",";
		}
		if (item_ids.endsWith(",")){
			item_ids = item_ids.substring(0,item_ids.length()-1) ;
		 }
		logger.info("要删除的折扣id"+item_ids);
		MtResult result =mtSservice.ActRetailDiscountBatchDeleteRequest(poiCode,item_ids,1001);
		logger.info(result.toString());
		start=start+100;
		}
		
		o2o_shop_retailMapper.deleteZheKouByPrimaryPoiCode(poiCode);
		return MtResult.ok();
	}
	
	/**
	 *
	 *  Description:删除美团店铺爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:08:57
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public MtResult RetailBaoPinDiscountBatchDelete(String poiCode) throws Exception {
		int count=o2o_shop_retailMapper.findBaoPinRetailAllcount(poiCode);
		System.err.println("爆品计数"+count);
		if (count<=0) {
		logger.info("未查询到爆品活动");
		return	MtResult.err(400,"未查询到爆品活动");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int start =0;
		int size =100;
		int page=count/size+1;
		for (int i = 0; i <page; i++) {
			map.put("start", start );
			map.put("size",size);
			map.put("poiCode", poiCode);	
		List<o2o_shop_retail> list=	o2o_shop_retailMapper.findBaoPinRetailAll(map);
		if (list.size()==0) {
			return MtResult.ok();
		}
		String item_ids="";
		for (o2o_shop_retail retail : list) {
			item_ids=item_ids+retail.getItemId()+",";
		}
		if (item_ids.endsWith(",")){
			item_ids = item_ids.substring(0,item_ids.length()-1) ;
		 }
		logger.info("要删除的爆品id"+item_ids);
	MtResult result=	mtSservice.ActRetailDiscountBatchDeleteRequest(poiCode,item_ids,56);
		logger.info(result.toString());
		start=start+100;
		}
		o2o_shop_retailMapper.deleteBaoPinByPrimaryPoiCode(poiCode);
		return MtResult.ok();
	}
	
	/**
	 * 
	 *  Description:更新门店价格
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:09:51
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public void selectUpdatePrice(String poiCode) throws Exception {
		int start =0;
		int size =100;
		int count =o2o_operate_upload_priceMapper.UpdatePriceCount(poiCode);
		int page =count/size+1;
		System.err.println("价格上传："+count +":"+page );
		for (int i = 0; i < page; i++) {
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.selectUpdatePrice(poiCode, start,size);
			if (list.size()==0) {
				return ;
			}
			String s = JSON.toJSONString(list);
			mtSservice.medicinePrice(poiCode,s);
			logger.info("上传价格"+s);
			start=start+size;
		}
		

	}
	/**
	 * 
	 *  Description:上传或更新折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public void selectUpdateZheKouDiscount(String poiCode) throws Exception {
		//o2o_operate_upload_priceMapper.piLiangUpdateDiscountzhuangtai(poiCode,String.valueOf(1),String.valueOf(0),"0");
		int start =0;
		int size =50;
		int count =o2o_operate_upload_priceMapper.UpdateDiscountZheKouCount(poiCode);
		int page =count/size+1;
		System.err.println("上传或更新折扣活动："+count +":"+page );
		for (int i = 0; i < page; i++) {
			System.err.println("折扣活动起始页:"+start);
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.selectUpdateZheKouDiscount(poiCode, start,size);
			String s = JSON.toJSONString(list);
			MtResult result=mtSservice.ActRetailDiscountBatchSave(poiCode,1001,s);
			logger.info("上传或更新折扣活动"+i+result.toString());
			start=start+size;
		}
		o2o_operate_upload_priceMapper.piLiangUpdateDiscountzhuangtai(poiCode,String.valueOf(2),String.valueOf(0),"0");
		ShopRetailZheKou(poiCode);
	}
	/**
	 * 
	 *  Description:上传或更新爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public void selectUpdateBaoPinDiscount(String poiCode) throws Exception {
		//o2o_operate_upload_priceMapper.piLiangUpdateDiscountzhuangtai(poiCode,String.valueOf(1),String.valueOf(1),"0");
		int start =0;
		int size =50;
		int count =o2o_operate_upload_priceMapper.piLiangUpdateDiscountBaoPinCount(poiCode);
		int page =count/size+1;
		System.err.println("上传或更新爆品活动："+count +":"+page );
		for (int i = 0; i < page; i++) {
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.selectUpdateBaoPinDiscount(poiCode,start,size);
			String s = JSON.toJSONString(list);
			MtResult result=mtSservice.ActRetailDiscountBatchSave(poiCode,56,s);
			logger.info("上传或更新爆品活动"+i+result.toString());
			start=start+size;
		}
		o2o_operate_upload_priceMapper.piLiangUpdateDiscountzhuangtai(poiCode,String.valueOf(2),String.valueOf(1),"0");
		ShopRetailBaoPing(poiCode);
	}
	
	/**
	 * 
	 *  Description:单次删除折扣活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public void selectDanCiUpdateZheKouDiscount(String poiCode) throws Exception {
		String laiYuan="单次";
		int start =0;
		int size =100;
		Map<String , Object > map= new HashMap<>();
		map.put("poiCode", poiCode);
		map.put("laiYuan", laiYuan);
		map.put("start", start);
		map.put("size", size);
		int count =o2o_operate_upload_priceMapper.UpdateDanCiDiscountZheKouCount(map);
		int page =count/size+1;
		Map<String,Object> maps= new HashMap<String, Object>();
		maps.put("act_type", 1001);
		maps.put("AppPoiCode", poiCode);
		for (int i = 0; i < page; i++) {
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.selectDanCiUpdateZheKouDiscount(map);
			String item_ids="";
			for (o2o_operate_upload_price retail : list) {
				item_ids=item_ids+retail.getItem_ids()+",";
			}
			if (item_ids.endsWith(",")){
				item_ids = item_ids.substring(0,item_ids.length()-1) ;
			 }
			//mtSservice.ActRetailDiscountBatchSave(maps);
			logger.info(item_ids);
			mtSservice.ActRetailDiscountBatchDeleteRequest(poiCode,item_ids,1001);
			start=start+size;
		}
		
	}
	/**
	 * 
	 *  Description:单次删除爆品活动
	 *  @author wangsheng  DateTime 2021年12月18日 下午6:10:20
	 *  @param poiCode
	 *  @throws Exception
	 */
	@Override
	public void selectDanCiUpdateBaoPinDiscount(String poiCode) throws Exception {
		String laiYuan="单次";
		int start =0;
		int size =10;
		Map<String , Object > map= new HashMap<>();
		map.put("poiCode", poiCode);
		map.put("laiYuan", laiYuan);
		map.put("start", start);
		map.put("size", size);
		int count =o2o_operate_upload_priceMapper.UpdateDanCiDiscountBaoPinCount(map);
		int page =count/size+1;
		Map<String,Object> maps= new HashMap<String, Object>();
		maps.put("act_type", 56);
		maps.put("AppPoiCode", poiCode);
		for (int i = 0; i < page; i++) {
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.selectDanCiUpdateBaoPinDiscount(map);
			String item_ids="";
			for (o2o_operate_upload_price retail : list) {
				item_ids=item_ids+retail.getItem_ids()+",";
			}
			if (item_ids.endsWith(",")){
				item_ids = item_ids.substring(0,item_ids.length()-1) ;
			 }
			//mtSservice.ActRetailDiscountBatchSave(maps);
			logger.info(item_ids);
			mtSservice.ActRetailDiscountBatchDeleteRequest(poiCode,item_ids,56);
			start=start+size;
		}
		
	}
	/**
	 * 
	 *  Description:药品批量删除
	 *  @author wangsheng  DateTime 2022年1月27日 下午1:57:41
	 *  @return
	 */
	@Override
	public List medicineBatchDeletes() {
	List<o2o_operate_upload_price> list=	o2o_operate_upload_priceMapper.selectList();
		for (o2o_operate_upload_price o2o_operate_upload_price : list) {
			String PoiCode=o2o_operate_upload_price.getPoiCode();
			String Goods_code=o2o_operate_upload_price.getGoods_code();
			logger.info(PoiCode+"->>"+Goods_code);
			mtSservice.medicineBatchDelete(PoiCode,Goods_code );
		}
		
		return null;
	}
	/**
	 * 
	 *  Description: 更新个别价格（删除折扣）
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	@Override
	public MtResult danCiDeleteZheKou(String poiCode) throws Exception {
		int size=100;
		int start =0;
		int count =o2o_operate_upload_priceMapper.findDCZKIdCount(poiCode);
		if (count==0) {
			return MtResult.err(400, "没有数据");
		}
		int page =count/size+1;
		String	Item_ids1 ="";
		String	Item_ids2 ="";
		for (int i = 0; i < page; i++) {
			Map<String, Object> maps =new HashMap<String, Object>(4);
			maps.put("poiCode",poiCode);
			maps.put("start",start);
			maps.put("size",size);
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.findDCZKId(maps);
			for (o2o_operate_upload_price re : list) {
				System.err.println(re.getItem_ids());
				if (re.getActType()==1001) {
					Item_ids1=Item_ids1+re.getItem_ids()+",";
				} else if (re.getActType()==56) {
					Item_ids2=Item_ids2+re.getItem_ids()+",";
				}
			}
			if (Item_ids1.endsWith(",")){
				Item_ids1 = Item_ids1.substring(0,Item_ids1.length()-1) ;
			 }
			if (Item_ids2.endsWith(",")){
				Item_ids2 = Item_ids2.substring(0,Item_ids2.length()-1) ;
			 }
			if (!Item_ids2.equals("")) {
				MtResult result2=mtSservice.ActRetailDiscountBatchDeleteRequest(poiCode,Item_ids2 ,56);
				logger.info((String) result2.getData());
			}
			if (!Item_ids1.equals("")) {
				MtResult result1=mtSservice.ActRetailDiscountBatchDeleteRequest(poiCode,Item_ids1 ,1001);
				logger.info((String) result1.getData());
			}
			start=start+size;
		}
		return MtResult.ok();
	}
	/**
	 * 
	 *  Description: 更新个别价格（更新价格）
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	@Override
	public MtResult danCiUpdatePrice(String poiCode) throws Exception {
		int size=100;
		int start =0;
		int count =o2o_operate_upload_priceMapper.findDanCiIsUpdateCount(poiCode);
		if (count==0) {
			return MtResult.err(400, "没有数据");
		}
		int page =count/size+1;
		for (int i = 0; i < page; i++) {
			Map<String, Object> maps =new HashMap<String, Object>(4);
			maps.put("poiCode",poiCode);
			maps.put("start",start);
			maps.put("size",size);
			System.err.println(maps);
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.findDanCiPriceUpdate(maps);
			String s=JSON.toJSONString(list);
			MtResult result =mtSservice.medicinePrice(poiCode,s);
			logger.info(result.toString());
			start=start+size;
		}
		return MtResult.err(200, "已完成");
	}

	/**
	 * 
	 *  Description: 单次（折扣活动创建）
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	@Override
	public MtResult danCiZheKouHuoDongUpdate(String poiCode) throws Exception {
		int size=50;
		int start =0;
		int count =o2o_operate_upload_priceMapper.DanCiUpdateDiscountZheKouCount(poiCode);
		if (count==0) {
			return MtResult.err(400, "没有数据");
		}
		int page =count/size+1;
		for (int i = 0; i < page; i++) {
			Map<String, Object> maps =new HashMap<String, Object>(4);
			maps.put("poiCode",poiCode);
			maps.put("start",start);
			maps.put("size",size);
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.DanCiUpdateZheKouDiscount(maps);
			String s=JSON.toJSONString(list);
			MtResult result =mtSservice.ActRetailDiscountBatchSave(poiCode,1001,s);
			logger.info(result.toString());
			start=start+size;
		}
		return MtResult.err(200, "已完成");
	}
	/**
	 * 
	 *  Description: 单次（爆品活动创建）
	 *  @author wangsheng  DateTime 2022年1月26日 下午5:00:52
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	@Override
	public MtResult danCiBaoPinHuoDongUpdate(String poiCode) throws Exception {
		int size=50;
		int start =0;
		int count =o2o_operate_upload_priceMapper.DanCiUpdateDiscountBaoPinCount(poiCode);
		if (count==0) {
			return MtResult.err(400, "没有数据");
		}
		int page =count/size+1;
		for (int i = 0; i < page; i++) {
			Map<String, Object> maps =new HashMap<String, Object>(4);
			maps.put("poiCode",poiCode);
			maps.put("start",start);
			maps.put("size",size);
			List<o2o_operate_upload_price> list =o2o_operate_upload_priceMapper.DanCiUpdateBaoPinDiscount(maps);
			String s=JSON.toJSONString(list);
			MtResult result =mtSservice.ActRetailDiscountBatchSave(poiCode,56,s);
			logger.info(result.toString());
			start=start+size;
		}
		return MtResult.err(200, "已完成");
	}
	/**
	 * 单次价格更新计数
	 *  Description:
	 *  @author wangsheng  DateTime 2022年1月27日 下午2:16:33
	 *  @param poiCode
	 *  @return
	 *  @throws Exception
	 */
	@Override
	public int findDanCiIsUpdateCount(String poiCode) throws Exception {
		int count = o2o_operate_upload_priceMapper.findDanCiIsUpdateCount(poiCode);
		return count;
	}
	
}
