package cn.lastwhisper.core.jifen;





import cn.lastwhisper.modular.pojo.hxProductList;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;

public interface jiFens {

	EasyUIDataGridResult findproductlistByPage(hxProductList productList, Integer page, Integer rows);

	GlobalResult productUpdata(hxProductList productList);
	
	boolean listCard(hxProductList  hxProductList);//同步海翔积分到数据库
	boolean findCard(hxProductList hxProductList);//叮咚积分查询,同步到库，无则创建会员用户
	boolean cyjftbu(hxProductList hxProductList);//差异积分同步
	
	boolean fujifengtongbu(hxProductList hxProductList);
	
	void integralCallback(String  storeid,String userid ,Integer integral); //积分回调
	
	/**
	 * 异常积分查询
	 */
	EasyUIDataGridResult  SelectErrIntegral (String  storeid ,int start ,int end);

	
}
