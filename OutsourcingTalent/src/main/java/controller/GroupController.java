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
import entity.MemberBean;
import entity.RelateUserGroupBean;
import service.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController {

	@Autowired
	GroupService groupService;
	
	/**
     * <p>�ӿ����ƣ�init
     * <p>��Ҫ��������ȡ���˵�Ⱥ����Ϣ
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/init
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     informations��List<RelateUserGroupBean> ��place�ֶ�Ϊ0����Ϊ�ӳ�������Ϊ��Ա
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/init")
	@ResponseBody
	public String init(HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		List<RelateUserGroupBean> list = groupService.getUserGroupInfo(userId);
		result.put("status", StatusCode.SUCCESS);
		result.put("informations",list);
		return JSON.toJSONString(result);
	}
	/**
     * <p>�ӿ����ƣ�create
     * <p>��Ҫ����������Ⱥ��
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/create
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * gourpName	String		Y		NUll	Ⱥ����
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     groupId:${groupId}���½�ȺId
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value="/create")
	@ResponseBody
	public String createGroup(HttpSession session,@RequestParam String groupName){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		if (groupName == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		Integer newGroupId = groupService.createGroup(userId,groupName);		
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
     * 
     */
	@RequestMapping(value="/invite")
	@ResponseBody
	public String inviteJoin(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
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
     * 
     */
	@RequestMapping(value="/deleteMember")
	@ResponseBody
	public String deleteMember(HttpSession session,@RequestParam Integer groupId,@RequestParam Integer deleteId){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
		if (groupId == null || deleteId == null ) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (groupService.deleteMenber(groupId, deleteId)){
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}

	/**
     * <p>�ӿ����ƣ�queryGroupMembers
     * <p>��Ҫ��������ѯȺ���Ա
     * <p>���ʷ�ʽ��post
     * <p>URL: /group/query
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * groupId   	Integer 		Y    	 NULL  �Ŷ�Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     members:List<MemberBean>�Ŷӳ�Ա��Ϣ
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/query")
	@ResponseBody
	public String findGroup(@RequestParam Integer groupId,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
		if (groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<MemberBean> memebers = groupService.queryMembers(groupId);
		result.put("status", StatusCode.SUCCESS);
		result.put("members", memebers);
		return JSON.toJSONString(result);
	}
	
}
