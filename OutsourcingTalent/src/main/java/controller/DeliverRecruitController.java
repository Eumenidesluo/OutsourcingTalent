package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import service.DeliveryRecruitService;

@Controller
@RequestMapping(value = "/delivery")
public class DeliverRecruitController {

	@Autowired
	DeliveryRecruitService deliveryRecruitService;
	
	/**
     * <p>接口名称：deliver
     * <p>主要描述：投递简历
     * <p>访问方式：post
     * <p>URL: /delivery/deliver
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * recruitId   	Integer 	Y    	 NULL  招聘记录Id
     * resumeId		Integer		Y		NULL	投往的简历Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/deliver")
	@ResponseBody
	public String deliver(HttpSession session,@RequestParam Integer recruitId,@RequestParam Integer resumeId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (recruitId == null || resumeId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (deliveryRecruitService.delivery(recruitId, userId,resumeId)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：status
     * <p>主要描述：判断用户是否收藏指定招聘记录
     * <p>访问方式：post
     * <p>URL: /delivery/status
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * recruitId   	Integer 	Y    	 NULL  招聘记录Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表（2000代表以投递，4001代表为投递）
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/status")
	@ResponseBody
	public String hasDeliveried(HttpSession session,@RequestParam Integer recruitId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (deliveryRecruitService.hasDeliveried(userId, recruitId)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.NOT_EXIST);
		return JSON.toJSONString(result);
	}
}
