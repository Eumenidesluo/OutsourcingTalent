package controller;

import java.util.ArrayList;
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
import dao.CollectProjectDao;
import entity.CollectUserProjectEntity;
import entity.ProjectEntity;
import service.ProjectService;

@Controller
@RequestMapping(value = "/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	@Autowired
	CollectProjectDao collectProjectDao;
	
	/**
     * <p>接口名称：collectStatus
     * <p>主要描述：判断用户是否收藏指定项目
     * <p>访问方式：post
     * <p>URL: /project/collectStatus
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId   	Integer 	Y    	 NULL  项目Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表（2000代表以收藏，4001代表为收藏）
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/collectStatus")
	@ResponseBody
	public String collectStatus(HttpSession session,@RequestParam Integer projectId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (collectProjectDao.findCollectOne(userId, projectId)!=null) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.NOT_EXIST);
		return JSON.toJSONString(result);
	}
	/**
     * <p>接口名称：queryCollects
     * <p>主要描述：查询项目收藏
     * <p>访问方式：post
     * <p>URL: /project/queryCollects
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     collects
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/queryCollects")
	@ResponseBody
	public String queryCollects(HttpSession session) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		
		List<?> list = collectProjectDao.findCollectsByUserId(userId);
		if (list == null) {
			list = new ArrayList<>();
			result.put("status", StatusCode.SUCCESS);
			result.put("collects", list);
			return JSON.toJSONString(result);
		}
		List<ProjectEntity> collects = new ArrayList<>();
		for(Object o:list) {
			CollectUserProjectEntity entity = (CollectUserProjectEntity)o;
			ProjectEntity projectEntity = projectService.queryProject(entity.getProjectId());
			if (projectEntity != null) {
				collects.add(projectEntity);
			}
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("collects", collects);
		return JSON.toJSONString(result);
	}
	/**
     * <p>接口名称：removeCollect
     * <p>主要描述：取消项目收藏收藏
     * <p>访问方式：post
     * <p>URL: /project/removeCollect
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId   	Integer 	Y    	 NULL  项目Id
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
	@RequestMapping(value = "/removeCollect")
	@ResponseBody
	public String removeCollect(HttpSession session,@RequestParam Integer projectId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (collectProjectDao.deleteCollect(userId, projectId)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SQL_OP_ERR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：collect
     * <p>主要描述：添加项目收藏
     * <p>访问方式：post
     * <p>URL: /project/collect
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId	Integer		Y		null	被收藏的项目Id
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
	@RequestMapping(value = "collect")
	@ResponseBody
	public String collect(HttpSession session,@RequestParam Integer projectId){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (projectId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		CollectUserProjectEntity entity = new CollectUserProjectEntity();
		entity.setProjectId(projectId);
		entity.setUserId(userId);
		if (collectProjectDao.addCollect(entity)==-1) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		return JSON.toJSONString(result);
	}
	/**
     * <p>接口名称：search
     * <p>主要描述：查询记录(模糊查询)
     * <p>访问方式：post
     * <p>URL: /project/search
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * text   		String 		Y    	 NULL  查找关键字
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     list：..
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/search")
	@ResponseBody
	public String search (HttpSession session,@RequestParam String text){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		
		if (text == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<?> list = projectService.findProjectByKey(text);
		if (list == null) {
			list = new ArrayList<>();
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("list", list);
		return JSON.toJSONString(result);
	}
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
     * <p>接口名称：list
     * <p>主要描述：批量查询项目列表
     * <p>访问方式：post
     * <p>URL: /project/list
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * count	   Integer 		Y    	NULL  	已查询的记录数量
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     projects: 查询到的项目的信息
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/list")
	@ResponseBody
	public String list(HttpServletRequest request,HttpSession session) {
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	String begin = request.getParameter("count");
    	if (begin == null) {
    		result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
    	
    	List<?> projectList = projectService.queryManyProjects(Integer.parseInt(begin), 10);
    	if (projectList == null) {
			projectList = new ArrayList<>();
		}
    	result.put("status", StatusCode.SUCCESS);
    	result.put("projects", projectList);
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
