package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.CollectDao;
import entity.CollectUserRecruitEntity;

public class CollectDaoImpl extends HibernateDaoSupport implements CollectDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addCollect(CollectUserRecruitEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteCollect(CollectUserRecruitEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateCollect(CollectUserRecruitEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public List<?> findCollectsByUserId(int userId) {
		return getHibernateTemplate().find("from CollectUserRecruitEntity e where e.userId=?", userId);
	}

	public List<?> findCollectsByRecruitId(int recruitId) {
		return getHibernateTemplate().find("from CollectUserRecruitEntity e where e.recruitId=?", recruitId);
	}



}
