package service;

import component.ServiceException;
import org.springframework.expression.ParseException;

/**
 * Created by Eumenides on 2017/2/18.
 */
public interface RegisterValidate {

    public void processregister(String password,String email);
    public void processActivate(String email , String validateCode)throws ServiceException, ParseException;
}
