package daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReEvaluationDao;
import entity.ReEvaluationEntity;

public class ReEvaluationDaoImpl extends HibernateDaoSupport implements ReEvaluationDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	public Integer addEvaluation(ReEvaluationEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public Boolean deleteEvaluation(int resumeId) {
		ReEvaluationEntity entity = findEvaluation(resumeId);
		if (entity == null) {
			return false;
		}
		try {
			getHibernateTemplate().delete(entity);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	public ReEvaluationEntity findEvaluation(int resumeId) {
		return (ReEvaluationEntity)getHibernateTemplate().find("from ReEvaluationEntity e where e.resumeId=?", resumeId).get(0);
	}

	public Boolean updateEvaluation(ReEvaluationEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

}
