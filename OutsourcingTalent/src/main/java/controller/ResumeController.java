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
import entity.ResumeEntity;
import service.ResumeService;

@Controller
@RequestMapping(value="/resume")
public class ResumeController {

	@Autowired
	ResumeService resumeService;
	
	/**
	 * 
	 * @param request 存放参数为resume，education，internship。schoolexp，evaluation，science等类的json
	 * resume不能为null，其它属性可以为null
	 * @return 各部分的生成的Id的json
	 */
	@RequestMapping(value="/addResume")
	@ResponseBody
	public String addResume(HttpServletRequest request){
		String resumeJson = request.getParameter("resume");
		String educationJson = request.getParameter("education");
		String internshipJson = request.getParameter("internship");
		String schoolExpJson = request.getParameter("schoolExp");
		String evaluationJson = request.getParameter("evaluation");
		String scienceJson = request.getParameter("science");
		
		Map<String, String> reportMap = new HashMap<>();
		String returnStr = "";
		String resumeId="";
		
		if(resumeJson != null){
			resumeId = resumeService.addResumeInformations("resume", resumeJson);
			reportMap.put("resume", resumeId);
			returnStr = "";
		}
		if (educationJson != null) {
			returnStr = resumeService.addResumeInformations("education", educationJson,resumeId);
			reportMap.put("education", returnStr);
			returnStr = "";
		}
		if (educationJson != null) {
			returnStr = resumeService.addResumeInformations("internship", internshipJson,resumeId);
			reportMap.put("internship", returnStr);
			returnStr = "";
		}
		if (educationJson != null) {
			returnStr = resumeService.addResumeInformations("schoolExp", schoolExpJson,resumeId);
			reportMap.put("schoolExp", returnStr);
			returnStr = "";
		}
		if (educationJson != null) {
			returnStr = resumeService.addResumeInformations("evaluation", evaluationJson,resumeId);
			reportMap.put("evaluation", returnStr);
			returnStr = "";
		}
		if (educationJson != null) {
			returnStr = resumeService.addResumeInformations("science", scienceJson,resumeId);
			reportMap.put("science", returnStr);
			returnStr = "";
		}
		
		return JSON.toJSONString(reportMap);
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
		String returnStr = "";
		
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
	 * @param request 根据isResumeId判断是查询单个部分信息还是全部部分信息，Id可以解读为resumeId或者部分Id
	 * @return 查询到的List<?>的Json或错误信息
	 */
	@RequestMapping(value="/queryPart")
	@ResponseBody
	public String queryPart(HttpServletRequest request){
		String Id = request.getParameter("Id");
		String isResumeId = request.getParameter("isResumeId");
		String partName = request.getParameter("partName");
		
		if (isResumeId == null || Id == null || partName == null ) {
			return "Invalid parameter";
		}
		if (isResumeId.equals("1")) {
			return resumeService.queryPartByResumeId(partName, Integer.parseInt(Id));
		}else if (isResumeId.equals("0")) {
			return resumeService.queryPartByMainId(partName, Integer.parseInt(Id));
		}else {
			return "Request Parameter error";
		}
		
	}

	/**
	 * @param request 根据resumeId删除对应的简历信息
	 * @return "success" 删除成功，其它信息 删除失败
	 */
	@RequestMapping(value="/deleteResume")
	@ResponseBody
	public String deleteResume(HttpServletRequest request){
		String resumeId = request.getParameter("resumeId");
		if (resumeId == null) {
			return "Invalid parameter";
		}
		if (resumeService.deleteResume(Integer.parseInt(resumeId))) {
			return "success";
		}else
			return "Unknown error";
	}
	
	/**
	 * 
	 * @param request 需要删除的part的Ids
	 * @return 返回String类型，对应Id删除成功则对应位置为S，否则为F
	 */
	@RequestMapping(value="/deletePart")
	@ResponseBody
	public String deletePart(HttpServletRequest request){
		String partName = request.getParameter("partName");
		String ids = request.getParameter("Ids");
		String[] idArray = ids.split(" ");
		String retrunValue="";
		for(String id:idArray){
			if(resumeService.deletePartById(Integer.parseInt(id), partName))
				retrunValue += "S";
			else {
				retrunValue += "F";
			}
		}
		return retrunValue;
	}

	/**
	 * 
	 * @param request update resume或evaluation等single部分
	 * @return "success" or "failed"
	 */
	@RequestMapping(value="/updateReOrEva")
	@ResponseBody
	public String updateResumeOrEva(HttpServletRequest request){
		String partName = request.getParameter("partName");
		String json = request.getParameter("json");
		
		if(resumeService.updateSingle(json, partName))
			return "success";
		return "failed";
	}
	
	/**
	 * 
	 * @param request update 允许多例的部分，参数为需要更新的partName
	 * @return 返回String类型，对应Id更新成功则对应位置为S，否则为F
	 */
	@RequestMapping(value="/updateParts")
	@ResponseBody
	public String updateParts(HttpServletRequest request){
		String partName = request.getParameter("partName");
		String json = request.getParameter("json");

		return resumeService.updateParts(json, partName);
		
	}
}


