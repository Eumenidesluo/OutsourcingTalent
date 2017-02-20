package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import component.ServiceException;
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

    @RequestMapping(value="/register",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String load(HttpServletRequest request) throws ParseException {
        String action = request.getParameter("action");
        String password=request.getParameter("password");
        System.out.println("-----r----"+action);

        if("register".equals(action)) {
            //娉ㄥ唽
            String email = request.getParameter("email");
            if (userDao.isEmailUnique(email)==1){
                RegisterValidate registerValidate=(RegisterValidate) SpringContextHolder.getBean("registerValidate");
                try {
                    registerValidate.processregister(password,email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
                return "the email has been registered";
        }
        else if("activate".equals(action)) {
            //婵�娲�
            String email = request.getParameter("email");//鑾峰彇email
            String validateCode = request.getParameter("validateCode");//婵�娲荤爜

            try {
                //璋冪敤婵�娲绘柟娉�
                service.processActivate(email,validateCode);
            } catch (ServiceException e) {
                request.setAttribute("message" , e.getMessage());
            }catch (ParseException e){
                e.printStackTrace();
            }

        }
        return "success";
    }
}
