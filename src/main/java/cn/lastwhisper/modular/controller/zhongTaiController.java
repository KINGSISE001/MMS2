package cn.lastwhisper.modular.controller;

import java.util.ArrayList;
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

import cn.lastwhisper.modular.pojo.o2oZtzi;
import cn.lastwhisper.modular.service.ztService;
import cn.lastwhisper.modular.service.impl.ztServiceimpl;
import cn.lastwhisper.modular.vo.GlobalResult;
@RequestMapping("zt")
@Controller
public class zhongTaiController {

	Logger log=  LoggerFactory.getLogger(zhongTaiController.class);
	
	@Autowired
	private ztService ztService;
	
	
	@RequestMapping(value = "/ztmdxxi", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult shangping(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.err.println(JSONObject.parse(string).toString());
		return ztService.addZtxxi(JSON.parseObject(JSONObject.parse(string).toString(), o2oZtzi.class));
	}
	
	@RequestMapping(value = "/allList", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public GlobalResult allList(@RequestBody String string, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return ztService.findAll();
	}
	@RequestMapping(value = "/selectByName", method = {RequestMethod.GET })
	@ResponseBody
	public GlobalResult selectByName(HttpServletRequest request,@RequestParam("names")String names) {
		// TODO Auto-generated method stub
		return ztService.selectByName(names);
	}
	
	@RequestMapping(value = "/updateZtaiById", method = {RequestMethod.POST })
	@ResponseBody
	public GlobalResult updateZtaiById(@RequestBody String string) {

		o2oZtzi o2oZtzi=JSONObject.toJavaObject((JSONObject)JSON.parse(string.toString()), o2oZtzi.class);
		return ztService.updateZtaiById(o2oZtzi) ;
	}
}
