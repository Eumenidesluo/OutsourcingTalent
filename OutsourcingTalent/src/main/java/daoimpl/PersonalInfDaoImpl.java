package daoimpl;

import java.util.List;

//import entity.PersonalInfEntity;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.PersonalInfDao;
import entity.PersonalInfEntity;



/**
 * Created by Eumenides on 2017/2/19.
 */
public class PersonalInfDaoImpl extends HibernateDaoSupport implements PersonalInfDao {
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


	public Boolean update(PersonalInfEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public PersonalInfEntity findByEmail(String  email) {
		List<?> list = getHibernateTemplate().find("from PersonalInfEntity as e where e.email=?",email);
		if (list.size() == 0) {
			return null;
		}
		return (PersonalInfEntity)list.get(0);
//		System.out.println(list);
	}

}
