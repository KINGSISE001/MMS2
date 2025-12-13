package cn.lastwhisper.modular.mt;

import java.util.Map;

import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;
import net.sf.jsqlparser.statement.update.Update;

public interface mtSservice {
	MtResult testpoi_code (String AppPoiCode);
	GlobalResult medicineBatchUpdate(String AppPoiCode,String poiname,Integer Limit,Integer Offset);
	MtResult medicinePrice(String AppPoiCode,String data);
	MtResult ActRetailDiscountBatchDeleteRequest(String poiCode,String ItemIds ,Integer type) throws Exception ;
	Map<String, Object> ActRetailDiscountListRequest(Map<String , Object> maps) throws Exception;
	MtResult ActRetailDiscountBatchSave(String poiCode,Integer type,String data) throws Exception;
	void medicineBatchDelete(String AppPoiCode,String MedicineCode) ;

	
	
	
}
