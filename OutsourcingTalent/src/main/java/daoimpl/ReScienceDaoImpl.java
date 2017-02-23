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
	
	@Override
	public void addScience(ReScienceEntity entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void deleteScience(ReScienceEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void updateScience(ReScienceEntity entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public ReScienceEntity findScienceByScienceId(int scienceId) {
		return (ReScienceEntity)getHibernateTemplate().find("from ReScienceEntity e where e.scienceId=?", scienceId).get(0);
	}

	@Override
	public List<?> findSciencesByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReScienceEntity e where e.resumeId=?", resumeId);
	}

}
