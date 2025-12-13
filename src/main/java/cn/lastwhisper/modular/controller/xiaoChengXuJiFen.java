package cn.lastwhisper.modular.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lastwhisper.core.annotation.LogAnno;
import cn.lastwhisper.core.jifen.jiFens;
import cn.lastwhisper.core.util.Signature;
import cn.lastwhisper.core.util.UserUtils;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.pojo.hxProductList;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;

@RequestMapping("jfen")
@Controller
public class xiaoChengXuJiFen {
	private static final String SignKey = "Зашел в миреcs";

	@Autowired
	private jiFens jiFens;

	/**
	 * 参数携带sign请求进行参数验证
	 *
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/verifySignature", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult verifySignature(HttpServletRequest request) throws UnsupportedEncodingException {
		if (request.getParameterMap().isEmpty()) {
			return MtResult.err(400, "没传值");
		} else {
			// 参数转换成Map		
			@SuppressWarnings("unchecked")
			TreeMap<String, Object> paramMap = Signature.switchMap(request.getParameterMap());
			// 进行参数签名验证
			String sign = paramMap.get("sign").toString();
			if (sign == null) {
				return MtResult.err(401, "没传sign值!");
			} 
			if (!Signature.isSignEquals(paramMap, SignKey)) {
				return MtResult.err(400, "verification failed");
			}
			
		String  userid= paramMap.get("user_id").toString();
		String storeid=paramMap.get("store_id").toString();//店铺id
		
		int  integral=Integer.valueOf((String) paramMap.get("integral"));//消耗的积分
		
		
		jiFens.integralCallback(storeid, userid ,integral);
			
		return MtResult.ok();
		}

	}

	/**
	* 参数携带sign请求进行参数验证
	*
	* @param request
	* @return
	* @throws UnsupportedEncodingException 
	*/
	@RequestMapping(value = "/productlistByPage", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public EasyUIDataGridResult userlistByPage(hxProductList productList,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows) {
		return jiFens.findproductlistByPage(productList, page, rows);
	}

	@LogAnno(operateType = "更新")
	@RequestMapping(value = "productupdate", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult productupdate(hxProductList productList) {
		return jiFens.productUpdata(productList);
	}

	@LogAnno(operateType = "删除")
	@RequestMapping(value = "productdelete", method = RequestMethod.POST)
	@ResponseBody
	public GlobalResult productdelete(hxProductList productList) {
		return GlobalResult.build(400, "删除" + productList.getCompanyname() + "失败！");
	}
	

	/**
	* 错误积分
	*
	* @param request
	* @return
	* @throws UnsupportedEncodingException 
	*/
	@RequestMapping(value = "/errjflistByPage", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public EasyUIDataGridResult errlistByPage(
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows) {
		User user = UserUtils.getSubjectUser();
		if (user.getDingDongId()!=null) {
			System.err.println(user.getDingDongId()+user.getUser_name());
			return jiFens.SelectErrIntegral(user.getDingDongId(), page, rows);
		}
		System.err.println(user.getDingDongId()+user.getUser_name());
		return null;
		
	}
	
}
