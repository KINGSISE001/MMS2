package cn.lastwhisper.modular.mt;

import java.util.Map;

import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;
import net.sf.jsqlparser.statement.update.Update;

public interface mtSservice {
	public MtResult testpoi_code (String AppPoiCode);
	public GlobalResult medicineBatchUpdate(String AppPoiCode,String poiname,Integer Limit,Integer Offset);
	public MtResult medicinePrice(String AppPoiCode,String data);
	public MtResult ActRetailDiscountBatchDeleteRequest(String poiCode,String ItemIds ,Integer type) throws Exception ;
	public Map<String, Object> ActRetailDiscountListRequest(Map<String , Object> maps) throws Exception;
	public MtResult ActRetailDiscountBatchSave(String poiCode,Integer type,String data) throws Exception;
	public void medicineBatchDelete(String AppPoiCode,String MedicineCode) ;

	
	
	
}
