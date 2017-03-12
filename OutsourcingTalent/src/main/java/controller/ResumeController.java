package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import service.ResumeService;

@Controller
@RequestMapping(value="/Resume")
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
	 * 
	 * @param request
	 * 需要添加的部分的名字如education，internship等，对应的list<?>的json，添入的resume的Id
	 * @return 对应生成的Id字符串，以 " "作为分隔符
	 */
	@RequestMapping(value = "/addPart")
	@ResponseBody
	public String addPartResume(HttpServletRequest request){

		String partName = request.getParameter("partName");
		String json = request.getParameter("partJson");
		String resumeId = request.getParameter("resumeId");
		
		if (partName == null||json == null || resumeId == null) {
			return "Invalid parameter";
		}
		Map<String, String> reportMap = new HashMap<>();
		String returnStr = "";
		
		switch (partName) {
		case "education":
			returnStr = resumeService.addResumeInformations("education", json,resumeId);
			reportMap.put("education", returnStr);
			break;
		case "internship":
			returnStr = resumeService.addResumeInformations("internship", json,resumeId);
			reportMap.put("internship", returnStr);
			break;
		case "schoolExp":
			returnStr = resumeService.addResumeInformations("schoolExp", json,resumeId);
			reportMap.put("schoolExp", returnStr);
			break;
		case "evaluation":
			returnStr = resumeService.addResumeInformations("evaluation", json,resumeId);
			reportMap.put("evaluation", returnStr);
			break;
		case "science":
			returnStr = resumeService.addResumeInformations("science", json,resumeId);
			reportMap.put("science", returnStr);
			break;

		default:
			returnStr = "Unknown parameter";
			break;
		}
		return returnStr;
	}
	
	/**
	 * 
	 * @param request 需要参数，简历Id
	 * 
	 * @return 对应简历基本信息的json
	 */
	@RequestMapping(value="/queryResume")
	@ResponseBody
	public String queryResume(HttpServletRequest request){
		String resumeId = request.getParameter("resumeId");
		return resumeService.queryResume(Integer.parseInt(resumeId));
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


