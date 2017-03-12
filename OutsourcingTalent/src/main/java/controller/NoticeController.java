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
import dao.NoticeDao;
import entity.NoticeEntity;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

	@Autowired
	NoticeDao noticeDao;
	
	/**
     * <p>接口名称：query
     * <p>主要描述：查询记录
     * <p>访问方式：post
     * <p>URL: /notice/query
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * userId   	String 		Y    	 NULL  用户Id
     * number		Integer		Y		 2   	查询数量 -1:查询全部通知
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     notices：..
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
	@RequestMapping(value = "/query")
	@ResponseBody
	public String query(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		String personId = request.getParameter("userId");
		String number = request.getParameter("number");
		if (personId == null || number == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		String userId = session.getAttribute("userId").toString();
		if (userId == null||userId != personId) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		if (Integer.parseInt(number) == -1) {
			List<?> notices = noticeDao.queryByUserId(userId);
			result.put("status", StatusCode.SUCCESS);
			result.put("notices", notices);
			return JSON.toJSONString(result);
		}else {
			List<NoticeEntity> notices = noticeDao.queryByUserId(userId,Integer.parseInt(number));
			result.put("status", StatusCode.SUCCESS);
			result.put("notices", notices);
			return JSON.toJSONString(result);
		}		
	}
}
