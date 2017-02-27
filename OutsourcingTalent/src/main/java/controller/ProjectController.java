package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import entity.ProjectEntity;
import service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	@RequestMapping(value = "/release")
	@ResponseBody
	public String releaseProject(HttpServletRequest request){
		String projectJson = request.getParameter("projectJson");
		if (projectJson == null) {
			return "Invalid parameter";
		}
		ProjectEntity projectEntity = JSON.parseObject(projectJson, ProjectEntity.class);
		Integer projectId = projectService.releaseProject(projectEntity);
		return projectId.toString();
	}
	
	@RequestMapping(value = "/cacelRelease")
	@ResponseBody
	public String cacelProject(HttpServletRequest request){
		String projectId = request.getParameter("projectId");
		if (projectService.deleteProject(Integer.parseInt(projectId))) {
			return "success";
		}
		return "faild";
	}
	
	@RequestMapping(value="/query")
	@ResponseBody
	public String queryyProject(HttpServletRequest request){
		String projectId = request.getParameter("projectId");
		if (projectId == null) {
			return "Invalid parameter";
		}
		ProjectEntity entity = projectService.queryProject(Integer.parseInt(projectId));
		return JSON.toJSONString(entity);
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public String updateProject(HttpServletRequest request){
		String projectJson = request.getParameter("projectJson");
		if (projectJson == null) {
			return "Invalid parameter";
		}
		ProjectEntity projectEntity = JSON.parseObject(projectJson,ProjectEntity.class);
		if (projectService.updateProject(projectEntity)) {
			return "success";
		}
		return "faild";
	}
	
}
