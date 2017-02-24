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
	
	public Integer addSchoolExp(ReSchoolExpEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public Boolean deleteSchoolExp(ReSchoolExpEntity entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Boolean updateSchoolExp(ReSchoolExpEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public ReSchoolExpEntity findExpBySchoolExpId(int schoolExpId) {
		return (ReSchoolExpEntity)getHibernateTemplate().find("from ReSchoolExpEntity e where e.schoolExpId=?", schoolExpId).get(0);
	}

	public List<?> findExpsByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReSchoolExpEntity e where e.resumeId=?", resumeId);
	}

}
