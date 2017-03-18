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
import entity.GroupEntity;
import service.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController {

	@Autowired
	GroupService groupService;
	
	/**
     * <p>�ӿ����ƣ�create
     * <p>��Ҫ����������Ⱥ��
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/create
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * creator   	Integer 		Y    	 NULL  ������Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     groupId:${groupId}���½�ȺId
     * }
     * </pre>
     * <p>�޸���:����
     * <pre>
     * 
     */
	@RequestMapping(value="/create")
	@ResponseBody
	public String createGroup(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤

		Integer newGroupId = groupService.createGroup(userId);		
		if (newGroupId == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}//����group������groupId
		result.put("status", StatusCode.SUCCESS);
		result.put("groupId",newGroupId);
		return JSON.toJSONString(result);
		
	}
	
	/**
     * <p>�ӿ����ƣ�invite
     * <p>��Ҫ�����������û������Ŷ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/invite
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * groupId   	Integer 		Y    	 NULL  �Ŷ�Id
     * inviterId	Integer			Y		null	��������Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
     * <pre>
     * 
     */
	@RequestMapping(value="/invite")
	@ResponseBody
	public String inviteJoin(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		String groupId = request.getParameter("groupId");
		String inviterId = request.getParameter("inviterId");
		
		if (groupId == null || inviterId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (groupService.inviteMember(Integer.parseInt(groupId), Integer.parseInt(inviterId))){
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>�ӿ����ƣ�deleteMember
     * <p>��Ҫ�������Ƴ��Ŷӳ�Ա
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/deleteMember
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * groupId   	Integer 		Y    	 NULL  �Ŷ�Id
     * deleteId		Integer			Y		null	��ɾ����Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
     * <pre>
     * 
     */
	@RequestMapping(value="/deleteMember")
	@ResponseBody
	public String deleteMember(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		String groupId = request.getParameter("groupId");
		String deleteId = request.getParameter("deleteId");	
		if (groupId == null || deleteId == null ) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (groupService.deleteMenber(Integer.parseInt(groupId), Integer.parseInt(deleteId))){
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}

	/**
     * <p>�ӿ����ƣ�findGroup
     * <p>��Ҫ����������Id����Ⱥ��
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/findGroup
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * groupId   	Integer 		Y    	 NULL  �Ŷ�Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     group:���ҵ����Ŷ���Ϣ
     * }
     * </pre>
     * <p>�޸���:����
     * <pre>
     * 
     */
	@RequestMapping(value = "/findGroup")
	@ResponseBody
	public String findGroup(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		String groupId = request.getParameter("groupId");
		if (groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		GroupEntity groupEntity = groupService.findGroup(Integer.parseInt(groupId));
		if (groupEntity == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("group", groupEntity);
		return JSON.toJSONString(result);
	}
	
}
