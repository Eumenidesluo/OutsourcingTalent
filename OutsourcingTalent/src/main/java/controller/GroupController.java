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
     * <p>接口名称：create
     * <p>主要描述：创建群组
     * <p>访问方式：post
     * <p>URL: /group/create
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * creator   	Integer 		Y    	 NULL  创建者Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     groupId:${groupId}，新建群Id
     * }
     * </pre>
     * <p>修改者:陈琦
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
		}//登录验证

		Integer newGroupId = groupService.createGroup(userId);		
		if (newGroupId == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}//创建group并返回groupId
		result.put("status", StatusCode.SUCCESS);
		result.put("groupId",newGroupId);
		return JSON.toJSONString(result);
		
	}
	
	/**
     * <p>接口名称：invite
     * <p>主要描述：邀请用户加入团队
     * <p>访问方式：post
     * <p>URL: /group/invite
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * groupId   	Integer 		Y    	 NULL  团队Id
     * inviterId	Integer			Y		null	被邀请者Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
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
		}//登录验证
    	
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
     * <p>接口名称：deleteMember
     * <p>主要描述：移除团队成员
     * <p>访问方式：post
     * <p>URL: /group/deleteMember
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * groupId   	Integer 		Y    	 NULL  团队Id
     * deleteId		Integer			Y		null	被删除者Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
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
		}//登录验证
    	
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
     * <p>接口名称：findGroup
     * <p>主要描述：根据Id查找群组
     * <p>访问方式：post
     * <p>URL: /group/findGroup
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * groupId   	Integer 		Y    	 NULL  团队Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     group:查找到的团队信息
     * }
     * </pre>
     * <p>修改者:陈琦
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
		}//登录验证
    	
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
