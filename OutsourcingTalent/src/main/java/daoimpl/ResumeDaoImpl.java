package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ResumeDao;
import entity.ResumeEntity;

public class ResumeDaoImpl extends HibernateDaoSupport implements ResumeDao {

	 public void setSuperSessionFactory(SessionFactory sessionFactory){
	        super.setSessionFactory(sessionFactory);
	    }
	public Integer saveResume(ResumeEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
		
		
	}

	public Boolean deleteResume(int resumeId) {
		try {
			ResumeEntity deleteEntity = (ResumeEntity)getHibernateTemplate().find("from ResumeEntity where resumeId=?", resumeId).get(0);
			getHibernateTemplate().delete(deleteEntity);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public void updateResume(ResumeEntity entity) {
		getHibernateTemplate().update(entity);
		
	}
	public List<?> findResume(int userId) {
		return getHibernateTemplate().find("from ResumeEntity e where e.userId=?", userId);
	}
	
	public ResumeEntity findResumeById(int id){
		return (ResumeEntity)getHibernateTemplate().find("from ResumeEntity e where e.resumeId=?", id).get(0);
	}

}