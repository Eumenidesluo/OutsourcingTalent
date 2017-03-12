package controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.ServiceException;
import component.StatusCode;
import dao.UserDao;
import service.RegisterValidate;
import serviceimpl.SpringContextHolder;

/**
 * Created by Eumenides on 2017/2/18.
 */
@Controller
public class RegisterController {
    @Resource
    private RegisterValidate service;
    @Autowired
    private UserDao userDao;

    /**
     * <p>接口名称：register
     * <p>主要描述：注册
     * <p>访问方式：post
     * <p>URL: /register
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * email   		String 		Y    	 NULL  用户名
     * password		String		Y		 NULL  密码
     * action 		Integer		Y 		0		0：注册，1激活
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
     * <pre>
     * 
     */
    @RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String load(HttpServletRequest request) throws ParseException {
        String action = request.getParameter("action");
        String password = request.getParameter("password");
        Map<String, Object> result = new HashMap<>();
        System.out.println("-----r----"+action);

        if("register".equals(action)) {
            String email = request.getParameter("email");
            if (userDao.isEmailUnique(email)==1){
                RegisterValidate registerValidate=(RegisterValidate) SpringContextHolder.getBean("registerValidate");
                try {
                    registerValidate.processregister(password,email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
            	result.put("status", StatusCode.REPEAT_REGISTER);
            	return JSON.toJSONString(result);
            }
                
        }
        else if("activate".equals(action)) {
            String email = request.getParameter("email");
            String validateCode = request.getParameter("validateCode");

            try {
                service.processActivate(email,validateCode);
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
            }catch (ParseException e){
                result.put("status", StatusCode.UNKNOW_ERROR);
                return JSON.toJSONString(result);
            }

        }
        result.put("status", StatusCode.SUCCESS);
        return JSON.toJSONString(result);
    }
}
