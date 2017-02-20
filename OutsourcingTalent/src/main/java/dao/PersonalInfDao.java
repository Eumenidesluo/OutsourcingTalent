package dao;

import entity.PersonalInfEntity;

/**
 * Created by Eumenides on 2017/2/19.
 */

public interface PersonalInfDao {
	public void update(PersonalInfEntity entity);
	public PersonalInfEntity findByExam(String email);

}
