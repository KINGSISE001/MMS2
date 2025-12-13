package cn.lastwhisper.modular.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.lastwhisper.core.util.PageBean;
import cn.lastwhisper.modular.pojo.Completedorder;
import cn.lastwhisper.modular.pojo.Detail;
import cn.lastwhisper.modular.pojo.SettlementInformation;
import cn.lastwhisper.modular.service.OrderService;
import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.vo.MtResult;

@RequestMapping("order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	
	/*
	 * 已完成订单produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/Completed", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult Completed(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map map = new HashMap();

		//后台接收
		Enumeration<String> er = request.getParameterNames();
		while (er.hasMoreElements()) {
			String name = er.nextElement();
			String value = URLDecoder.decode(request.getParameter(name), "utf-8");
			map.put(name, value);
		}
		if (map.size() == 0) {
			System.err.println("Completed:TEST!");
			return MtResult.ok();
		}
        // 将 Map 转换为 实体类
		Completedorder completedorder = JSON.parseObject(JSON.toJSONString(map), Completedorder.class);
		
		Long OrderId =completedorder.getOrderId();
		System.out.println("OrderId:" + OrderId);
		String Status= completedorder.getStatus();
	
		System.out.println(Status);
		int r=	orderService.updateStatus(OrderId, Status);
		
		if (r>0) {
			
			return MtResult.ok();
		}
		else {
			MtResult.err(400,"err");
		}
	return MtResult.ok();

	}
	
	/*
	 * 已确定订单produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/Confirmed", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public MtResult Confirmed(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map map = new HashMap();

		//后台接收
		Enumeration<String> er = request.getParameterNames();
		while (er.hasMoreElements()) {
			String name = er.nextElement();
			String value = URLDecoder.decode(request.getParameter(name), "utf-8");
			map.put(name, value);
		}
		if (map.size() == 0) {
			System.err.println("Completed:TEST!");
			return MtResult.ok();
		}
        // 将 Map 转换为 实体类
		Completedorder completedorder = JSON.parseObject(JSON.toJSONString(map), Completedorder.class);
		System.out.println("OrderId:" + completedorder.getOrderId());
		List<Detail> lists = new ArrayList<Detail>();
		JSONArray detailJsonArray = JSONArray.parseArray(completedorder.getDetail());
		for (int i = 0; i < detailJsonArray.size(); i++) {
			JSONObject jsonObject = detailJsonArray.getJSONObject(i);
			Detail detail = JSON.parseObject(jsonObject.toJSONString(), Detail.class);
			detail.setOrderId(completedorder.getOrderId());
			lists.add(detail);
		}
		completedorder.setDate();
		completedorder.setTimeMillis();
		float reduce_fee = 0;
		String remark = "";
		JSONArray extras = JSONArray.parseArray(completedorder.getExtras());
		for (int i = 0; i < extras.size(); i++) {
			JSONObject activityDetail = extras.getJSONObject(i);
			reduce_fee += activityDetail.getFloatValue("reduce_fee");
			remark += activityDetail.getString("remark") + ";";
		}

		String estraString = "共减去:" + reduce_fee + "元,分别为:" + remark;
		completedorder.setExtras(estraString);
		completedorder.setDetail("Detail");
		int count1 = orderService.insertCompletedorder(completedorder);
		int count2 = orderService.insertDetail(lists);

		if (count1 > 0 && count2 > 0) {
			System.out.println(count1+"---Completedorder\r"+count2+"---Detail");
			return MtResult.ok();
		} else {
			return MtResult.err(400,"err");
		}

	}
	
	

	/*
	 * 订单结算信息
	 * Order settlement information...
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/Settlement", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult Settlement(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map map = new HashMap();

		//后台接收
		Enumeration<String> er = request.getParameterNames();
		while (er.hasMoreElements()) {
			String name = er.nextElement();
			String value = URLDecoder.decode(request.getParameter(name), "utf-8");
			map.put(name, value);
		}
		if (map.size() == 0) {
			System.err.println("Settlement:TEST!");
			return GlobalResult.ok("ok");
		}

        SettlementInformation settlementinformation = JSON.parseObject(JSON.toJSON(map).toString(),
				SettlementInformation.class);
		JSONArray activityDetailsjsonArray = JSONArray.parseArray(settlementinformation.getActivitydetails());
		float chargeAmount = 0;
		String chargeDesc = "";
		for (int i = 0; i < activityDetailsjsonArray.size(); i++) {
			JSONObject activityDetail = activityDetailsjsonArray.getJSONObject(i);
			chargeAmount += activityDetail.getFloatValue("chargeAmount");
			chargeDesc += activityDetail.getString("chargeDesc") + ";";
		}
		settlementinformation.setActivitydetails("共减去:" + chargeAmount + "元,分别为:" + chargeDesc);

		int count = orderService.insertsettlementinformation(settlementinformation);
		if (count > 0) {
			System.out.println(count+"--settlementinformation");
			return GlobalResult.ok("ok");
		} else {
			return GlobalResult.build(404, "Request failed");
		}

	}

	

	/*
	 * 订单退款信息
	 * OrderRefundDetail
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/Refund", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult Refund(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map map = new HashMap();

		//后台接收
		Enumeration<String> er = request.getParameterNames();
		while (er.hasMoreElements()) {
			String name = er.nextElement();
			String value = URLDecoder.decode(request.getParameter(name), "utf-8");
			map.put(name, value);
		}
		if (map.size() == 0) {
			System.err.println("Refund:TEST!");
			return GlobalResult.ok("ok");
		}
        System.err.println(JSON.toJSONString(map));
return GlobalResult.ok("ok");
	}

	/*
	 * 拉取订单信息
	 * Pull order
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/PullOrder", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult PullOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
	if ( request.getParameter("AppPoiCode")==null||request.getParameter("rows")==null||request.getParameter("page")==null||request.getParameter("maxtime")==null) {
		return GlobalResult.build(400,"你看那个参数没传<AppPoiCode,page,rows,maxtime>");
	}
		
		String pageString=request.getParameter("page");
		if (pageString.equals("")) {
			pageString="0";
		}
		int page = Integer.valueOf(pageString) ;
		if (page<=0) {
			page =1;
		}
	int 	rows = 20;
		
		String maxtimeString =request.getParameter("maxtime");
		if (maxtimeString.equals("")) {
			maxtimeString="0";
		}
		long maxtime =Long.valueOf(maxtimeString);
            PageBean pageBean = new PageBean(page,rows);
            int start =pageBean.getStart();
            int size = pageBean.getPageSize();
		
		String AppPoiCodes = request.getParameter("AppPoiCode");
		if (AppPoiCodes==null ||AppPoiCodes.equals("")) {
			return GlobalResult.build(201,"未传入美团门店ID");
		}
		System.err.println(request.getParameter("AppPoiCode"));
		String[ ] AppPoiCode =AppPoiCodes.split(",");
return GlobalResult.build(200, "ok",JSON.toJSON(orderService.selectorderinformation(AppPoiCode,start, size,maxtime)));
	}
	

	/*
	 * 拉取订单信息
	 * Pull order
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/PullOrders", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public EasyUIDataGridResult PullOrders(Completedorder completedorder,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "rows", required = true, defaultValue = "10") Integer rows) throws Exception {
	
		System.err.println("传入的ID"+completedorder.getOrderId());
return orderService.findOrderlistByPage(completedorder,page, rows);
	}
	
	/*
	 * 拉取订单信息
	 * Pull order
	 * produces = MediaType.APPLICATION_JSON_VALUE,@RequestBody String string,
	 */
	@RequestMapping(value = "/PullOrdersDetail", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public EasyUIDataGridResult PullOrdersDetail(
	@RequestParam(value = "orderId",required = true) Long orderId
	 )throws Exception {
	
		System.err.println("Detail传入的ID"+orderId);
return orderService.findDetailByOrderId(orderId);
	}
	
	
	

	
	
	
}
