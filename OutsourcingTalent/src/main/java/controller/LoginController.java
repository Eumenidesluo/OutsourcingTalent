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
	 * <p>�ӿ����ƣ�login
	 * <p>��Ҫ�������û���¼
	 * <p>���ʷ�ʽ��post
	 * <p>URL: /login/login
	 * <p>����˵��:
	 * <pre>
	 * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
	 * email   		String 		Y    	 NULL  �û���
	 * password  	String 		Y     	NULL   �û�����
	 * </pre>
	 * <p>��������:JSON
	 * <pre>
	 * {
	 *     status: ${StatusCode}, �μ�״̬���
	 *     userId: ${userId}
	 * }
	 * </pre>
	 * <p>�޸���:����
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
     * <p>�ӿ����ƣ�information
     * <p>��Ҫ��������¼�ɹ����ȡ��Ϣ
     * <p>���ʷ�ʽ��post
     * <p>URL: /login/getInformation
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * tag   		String 		Y    	 NULL  �û�������Ȥ��ǩ
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     recruits: ${userId}..
     *     notice: ${notice}..
     *     personal: ${personal}
     * }
     * </pre>
     * <p>�޸���:����
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
