package serviceimpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PersonalInfDao;
import entity.PersonalInfEntity;
import service.PersonalInfService;
@Service("personalInfService")
public class PersonalInfServiceImpl implements PersonalInfService{

	@Autowired
	PersonalInfDao personalInfDao;
	public PersonalInfDao getPersonalInfDao() {
		return personalInfDao;
	}
	public void setPersonalInfDao(PersonalInfDao personalInfDao) {
		this.personalInfDao = personalInfDao;
	}
	
	public PersonalInfEntity getPersonByEmail(String email) {
		PersonalInfEntity entity = personalInfDao.findByExam(email);
		return entity;
	}
	
	public Boolean updatePersonInformation(Map<String, String> informations) {
		PersonalInfEntity entity = getPersonByEmail(informations.get("email"));
		if (entity == null) {
			return false;
		}
		generateEntity(entity, informations);
		
		return true;
	}
	
	private void generateEntity( PersonalInfEntity entity,Map<String, String> informations){
		entity.setName(informations.get("name"));
		entity.setAddress(informations.get("address"));
		entity.setEducation(informations.get("education"));
		entity.setMajor(informations.get("major"));
		entity.setPhoneNumber(informations.get("phone"));
		entity.setAddress(informations.get("sex"));
		entity.setSchool(informations.get("school"));
		entity.setGraduationTime(informations.get("graduationTime"));
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		try {      
			java.util.Date date = bartDateFormat.parse(informations.get("birthday"));  
			java.sql.Date sqldate = new Date(date.getTime());
			entity.setBirthday(sqldate);
		}catch(Exception e){
			e.printStackTrace();
		}
			
	
	}
	@Override
	public Boolean updatePersonInformation(String json) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
