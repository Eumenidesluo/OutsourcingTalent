package daoimpl;

import org.hibernate.SessionFactory;
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

	public void deleteEvaluation(int resumeId) {
		ReEvaluationEntity entity = findEvaluation(resumeId);
		getHibernateTemplate().delete(entity);
	}

	public ReEvaluationEntity findEvaluation(int resumeId) {
		return (ReEvaluationEntity)getHibernateTemplate().find("from ReEvaluationEntity e where e.resumeId=?", resumeId).get(0);
	}

	public void updateEvaluation(ReEvaluationEntity entity) {
		getHibernateTemplate().update(entity);
	}

}
