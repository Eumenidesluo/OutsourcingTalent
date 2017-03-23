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
     * <p>接口名称：update
     * <p>主要描述：获取个人信息
     * <p>访问方式：post
     * <p>URL: /personal/update
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * personJson   Integer 		Y    	 NULL  用户信息
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
     * <p>接口名称：query
     * <p>主要描述：获取个人信息
     * <p>访问方式：post
     * <p>URL: /personal/query
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * person   	Integer 		Y    	 NULL  用户Id
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
