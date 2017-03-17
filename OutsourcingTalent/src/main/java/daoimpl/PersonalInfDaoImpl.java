package daoimpl;

import java.util.List;

//import entity.PersonalInfEntity;
import org.hibernate.SessionFactory;
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


	public void update(PersonalInfEntity entity) {
//		PersonalInfEntity tempEntity =findByExam(entity.getEmail());
		getHibernateTemplate().update(entity);
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
