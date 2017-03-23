package controller;

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
import entity.RecruitBean;
import service.RecruitService;

@Controller
@RequestMapping(value = "/recruit")
public class CompanyRecruitController {

	@Autowired
	RecruitService recruitService;
	
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
     *     recruits��..
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
		List<?> list = recruitService.findByKey(text);
		if (list == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("recruit", list);
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
