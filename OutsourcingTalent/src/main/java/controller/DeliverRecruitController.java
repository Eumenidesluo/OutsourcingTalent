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
     * <p>�ӿ����ƣ�deliver
     * <p>��Ҫ������Ͷ�ݼ���
     * <p>���ʷ�ʽ��post
     * <p>URL: /delivery/deliver
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * recruitId   	Integer 	Y    	 NULL  ��Ƹ��¼Id
     * resumeId		Integer		Y		NULL	Ͷ���ļ���Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��½��֤
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
     * <p>�ӿ����ƣ�status
     * <p>��Ҫ�������ж��û��Ƿ��ղ�ָ����Ƹ��¼
     * <p>���ʷ�ʽ��post
     * <p>URL: /delivery/status
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * recruitId   	Integer 	Y    	 NULL  ��Ƹ��¼Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���2000������Ͷ�ݣ�4001����ΪͶ�ݣ�
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��½��֤
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
