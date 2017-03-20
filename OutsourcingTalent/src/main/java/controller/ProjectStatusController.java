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
import service.ProjectStatusService;

@Controller
@RequestMapping(value = "bid")
public class ProjectStatusController {

	@Autowired
	ProjectStatusService projectStatusService;
	
	/**
     * <p>接口名称：apply
     * <p>主要描述：申请指定项目
     * <p>访问方式：post
     * <p>URL: /bid/apply
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId	Integer			Y		null	被申请的项目Id
     * groupId   	Integer 		Y    	 NULL  申请的团队Id
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
	@RequestMapping(value = "/apply")
	@ResponseBody
	public String apply(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (projectStatusService.apply(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：appoint
     * <p>主要描述：根据Id查找群组
     * <p>访问方式：post
     * <p>URL: /bid/appoint
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * projectId	Integer			Y		null操作的项目Id
     * groupId   	Integer 		Y    	NULL  委托的团队Id
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
	@RequestMapping(value = "/appoint")
	@ResponseBody
	public String appoint(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}//参数验证
		
		try {
			String operation = projectStatusService.appoint(Integer.parseInt(projectId), Integer.parseInt(projectId));
			if (operation.equals("success")) {
				result.put("status", StatusCode.SUCCESS);
				return JSON.toJSONString(result);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.put("status", StatusCode.UNKNOW_ERROR);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("status", StatusCode.UNKNOW_ERROR);
		}
		return JSON.toJSONString(result);
	}
	
	
	@RequestMapping(value = "/finish")
	@ResponseBody
	public String finish(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登录验证
    	
		String projectId = request.getParameter("projectId");
		String groupId = request.getParameter("groupId");
		
		if (projectId == null || groupId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (projectStatusService.finish(Integer.parseInt(projectId), Integer.parseInt(groupId))) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
}
