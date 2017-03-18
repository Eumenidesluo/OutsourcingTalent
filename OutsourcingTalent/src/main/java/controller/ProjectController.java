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
import entity.ProjectEntity;
import service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	/**
     * <p>接口名称：release
     * <p>主要描述：公司发布项目
     * <p>访问方式：post
     * <p>URL: /project/release
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectJson   String 		Y    	NULL  项目信息的JSon
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     projectId:项目Id
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
	@RequestMapping(value = "/release")
	@ResponseBody
	public String releaseProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectJson = request.getParameter("projectJson");
		if (projectJson == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		ProjectEntity projectEntity = JSON.parseObject(projectJson, ProjectEntity.class);
		Integer projectId = projectService.releaseProject(projectEntity);
		if (projectId == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("projectId", projectId);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：cancel
     * <p>主要描述：取消已发布的项目
     * <p>访问方式：post
     * <p>URL: /project/cancel
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId   String 		Y    	NULL  项目Id
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
	@RequestMapping(value = "/cancel")
	@ResponseBody
	public String cacelProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectId = request.getParameter("projectId");
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (projectService.deleteProject(Integer.parseInt(projectId))) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：query
     * <p>主要描述：查询指定的已发布的项目
     * <p>访问方式：post
     * <p>URL: /project/query
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId   String 		Y    	NULL  项目Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     project：项目信息
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
	@RequestMapping(value="/query")
	@ResponseBody
	public String queryyProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectId = request.getParameter("projectId");
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		ProjectEntity entity = projectService.queryProject(Integer.parseInt(projectId));
		if (entity == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}else {
			result.put("status", StatusCode.SUCCESS);
			result.put("project", entity);
			return JSON.toJSONString(result);
		}
	}
	
	/**
     * <p>接口名称：update
     * <p>主要描述：更新指定的已发布的项目
     * <p>访问方式：post
     * <p>URL: /project/update
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectJson  String 		Y    	NULL  更新的项目信息
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
	@RequestMapping(value="/update")
	@ResponseBody
	public String updateProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectJson = request.getParameter("projectJson");
		if (projectJson == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		ProjectEntity projectEntity = JSON.parseObject(projectJson,ProjectEntity.class);
		if (projectEntity == null) {
			result.put("status", StatusCode.INFORMATION_PARSE_FAILED);
			return JSON.toJSONString(result);
		}
		if (projectService.updateProject(projectEntity)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
}
