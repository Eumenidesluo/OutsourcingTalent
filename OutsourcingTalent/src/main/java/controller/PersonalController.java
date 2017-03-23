package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import entity.PersonalInfEntity;
import service.PersonalInfService;

/**
 * Created by Eumenides on 2017/2/19.
 */
@Controller
@RequestMapping(value = "/personal")
public class PersonalController {
    @Autowired
    PersonalInfService personService;
    
    /**
     * <p>�ӿ����ƣ�update
     * <p>��Ҫ��������ȡ������Ϣ
     * <p>���ʷ�ʽ��post
     * <p>URL: /personal/update
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * personJson   Integer 		Y    	 NULL  �û���Ϣ
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
	@RequestMapping(value = "/update")
    @ResponseBody
    public String execute(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
    	String personJson = request.getParameter("personJson");
    	if (personService.updatePersonInformation(personJson)) {
    		result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}else{
			result.put("status", StatusCode.NOT_EXIST);
			return JSON.toJSONString(result);
		}
			
    }
	
	/**
     * <p>�ӿ����ƣ�query
     * <p>��Ҫ��������ȡ������Ϣ
     * <p>���ʷ�ʽ��post
     * <p>URL: /personal/query
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * person   	Integer 		Y    	 NULL  �û�Id
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
	@RequestMapping(value = "/query")
	@ResponseBody
	public String queryPersonal(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		String personId = request.getParameter("person");
		if (personId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null||userId != Integer.parseInt(personId)) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		PersonalInfEntity entity = personService.getPersonById(personId);
		if (entity == null) {
			result.put("status", StatusCode.NOT_EXIST);
			return JSON.toJSONString(result);
		}else{
			result.put("status", StatusCode.SUCCESS);
			result.put("personal", entity);
			return JSON.toJSONString(result);
		}
	}
}
