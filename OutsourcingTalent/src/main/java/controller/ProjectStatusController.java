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
import service.ProjectStatusService;

@Controller
@RequestMapping(value = "bid")
public class ProjectStatusController {

	@Autowired
	ProjectStatusService projectStatusService;
	
	/**
     * <p>�ӿ����ƣ�apply
     * <p>��Ҫ����������ָ����Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /bid/apply
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId	Integer			Y		null	���������ĿId
     * groupId   	Integer 		Y    	 NULL  ������Ŷ�Id
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
	@RequestMapping(value = "/apply")
	@ResponseBody
	public String apply(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (projectStatusService.apply(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>�ӿ����ƣ�appoint
     * <p>��Ҫ����������Id����Ⱥ��
     * <p>���ʷ�ʽ��post
     * <p>URL: /bid/appoint
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId	Integer			Y		null��������ĿId
     * groupId   	Integer 		Y    	NULL  ί�е��Ŷ�Id
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
	@RequestMapping(value = "/appoint")
	@ResponseBody
	public String appoint(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}//������֤
		
		try {
			String operation = projectStatusService.appoint(Integer.parseInt(projectId), Integer.parseInt(projectId));
			if (operation.equals("success")) {
				result.put("status", StatusCode.SUCCESS);
				return JSON.toJSONString(result);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.put("status", StatusCode.UNKNOW_ERROR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("status", StatusCode.UNKNOW_ERROR);
		}
		return JSON.toJSONString(result);
	}
	
	
	@RequestMapping(value = "/finish")
	@ResponseBody
	public String finish(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (projectStatusService.finish(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
}
