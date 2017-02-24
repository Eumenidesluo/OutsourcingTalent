package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReScienceDao;
import entity.ReScienceEntity;

public class ReScienceDaoImpl extends HibernateDaoSupport implements ReScienceDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Integer addScience(ReScienceEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public Boolean deleteScience(ReScienceEntity entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch( Exception e) {
			return false;
		}
		return true;
	}

	public Boolean updateScience(ReScienceEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public ReScienceEntity findScienceByScienceId(int scienceId) {
		return (ReScienceEntity)getHibernateTemplate().find("from ReScienceEntity e where e.scienceId=?", scienceId).get(0);
	}

	public List<?> findSciencesByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReScienceEntity e where e.resumeId=?", resumeId);
	}

}
