package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.CompanyDao;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	@Autowired
	private CompanyDao companyDao;
	
	/**
     * <p>�ӿ����ƣ�search
     * <p>��Ҫ��������ѯ��˾(ģ����ѯ)
     * <p>���ʷ�ʽ��post
     * <p>URL: /company/search
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * text   		String 		Y    	 NULL  ���ҹؼ���
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     companies��..
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/search")
	@ResponseBody
	public String search(@RequestParam String text,HttpSession session) {
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
		
		List<?> list = companyDao.findCompanyesByKey(text);
		if (list == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("companies", list);
		return JSON.toJSONString(result);
	}
}
