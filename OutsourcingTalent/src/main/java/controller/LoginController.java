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
import dao.CoRecruitDao;
import dao.NoticeDao;
import dao.UserDao;
import entity.NoticeEntity;
import entity.PersonalInfEntity;
import entity.UserEntity;
import service.PersonalInfService;
import serviceimpl.SpringContextHolder;

/**
 * Created by Eumenides on 2017/2/18.
 */


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@Autowired
	PersonalInfService personalInfService;
	@Autowired
	CoRecruitDao coRecruitDao;
	@Autowired
	NoticeDao noticeDao;
	
	/**
	 * <p>接口名称：login
	 * <p>主要描述：用户登录
	 * <p>访问方式：post
	 * <p>URL: /login/login
	 * <p>参数说明:
	 * <pre>
	 * |名称              |类型         |是否必须   |默认值    |说明
	 * email   		String 		Y    	 NULL  用户名
	 * password  	String 		Y     	NULL   用户密码
	 * </pre>
	 * <p>返回数据:JSON
	 * <pre>
	 * {
	 *     status: ${StatusCode}, 参见状态码表
	 *     userId: ${userId}
	 * }
	 * </pre>
	 * <p>修改者:陈琦
	 * <pre>
	 * 
	 */
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request,HttpSession session) {
        String email;
        String password;
        Map<String ,Object> result = new HashMap<>();
        email = request.getParameter("email");
        password = request.getParameter("password");
        UserDao userDao = (UserDao) SpringContextHolder.getBean("userDao");
        
        UserEntity userEntity = userDao.findByEmail(email);
        if (userEntity == null||userEntity.getStatus()==0)
            result.put("status", StatusCode.NOT_EXIST);
        else {
            if (userEntity.getPassword().equals(password)) {
            	session.setAttribute("userId", userEntity.getId());
            	result.put("status", StatusCode.SUCCESS);
            	result.put("userId", userEntity.getId());
            }else
            	result.put("status", StatusCode.PASSWORD_OR_EMAIL_WRONG);
        }
        return JSON.toJSONString(result);
    }
    
    /**
     * <p>接口名称：information
     * <p>主要描述：登录成功后获取信息
     * <p>访问方式：post
     * <p>URL: /login/getInformation
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * tag   		String 		Y    	 NULL  用户名感兴趣标签
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     *     recruits: ${userId}..
     *     notice: ${notice}..
     *     personal: ${personal}
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
    @RequestMapping(value = "/getInformation")
    @ResponseBody
    public String loginGetInformation(HttpServletRequest request,HttpSession session){
    	Map<String, Object> result = new HashMap<>();
    	Integer userId = (Integer) session.getAttribute("userId");
    	if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
    	String tag = request.getParameter("tag");
    	if (tag == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
    	PersonalInfEntity personalInfEntity = personalInfService.getPersonById(userId.toString());
    	List<?> recruitEntitys = coRecruitDao.findRecruitsLimit(0, 5,tag);
    	List<NoticeEntity> notice = noticeDao.queryByUserId(userId.toString(),2);
    	result.put("status", StatusCode.SUCCESS);
    	result.put("recruits", recruitEntitys);
    	result.put("personal", personalInfEntity);
    	result.put("notices", notice);
    	return JSON.toJSONString(result);
    }
}
