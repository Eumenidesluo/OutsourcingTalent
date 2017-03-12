package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.CoRecruitDao;

@Controller
@RequestMapping(value = "/recruit")
public class CompanyRecruitController {

	@Autowired
	CoRecruitDao coRecruitDao;
	
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
     * <pre>
     * 
     */
	@RequestMapping(value = "/query")
	public String query(HttpServletRequest request,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		String userId = session.getAttribute("userId").toString();
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		String tag = request.getParameter("tag");
		String begin = request.getParameter("begin");
		if (tag == null || begin == null){
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		List<?> list = coRecruitDao.findRecruitsLimit(Integer.parseInt(begin), 10, tag);
		if (list == null) {
			result.put("status", StatusCode.MAX);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("recruits", list);
		return JSON.toJSONString(result);
	}
}
