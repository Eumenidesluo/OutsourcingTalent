package dao;

import entity.PersonalInfEntity;

/**
 * Created by Eumenides on 2017/2/19.
 */

public interface PersonalInfDao {
	public Boolean update(PersonalInfEntity entity);
	public PersonalInfEntity findByEmail(String email);

}
