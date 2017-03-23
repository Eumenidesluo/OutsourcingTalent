package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.CompanyDao;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	@Autowired
	private CompanyDao companyDao;
	
	/**
     * <p>接口名称：search
     * <p>主要描述：查询公司(模糊查询)
     * <p>访问方式：post
     * <p>URL: /company/search
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * text   		String 		Y    	 NULL  查找关键字
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     companies：..
     * }
     * </pre>
     * <p>修改者:陈琦
     * 
     */
	@RequestMapping(value = "/search")
	@ResponseBody
	public String search(@RequestParam String text,HttpSession session) {
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
		
		List<?> list = companyDao.findCompanyesByKey(text);
		if (list == null) {
			result.put("status", StatusCode.SQL_OP_ERR);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.SUCCESS);
		result.put("companies", list);
		return JSON.toJSONString(result);
	}
}
