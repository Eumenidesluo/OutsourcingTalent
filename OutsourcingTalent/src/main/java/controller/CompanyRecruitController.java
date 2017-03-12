package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.CoRecruitDao;

@Controller
@RequestMapping(value = "/recruit")
public class CompanyRecruitController {

	@Autowired
	CoRecruitDao coRecruitDao;
	
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
     * <pre>
     * 
     */
	@RequestMapping(value = "/query")
	public String query(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		String userId = session.getAttribute("userId").toString();
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		String tag = request.getParameter("tag");
		String begin = request.getParameter("begin");
		if (tag == null || begin == null){
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<?> list = coRecruitDao.findRecruitsLimit(Integer.parseInt(begin), 10, tag);
		if (list == null) {
			result.put("status", StatusCode.MAX);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("recruits", list);
		return JSON.toJSONString(result);
	}
}
