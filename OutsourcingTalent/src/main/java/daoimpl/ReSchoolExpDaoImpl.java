package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReSchoolExpDao;
import entity.ReSchoolExpEntity;

public class ReSchoolExpDaoImpl extends HibernateDaoSupport implements ReSchoolExpDao {
	
	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	@Override
	public void addSchoolExp(ReSchoolExpEntity entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void deleteSchoolExp(ReSchoolExpEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void updateSchoolExp(ReSchoolExpEntity entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public ReSchoolExpEntity findExpBySchoolExpId(int schoolExpId) {
		return (ReSchoolExpEntity)getHibernateTemplate().find("from ReSchoolExpEntity e where e.schoolExpId=?", schoolExpId).get(0);
	}

	@Override
	public List<?> findExpsByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReSchoolExpEntity e where e.resumeId=?", resumeId);
	}

}
