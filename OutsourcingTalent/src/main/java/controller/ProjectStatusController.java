package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import component.ServiceException;
import service.ProjectStatusService;

@Controller
public class ProjectStatusController {

	@Autowired
	ProjectStatusService projectStatusService;
	String paraError = "Invalid parameter";
	
	@RequestMapping(value = "/apply")
	@ResponseBody
	public String apply(HttpServletRequest request){
		
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			return paraError;
		}
		
		if (projectStatusService.apply(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			return "success";
		}
		return "Unknow error";
	}
	
	@RequestMapping(value = "/appoint")
	@ResponseBody
	public String appoint(HttpServletRequest request){
		
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			return paraError;
		}
		
		try {
			return projectStatusService.appoint(Integer.parseInt(projectId), Integer.parseInt(projectId));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "NumberFormatException";
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/finish")
	@ResponseBody
	public String finish(HttpServletRequest request){
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			return paraError;
		}
		
		if (projectStatusService.finish(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			return "success";
		}
		return "Unknow error";
	}
}
