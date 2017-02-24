package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReEducationDao;
import entity.ReEducationEntity;

public class ReEducationDaoImpl extends HibernateDaoSupport implements ReEducationDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	public Integer addEducation(ReEducationEntity educationEntity) {
		return (Integer)getHibernateTemplate().save(educationEntity);
	}

	public Boolean deleteEducation(ReEducationEntity educationEntity) {
		try {
			getHibernateTemplate().delete(educationEntity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<?> findEducationsByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReEducationEntity e where e.resumeId=?", resumeId);
		
	}

	public Boolean updateEducation(ReEducationEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	public ReEducationEntity findByEducationId(int educationId) {
		return (ReEducationEntity)getHibernateTemplate().find("from ReEducationEntity e where e.educationId=?",educationId ).get(0);
	}

}
