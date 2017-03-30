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
import dao.CollectRecruitDao;
import entity.CollectUserRecruitEntity;
import entity.RecruitBean;
import service.RecruitService;

@Controller
@RequestMapping(value = "/recruit")
public class RecruitController {

	@Autowired
	RecruitService recruitService;
	@Autowired
	CollectRecruitDao collectRecruitDao;
	
	/**
     * <p>接口名称：collectStatus
     * <p>主要描述：判断用户是否收藏指定招聘记录
     * <p>访问方式：post
     * <p>URL: /recruit/collectStatus
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * recruitId   	Integer 	Y    	 NULL  招聘记录Id
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
	public String collectStatus(HttpSession session,@RequestParam Integer recruitId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		
		if (collectRecruitDao.findCollectOne(userId, recruitId)!=null) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.NOT_EXIST);
		return JSON.toJSONString(result);
	}
	/**
     * <p>接口名称：queryCollects
     * <p>主要描述：查询职位收藏
     * <p>访问方式：post
     * <p>URL: /recruit/queryCollects
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
		
		List<?> list = collectRecruitDao.findCollectsByUserId(userId);
		List<RecruitBean> collects = new ArrayList<>();
		if (list == null) {
			result.put("status", StatusCode.SUCCESS);
			result.put("collects", collects);
			return JSON.toJSONString(result);
		}
		for(Object o:list) {
			CollectUserRecruitEntity entity = (CollectUserRecruitEntity)o;
			RecruitBean recruitEntity = recruitService.findRecruitByRecruitId(entity.getRecruitId());
			if (recruitEntity != null) {
				collects.add(recruitEntity);
			}
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("collects", collects);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：removeCollect
     * <p>主要描述：取消职位收藏
     * <p>访问方式：post
     * <p>URL: /recruit/removeCollect
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * recruitId   	Integer 	Y    	 NULL  招聘记录Id
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
	public String removeCollect(HttpSession session,@RequestParam Integer recruitId) {
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (collectRecruitDao.deleteCollect(userId, recruitId)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SQL_OP_ERR);
		return JSON.toJSONString(result);
	}
	
	/**
     * <p>接口名称：collect
     * <p>主要描述：添加职位收藏
     * <p>访问方式：post
     * <p>URL: /recruit/collect
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * recruitId   	Integer 	Y    	 NULL  招聘记录Id
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
	public String collect(HttpSession session,@RequestParam Integer recruitId){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}//登陆验证
		if (recruitId == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		CollectUserRecruitEntity entity = new CollectUserRecruitEntity();
		entity.setRecruitId(recruitId);
		entity.setUserId(userId);
		if (collectRecruitDao.addCollect(entity)==-1) {
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
     * <p>URL: /recruit/search
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
	public String search(HttpSession session,@RequestParam String text){
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
		List<RecruitBean> list = recruitService.findByKey(text);
		result.put("status", StatusCode.SUCCESS);
		result.put("list", list);
		return JSON.toJSONString(result);
	}
	/**
     * <p>接口名称：query
     * <p>主要描述：查询记录
     * <p>访问方式：post
     * <p>URL: /recruit/query
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * tag   		String 		Y    	 NULL  感兴趣标签
     * begin		Integer		Y		 0   	已查询记录条数
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     recruits：..
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/query")
	@ResponseBody
 	public String query(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		String tag = request.getParameter("tag");
		String begin = request.getParameter("begin");
		if (begin == null){
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<RecruitBean> list = recruitService.findRecruitsLimit(Integer.parseInt(begin), 10, tag);
		if (list == null) {
			result.put("status", StatusCode.MAX);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("recruits", list);
		return JSON.toJSONString(result);
	}
}
