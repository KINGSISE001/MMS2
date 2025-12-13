package cn.lastwhisper.modular.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.lastwhisper.core.util.UserUtils;
import cn.lastwhisper.modular.pojo.User;
import cn.lastwhisper.modular.pojo.dming;
import cn.lastwhisper.modular.pojo.sqmd;
import cn.lastwhisper.modular.service.MtProductService;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;

@RequestMapping("mt")
@Controller
public class MtProductController {
Logger log=  LoggerFactory.getLogger(MtProductController.class);
	@Autowired
	private MtProductService mtProductService;

	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/shangping", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult shangping(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JSONArray jaonArray = JSONArray.parseArray(string);
		log.info("传过来的值[{}]",jaonArray.toJSONString());
		List lists = new ArrayList();
		for (int i = 0; i < jaonArray.size(); i++) {
			JSONObject jsonObject = jaonArray.getJSONObject(i);
			
			Map<String, Object> maps = JSON.parseObject(jsonObject.toJSONString(), Map.class);
			if (maps != null) {
				lists.add(maps);
			}
		}
		if (lists.size() != 0) {
			mtProductService.insertShangPing(lists);
			return GlobalResult.ok("ok");
		} else {
			return GlobalResult.ok("err");
		}

	}

	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/findDm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult findDm(@RequestParam(value = "dm", required = true, defaultValue = "") String dm,
			@RequestParam(value = "zhuangtai", required = true, defaultValue = "") int zhuangtai,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.err.println(dm + zhuangtai);
		dming dming = mtProductService.selectProducts(dm, zhuangtai);
		return GlobalResult.ok(dming);
	}

	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/updateDmZhuangtai", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult updateDmZhuangtai(@RequestParam(value = "dm", required = true, defaultValue = "") String dm,
			@RequestParam(value = "zhuangtai", required = true, defaultValue = "") int zhuangtai,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		int m = mtProductService.updateDmZhuangtai(dm, zhuangtai);
		return GlobalResult.ok(m);
	}
	
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/find", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public EasyUIDataGridResult find(dming dming,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows,User user) throws Exception {

		System.out.println(UserUtils.getSubjectUser().getUser_name());
		return mtProductService.selectdming(dming,page,rows);
	}
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/findadd", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult findadd(dming dming) throws Exception {
		int o=mtProductService.selectdming(dming, 1, 1).getTotal();
		if (o>=1) {
			return GlobalResult.build(201, "已存在此店铺,注意查找");
		}else {
			int i=mtProductService.adddming(dming);
			
			if (i>0) {
				return GlobalResult.build(200, "添加成功");
			}else {
				return GlobalResult.build(201, "添加失败");
			}
		}
		

	}
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/findupdate", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult findupdate(dming dming) throws Exception {
	int i=	mtProductService.findupdate(dming);
		if (i>0) {
			return GlobalResult.build(200, "更新成功");
		}else {
			return GlobalResult.build(201, "更新失败");
		}
		
	}
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/goods", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult goods() throws Exception {

		return mtProductService.selectShop();
		
		
	}
	
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/shangpings", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult shangpings(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(string);
		JSONArray jaonArray = JSONArray.parseArray(string);

		List lists = new ArrayList();
		for (int i = 0; i < jaonArray.size(); i++) {
			JSONObject jsonObject = jaonArray.getJSONObject(i);
			Map<String, Object> maps = JSON.parseObject(jsonObject.toJSONString(), Map.class);
			if (maps != null) {
				lists.add(maps);
			}
		}
		if (lists.size() != 0) {
			mtProductService.insertShangPings(lists);
			return GlobalResult.ok("ok");
		} else {
			return GlobalResult.ok("err");
		}

	}
	
	
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/mdxxi", method = { RequestMethod.POST})
	@ResponseBody
	public GlobalResult mdxxi(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.err.println(string);
		JSONArray jaonArray = JSONArray.parseArray(string);
		if (jaonArray.size()==0) {
			return GlobalResult.ok("无数据");
		}

List< sqmd>  lists=new ArrayList<sqmd>() ;

			for (int i = 0; i < jaonArray.size(); i++) {
				JSONObject jsonObject = jaonArray.getJSONObject(i);
				sqmd maps = JSON.parseObject(jsonObject.toJSONString(), sqmd.class);
				if (maps != null) {
					lists.add(maps);
				}
			}
		if (lists.size() != 0) {
			mtProductService.insertMdxxi(lists);
		
			return GlobalResult.ok("ok");
		} else {
			return GlobalResult.ok("err");
		}

	}
	
	

	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/dwxxi", method = { RequestMethod.POST})
	@ResponseBody
	public GlobalResult dwxxi(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
return mtProductService.selectDwCshi();
	}
	
	
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/dwxxztai", method = { RequestMethod.POST})
	@ResponseBody
	public GlobalResult dwxxztai(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.err.println(string);
		JSONObject json=JSONObject.parseObject(string);
		String newdz="";
		if (json.getString("newdz")!=null) {
		newdz=json.getString("newdz");
		}
		mtProductService.updateDwsqZtai(json.getIntValue("zt"), json.getIntValue("dwid"),newdz);
return GlobalResult.ok();
	}
	
	
	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/updateypssuoZtai", method = { RequestMethod.POST})
	@ResponseBody
	public GlobalResult updateypssuoZtai(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.err.println(string);
		JSONObject json=JSONObject.parseObject(string);
		mtProductService.updateypssuoZtai(json.getString("id"),json.getString("zt"),json.getString("bzhu") );
return GlobalResult.ok();
	}
	
	/*
	 * 获取市场店铺信息
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/findscdpxx", method = { RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public GlobalResult findscdpxx(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
return GlobalResult.build(200,"ok", mtProductService.findscdpxxs());
	}
	

	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/updateDmXinXiZhuangtai", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult updateDmXinXiZhuangtai(@RequestParam(value = "dmid", required = true, defaultValue = "") String dmid,
			@RequestParam(value = "zhuangtai", required = true, defaultValue = "") String zhuangtai,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		mtProductService.updateDmXinXiZhuangtai(dmid, zhuangtai);
		return GlobalResult.ok();
	}
	
	

	/*
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/upmdxx", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult upmdxx(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(string);
			JSONObject jsonObject = JSONObject.parseObject(string);
			Map<String, String> maps = JSON.parseObject(jsonObject.toJSONString(), Map.class);
			for (Object obj : maps.keySet()){
			       System.out.println("key为："+obj+"值为："+maps.get(obj));
			   }		
		if (!maps.isEmpty()) {
			mtProductService.upDmXinXis(maps);
			return GlobalResult.ok("ok");
		} else {
			return GlobalResult.ok("err");
		}

	}
	
	
	
}
