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
	
	@RequestMapping(value="/queryResume")
	@ResponseBody
	public String queryResume(HttpServletRequest request){
		String resumeId = request.getParameter("resumeId");
		return resumeService.queryResume(Integer.parseInt(resumeId));
	}
	
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

	@RequestMapping(value="/updateReOrEva")
	@ResponseBody
	public String updateResumeOrEva(HttpServletRequest request){
		String partName = request.getParameter("partName");
		String json = request.getParameter("json");
		
		if(resumeService.updateSingle(json, partName))
			return "success";
		return "failed";
	}
	
	@RequestMapping(value="/updateParts")
	@ResponseBody
	public String updateParts(HttpServletRequest request){
		String partName = request.getParameter("partName");
		String json = request.getParameter("json");

		return resumeService.updateParts(json, partName);
		
	}
}


