package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import entity.PersonalInfEntity;
import service.PersonalInfService;

/**
 * Created by Eumenides on 2017/2/19.
 */
@Controller
public class PersonalController {
    @Autowired
    PersonalInfService personService;
	@RequestMapping(value = "/updatePersonal")
    @ResponseBody
    public String execute(HttpServletRequest request){
    	String personJson = request.getParameter("person");
    	if (personService.updatePersonInformation(personJson)) {
    		return "Update person information success";
		}else
			return "Information not exist";
    }
	
	@RequestMapping(value = "/queryPersonal")
	@ResponseBody
	public String queryPersonal(String email){
		PersonalInfEntity entity = personService.getPersonByEmail(email);
		if (entity == null) {
			return "Information not exist";
		}else
			return JSON.toJSONString( entity );
	}
}
