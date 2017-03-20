package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.ResumeDao;
import entity.ResumeEntity;
import service.ResumeService;

@Controller
@RequestMapping(value="/resume")
public class ResumeController {

	@Autowired
	ResumeService resumeService;
	@Autowired
	ResumeDao resumeDao;
	
	/**
     * <p>接口名称：init
     * <p>主要描述：初始化简历界面
     * <p>访问方式：post
     * <p>URL: /resume/init
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     resumes:所有简历基本信息
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/init")
	@ResponseBody
	public String init(HttpServletRequest request,HttpSession session) {
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
    	List<?> list = resumeDao.findResume(userId);
    	if (list == null) {
    		result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
    	result.put("status", StatusCode.SUCCESS);
    	result.put("resumes", list);
		return JSON.toJSONString(result);
	}
	/**
     * <p>接口名称：addResume
     * <p>主要描述：增加简历
     * <p>访问方式：post
     * <p>URL: /resume/addResume
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * resumeJson	String 		Y		null	简历基本信息
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
	@RequestMapping(value="/addResume")
	@ResponseBody
	public String addResume(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
		String resumeJson = request.getParameter("resumeJson");
		if (resumeJson == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		ResumeEntity resumeEntity = JSON.parseObject(resumeJson,ResumeEntity.class);
		if (resumeEntity == null) {
			result.put("status", StatusCode.JSON_PARSE_ERROR);
			return JSON.toJSONString(result);
		}
		Integer resumeId = resumeDao.saveResume(resumeEntity);
		if (resumeId < 1) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("resumeId", resumeId);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：addPart
     * <p>主要描述：向指定简历添加模块信息
     * <p>访问方式：post
     * <p>URL: /resume/addPart
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * partName		String		Y		null	添加的简历模块的名字education，internship，schoolExp，evaluation，science
     * partJson   	String 		Y    	NULL  	对应模块的信息
     * resumeId		Integer		Y		null	添加往的简历的Id
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
	@RequestMapping(value = "/addPart")
	@ResponseBody
	public String addPartResume(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String partName = request.getParameter("partName");
		String json = request.getParameter("partJson");
		String resumeId = request.getParameter("resumeId");
		
		if (partName == null||json == null || resumeId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		Integer returnStr;
		
		switch (partName) {
		case "education":
			returnStr = resumeService.addResumeInformations("education", json,resumeId);
			if (returnStr != null) {
				result.put("status", StatusCode.SUCCESS);
			}else {
				result.put("status", StatusCode.UNKNOW_ERROR);
			}
			break;
		case "internship":
			returnStr = resumeService.addResumeInformations("internship", json,resumeId);
			if (returnStr != null) {
				result.put("status", StatusCode.SUCCESS);
			}else {
				result.put("status", StatusCode.UNKNOW_ERROR);
			}
			break;
		case "schoolExp":
			returnStr = resumeService.addResumeInformations("schoolExp", json,resumeId);
			if (returnStr != null) {
				result.put("status", StatusCode.SUCCESS);
			}else {
				result.put("status", StatusCode.UNKNOW_ERROR);
			}
			break;
		case "evaluation":
			returnStr = resumeService.addResumeInformations("evaluation", json,resumeId);
			if (returnStr != null) {
				result.put("status", StatusCode.SUCCESS);
			}else {
				result.put("status", StatusCode.UNKNOW_ERROR);
			}
			break;
		case "science":
			returnStr = resumeService.addResumeInformations("science", json,resumeId);
			if (returnStr != null) {
				result.put("status", StatusCode.SUCCESS);
			}else {
				result.put("status", StatusCode.UNKNOW_ERROR);
			}
			break;

		default:
			result.put("status", StatusCode.PARAMETER_ERROR);
			break;
		}
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：queryResume
     * <p>主要描述：查询指定简历
     * <p>访问方式：post
     * <p>URL: /resume/queryResume
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * resumeId		Integer		Y		null	查询的简历的Id
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     resume：查询到的简历的信息
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
	@RequestMapping(value="/queryResume")
	@ResponseBody
	public String queryResume(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String resumeId = request.getParameter("resumeId");
		if (resumeId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		ResumeEntity resumeEntity = resumeService.queryResume(Integer.parseInt(resumeId));
		if (resumeEntity == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}else{
			result.put("status", StatusCode.SUCCESS);
			result.put("resume", resumeEntity);
			return JSON.toJSONString(result);
		}
	}
	
	/**
     * <p>接口名称：queryPart
     * <p>主要描述：查询指定简历的某个项目
     * <p>访问方式：post
     * <p>URL: /resume/queryPart
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * Id			Integer		Y		null	查询的简历的Id
     * isResumeId	Integer		Y		null	判断是查询简历大项还是某个子项目
     * partName		Integer		Y		null	查询的项目的名称
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     result:查询结果
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
	@RequestMapping(value="/queryPart")
	@ResponseBody
	public String queryPart(HttpServletRequest request,HttpSession session) {
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String Id = request.getParameter("Id");
		String isResumeId = request.getParameter("isResumeId");
		String partName = request.getParameter("partName");
		
		if (isResumeId == null || Id == null || partName == null ) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (isResumeId.equals("1")) {
			List<?> list = resumeService.queryPartByResumeId(partName, Integer.parseInt(Id));
			if (list == null) {
				result.put("status", StatusCode.SQL_OP_ERR);
				return JSON.toJSONString(result);
			}
			result.put("status", StatusCode.SUCCESS);
			result.put("result", list);
			return JSON.toJSONString(result);
		}else if (isResumeId.equals("0")) {
			Object object = resumeService.queryPartByMainId(partName, Integer.parseInt(Id));
			if (object == null) {
				result.put("status", StatusCode.SQL_OP_ERR);
				return JSON.toJSONString(result);
			}
			result.put("status", StatusCode.SUCCESS);
			result.put("result", object);
			return JSON.toJSONString(result);
		}else {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
	}

	/**
     * <p>接口名称：deleteResume
     * <p>主要描述：删除指定的简历
     * <p>访问方式：post
     * <p>URL: /resume/deleteResume
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * resumeId			Integer		Y		null	删除的简历的Id
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value="/deleteResume")
	@ResponseBody
	public String deleteResume(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String resumeId = request.getParameter("resumeId");
		if (resumeId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (resumeService.deleteResume(Integer.parseInt(resumeId))) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}else{
			result.put("status", StatusCode.UNKNOW_ERROR);
			return JSON.toJSONString(result);
		}
	}
	
	/**
     * <p>接口名称：deletePart
     * <p>主要描述：删除指定的项目的某一部分
     * <p>访问方式：post
     * <p>URL: /resume/deletePart
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * partName		String		Y		null	更新项目的名字
     * Id			Integer		Y		Null	该部分的Id
     * 
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value="/deletePart")
	@ResponseBody
	public String deletePart(HttpServletRequest request,HttpSession session) {
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String partName = request.getParameter("partName");
		String id = request.getParameter("Id");
		if (partName == null || id == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (resumeService.deletePartById(Integer.parseInt(id), partName)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：updateParts
     * <p>主要描述：更新指定的项目的某一部分
     * <p>访问方式：post
     * <p>URL: /resume/updateParts
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * partName		String		Y		null	更新项目的名字
     * json			String		Y		Null	更新后的内容
     * 
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value="/updateParts")
	@ResponseBody
	public String updateParts(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String partName = request.getParameter("partName");
		String json = request.getParameter("json");
		if (partName == null || json == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}

		if(resumeService.updatePart(json, partName)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}else{
			result.put("status", StatusCode.UNKNOW_ERROR);
			return JSON.toJSONString(result);
		}
		
	}
}


