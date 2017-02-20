package controller;

import dao.UserDao;
import entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import serviceimpl.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Eumenides on 2017/2/18.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        String email;
        String password;
        email = request.getParameter("email");
        password = request.getParameter("password");
        UserDao userDao = (UserDao) SpringContextHolder.getBean("userDao");

        UserEntity userEntity = userDao.findByEmail(email);
        if (userEntity == null||userEntity.getStatus()==0)
            return "not exist";
        else {
            if (userEntity.getPassword().equals(password)) {
                return "success";
            }
            return "password or username not exist";
        }
    }
}
