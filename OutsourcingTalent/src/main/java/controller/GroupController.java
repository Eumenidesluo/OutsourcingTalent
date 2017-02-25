package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import entity.GroupEntity;
import service.GroupService;

@Controller
@RequestMapping(value="/group")
public class GroupController {

	@Autowired
	GroupService groupService;
	@RequestMapping(value="/create")
	@ResponseBody
	public String createGroup(HttpServletRequest request){
		String creator = request.getParameter("creator");
		
		if (creator == null) {
			return "Parameter cannot be null";
		}
		
		return groupService.createGroup(Integer.parseInt(creator)).toString();
	}
	
	@RequestMapping(value="/inviteJoin")
	@ResponseBody
	public String inviteJoin(HttpServletRequest request){
		String groupId = request.getParameter("groupId");
		String inviterId = request.getParameter("inviterId");
		
		if (groupId == null || inviterId == null) {
			return "Invalid parameter";
		}
		if (groupService.inviteMember(Integer.parseInt(groupId), Integer.parseInt(inviterId)))
			return "success";
		return "invite faild";
	}
	
	@RequestMapping(value="/deleteMember")
	@ResponseBody
	public String deleteMember(HttpServletRequest request){
		String groupId = request.getParameter("groupId");
		String deleteId = request.getParameter("deleteId");
		
		if (groupId == null || deleteId == null ) {
			return "Invalid parameter";
		}
		if (groupService.deleteMenber(Integer.parseInt(groupId), Integer.parseInt(deleteId)))
			return "success";
		return "delete faild";
	}

	@RequestMapping(value = "/findGroup")
	@ResponseBody
	public String findGroup(HttpServletRequest request){
		String groupId = request.getParameter("groupId");
		if (groupId == null) {
			return "Invalid parameter";
		}
		GroupEntity groupEntity = groupService.findGroup(Integer.parseInt(groupId));
		return JSON.toJSONString(groupEntity);
	}
	
}
