package service;

import entity.PersonalInfEntity;

public interface PersonalInfService {
	public PersonalInfEntity getPersonByEmail(String email);
	public Boolean updatePersonInformation(PersonalInfEntity entity);
	public PersonalInfEntity getPersonById(String id);
}
