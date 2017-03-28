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
     * <p>接口名称：init
     * <p>主要描述：获取个人的群组信息
     * <p>访问方式：post
     * <p>URL: /group/init
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     informations：List<RelateUserGroupBean> 若place字段为0，则为队长，否则为队员
     * }
     * </pre>
     * <p>修改者:陈琦
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
     * <p>接口名称：create
     * <p>主要描述：创建群组
     * <p>访问方式：post
     * <p>URL: /group/create
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * gourpName	String		Y		NUll	群组名
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     groupId:${groupId}，新建群Id
     * }
     * </pre>
     * <p>修改者:陈琦
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
		}//登录验证
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
     * <p>接口名称：queryGroupMembers
     * <p>主要描述：查询群组成员
     * <p>访问方式：post
     * <p>URL: /group/query
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * groupId   	Integer 		Y    	 NULL  团队Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     members:List<MemberBean>团队成员信息
     * }
     * </pre>
     * <p>修改者:陈琦
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
		}//登录验证
    	
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
