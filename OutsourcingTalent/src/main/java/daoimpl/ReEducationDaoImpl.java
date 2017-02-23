package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReEducationDao;
import entity.ReEducationEntity;

public class ReEducationDaoImpl extends HibernateDaoSupport implements ReEducationDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	public void addEducation(ReEducationEntity educationEntity) {
		getHibernateTemplate().save(educationEntity);
	}

	public void deleteEducation(ReEducationDao educationEntityDao) {
		getHibernateTemplate().delete(educationEntityDao);
	}

	public List<?> findEducationsByResumeId(int reusmeId) {
		return getHibernateTemplate().find("from ReEducationEntity e where e.resumeId=?", reusmeId);
		
	}

	public void updateEducation(ReEducationEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public ReEducationEntity findByEducationId(int educationId) {
		return (ReEducationEntity)getHibernateTemplate().find("from ReEducationEntity e where e.educationId=?",educationId ).get(0);
	}

}
