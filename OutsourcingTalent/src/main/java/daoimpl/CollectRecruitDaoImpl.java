package daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.CollectRecruitDao;
import entity.CollectUserRecruitEntity;

public class CollectRecruitDaoImpl extends HibernateDaoSupport implements CollectRecruitDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Integer addCollect(CollectUserRecruitEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);	
	}

	public Boolean deleteCollect(Integer userId,Integer recruitId) {
		Session session = getSessionFactory().getCurrentSession();
		try {		
			Query query = session.createQuery("delete CollectUserRecruitEntity as e where e.userId = ? and e.recruitId = ?");
			query.setInteger(0, userId);
			query.setInteger(1, recruitId);
			query.executeUpdate();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public void updateCollect(CollectUserRecruitEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public CollectUserRecruitEntity findCollectOne(Integer userId,Integer recruitId){
		List<?> list = getHibernateTemplate().find("from CollectUserRecruitEntity e where e.userId=? and e.recruitId=?", userId,recruitId);
		if (list.size()==0) {
			return null;
		}
		return (CollectUserRecruitEntity)list.get(0);
	}
	public List<?> findCollectsByUserId(int userId) {
		List<?> list = getHibernateTemplate().find("from CollectUserRecruitEntity e where e.userId=?", userId);
		if (list.size()==0) {
			return null;
		}
		return list;
	}

	public List<?> findCollectsByRecruitId(int recruitId) {
		List<?> list = getHibernateTemplate().find("from CollectUserRecruitEntity e where e.recruitId=?", recruitId);
		if (list.size()==0) {
			return null;
		}
		return list;
	}



}
