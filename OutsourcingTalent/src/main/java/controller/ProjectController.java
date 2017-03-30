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
     * <p>�ӿ����ƣ�collectStatus
     * <p>��Ҫ�������ж��û��Ƿ��ղ�ָ����Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/collectStatus
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId   	Integer 	Y    	 NULL  ��ĿId
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���2000�������ղأ�4001����Ϊ�ղأ�
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��½��֤
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
     * <p>�ӿ����ƣ�queryCollects
     * <p>��Ҫ��������ѯ��Ŀ�ղ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/queryCollects
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     collects
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��½��֤
		
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
     * <p>�ӿ����ƣ�removeCollect
     * <p>��Ҫ������ȡ����Ŀ�ղ��ղ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/removeCollect
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId   	Integer 	Y    	 NULL  ��ĿId
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
	@RequestMapping(value = "/removeCollect")
	@ResponseBody
	public String removeCollect(HttpSession session,@RequestParam Integer projectId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��½��֤
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
     * <p>�ӿ����ƣ�collect
     * <p>��Ҫ�����������Ŀ�ղ�
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/collect
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId	Integer		Y		null	���ղص���ĿId
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
	@RequestMapping(value = "collect")
	@ResponseBody
	public String collect(HttpSession session,@RequestParam Integer projectId){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��½��֤
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
     * <p>�ӿ����ƣ�search
     * <p>��Ҫ��������ѯ��¼(ģ����ѯ)
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/search
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * text   		String 		Y    	 NULL  ���ҹؼ���
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     list��..
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��½��֤
		
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
     * <p>�ӿ����ƣ�release
     * <p>��Ҫ��������˾������Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/release
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectJson   String 		Y    	NULL  ��Ŀ��Ϣ��JSon
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     projectId:��ĿId
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�list
     * <p>��Ҫ������������ѯ��Ŀ�б�
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/list
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * count	   Integer 		Y    	NULL  	�Ѳ�ѯ�ļ�¼����
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     projects: ��ѯ������Ŀ����Ϣ
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
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
     * <p>�ӿ����ƣ�cancel
     * <p>��Ҫ������ȡ���ѷ�������Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/cancel
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId   String 		Y    	NULL  ��ĿId
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
	@RequestMapping(value = "/cancel")
	@ResponseBody
	public String cacelProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�query
     * <p>��Ҫ��������ѯָ�����ѷ�������Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/query
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectId   String 		Y    	NULL  ��ĿId
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     project����Ŀ��Ϣ
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�update
     * <p>��Ҫ����������ָ�����ѷ�������Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /project/update
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * projectJson  String 		Y    	NULL  ���µ���Ŀ��Ϣ
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
	@RequestMapping(value="/update")
	@ResponseBody
	public String updateProject(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
    	
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
