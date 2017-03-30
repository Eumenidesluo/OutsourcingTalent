package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.CollectRecruitDao;
import entity.CollectUserRecruitEntity;
import entity.RecruitBean;
import service.RecruitService;

@Controller
@RequestMapping(value = "/recruit")
public class RecruitController {

	@Autowired
	RecruitService recruitService;
	@Autowired
	CollectRecruitDao collectRecruitDao;
	
	/**
     * <p>�ӿ����ƣ�collectStatus
     * <p>��Ҫ�������ж��û��Ƿ��ղ�ָ����Ƹ��¼
     * <p>���ʷ�ʽ��post
     * <p>URL: /recruit/collectStatus
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * recruitId   	Integer 	Y    	 NULL  ��Ƹ��¼Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���2000�������ղأ�4001����Ϊ�ղأ�
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/collectStatus")
	@ResponseBody
	public String collectStatus(HttpSession session,@RequestParam Integer recruitId) {
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
		
		if (collectRecruitDao.findCollectOne(userId, recruitId)!=null) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.NOT_EXIST);
		return JSON.toJSONString(result);
	}
	/**
     * <p>�ӿ����ƣ�queryCollects
     * <p>��Ҫ��������ѯְλ�ղ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /recruit/queryCollects
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     collects
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/queryCollects")
	@ResponseBody
	public String queryCollects(HttpSession session) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��½��֤
		
		List<?> list = collectRecruitDao.findCollectsByUserId(userId);
		List<RecruitBean> collects = new ArrayList<>();
		if (list == null) {
			result.put("status", StatusCode.SUCCESS);
			result.put("collects", collects);
			return JSON.toJSONString(result);
		}
		for(Object o:list) {
			CollectUserRecruitEntity entity = (CollectUserRecruitEntity)o;
			RecruitBean recruitEntity = recruitService.findRecruitByRecruitId(entity.getRecruitId());
			if (recruitEntity != null) {
				collects.add(recruitEntity);
			}
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("collects", collects);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>�ӿ����ƣ�removeCollect
     * <p>��Ҫ������ȡ��ְλ�ղ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /recruit/removeCollect
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * recruitId   	Integer 	Y    	 NULL  ��Ƹ��¼Id
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
	@RequestMapping(value = "/removeCollect")
	@ResponseBody
	public String removeCollect(HttpSession session,@RequestParam Integer recruitId) {
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
		if (collectRecruitDao.deleteCollect(userId, recruitId)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SQL_OP_ERR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>�ӿ����ƣ�collect
     * <p>��Ҫ���������ְλ�ղ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /recruit/collect
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * recruitId   	Integer 	Y    	 NULL  ��Ƹ��¼Id
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
	@RequestMapping(value = "collect")
	@ResponseBody
	public String collect(HttpSession session,@RequestParam Integer recruitId){
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
		CollectUserRecruitEntity entity = new CollectUserRecruitEntity();
		entity.setRecruitId(recruitId);
		entity.setUserId(userId);
		if (collectRecruitDao.addCollect(entity)==-1) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		return JSON.toJSONString(result);
	}
	/**
     * <p>�ӿ����ƣ�search
     * <p>��Ҫ��������ѯ��¼(ģ����ѯ)
     * <p>���ʷ�ʽ��post
     * <p>URL: /recruit/search
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * text   		String 		Y    	 NULL  ���ҹؼ���
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     list��..
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/search")
	@ResponseBody
	public String search(HttpSession session,@RequestParam String text){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��½��֤
		if (text == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<RecruitBean> list = recruitService.findByKey(text);
		result.put("status", StatusCode.SUCCESS);
		result.put("list", list);
		return JSON.toJSONString(result);
	}
	/**
     * <p>�ӿ����ƣ�query
     * <p>��Ҫ��������ѯ��¼
     * <p>���ʷ�ʽ��post
     * <p>URL: /recruit/query
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * tag   		String 		Y    	 NULL  ����Ȥ��ǩ
     * begin		Integer		Y		 0   	�Ѳ�ѯ��¼����
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     recruits��..
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/query")
	@ResponseBody
 	public String query(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		String tag = request.getParameter("tag");
		String begin = request.getParameter("begin");
		if (begin == null){
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<RecruitBean> list = recruitService.findRecruitsLimit(Integer.parseInt(begin), 10, tag);
		if (list == null) {
			result.put("status", StatusCode.MAX);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("recruits", list);
		return JSON.toJSONString(result);
	}
}
