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
     * <p>�ӿ����ƣ�init
     * <p>��Ҫ��������ʼ����������
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/init
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     resumes:���м���������Ϣ
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�addResume
     * <p>��Ҫ���������Ӽ���
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/addResume
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * resumeJson	String 		Y		null	����������Ϣ
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
	@RequestMapping(value="/addResume")
	@ResponseBody
	public String addResume(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//��¼��֤
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
     * <p>�ӿ����ƣ�addPart
     * <p>��Ҫ��������ָ���������ģ����Ϣ
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/addPart
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * partName		String		Y		null	��ӵļ���ģ�������education��internship��schoolExp��evaluation��science
     * partJson   	String 		Y    	NULL  	��Ӧģ�����Ϣ
     * resumeId		Integer		Y		null	������ļ�����Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�queryResume
     * <p>��Ҫ��������ѯָ������
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/queryResume
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * resumeId		Integer		Y		null	��ѯ�ļ�����Id
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     resume����ѯ���ļ�������Ϣ
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�queryPart
     * <p>��Ҫ��������ѯָ��������ĳ����Ŀ
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/queryPart
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * Id			Integer		Y		null	��ѯ�ļ�����Id
     * isResumeId	Integer		Y		null	�ж��ǲ�ѯ���������ĳ������Ŀ
     * partName		Integer		Y		null	��ѯ����Ŀ������
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     result:��ѯ���
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�deleteResume
     * <p>��Ҫ������ɾ��ָ���ļ���
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/deleteResume
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * resumeId			Integer		Y		null	ɾ���ļ�����Id
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�deletePart
     * <p>��Ҫ������ɾ��ָ������Ŀ��ĳһ����
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/deletePart
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * partName		String		Y		null	������Ŀ������
     * Id			Integer		Y		Null	�ò��ֵ�Id
     * 
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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
     * <p>�ӿ����ƣ�updateParts
     * <p>��Ҫ����������ָ������Ŀ��ĳһ����
     * <p>���ʷ�ʽ��post
     * <p>URL: /resume/updateParts
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * partName		String		Y		null	������Ŀ������
     * json			String		Y		Null	���º������
     * 
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
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
		}//��¼��֤
    	
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


