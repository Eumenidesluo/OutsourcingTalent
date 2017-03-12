package service;

import entity.PersonalInfEntity;

public interface PersonalInfService {
	public PersonalInfEntity getPersonByEmail(String email);
	public Boolean updatePersonInformation(String json);
	public PersonalInfEntity getPersonById(String id);
}
