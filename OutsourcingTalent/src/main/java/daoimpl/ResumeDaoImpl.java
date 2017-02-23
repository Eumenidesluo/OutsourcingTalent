package daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ResumeDao;
import entity.ResumeEntity;

public class ResumeDaoImpl extends HibernateDaoSupport implements ResumeDao {

	 public void setSuperSessionFactory(SessionFactory sessionFactory){
	        super.setSessionFactory(sessionFactory);
	    }
	@Override
	public void saveResume(ResumeEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteResume(int resumeId) {
		ResumeEntity deleteEntity = (ResumeEntity)getHibernateTemplate().find("from ResumeEntity where resumeId=?", resumeId).get(0);
		getHibernateTemplate().delete(deleteEntity);

	}

	public void updateResume(ResumeEntity entity) {
//		try {
//			Session session = getSessionFactory().openSession();
//			session.beginTransaction();
//			ResumeEntity entity2 = (ResumeEntity)getHibernateTemplate().find("from ResumeEntity e where e.userId=?", entity.getUserId()).get(0);
//			entity2 = entity;
//			getSessionFactory().openSession().beginTransaction().commit();
////		getHibernateTemplate().update(entity);
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		}finally {
//			getSessionFactory().openSession().close();
//		}
		getHibernateTemplate().update(entity);
		
	}
	public ResumeEntity findResume(int userId) {
		return (ResumeEntity)getHibernateTemplate().find("from ResumeEntity e where e.userId=?", userId).get(0);
	}
	

}
