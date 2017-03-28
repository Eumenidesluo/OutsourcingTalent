package daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.CollectProjectDao;
import entity.CollectUserProjectEntity;

public class CollectProjectDaoImpl extends HibernateDaoSupport implements CollectProjectDao{

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	@Override
	public Integer addCollect(CollectUserProjectEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	@Override
	public Boolean deleteCollect(Integer userId, Integer projectId) {
		Session session = getSessionFactory().getCurrentSession();
		try {		
			Query query = session.createQuery("delete CollectUserProjectEntity as e where e.userId = ? and e.projectId = ?");
			query.setInteger(0, userId);
			query.setInteger(1, projectId);
			query.executeUpdate();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<?> findCollectsByUserId(int userId) {
		List<?> list = getHibernateTemplate().find("from CollectUserProjectEntity e where e.userId=?", userId);
		if (list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public List<?> findCollectsByProjectId(int projectId) {
		List<?> list = getHibernateTemplate().find("from CollectUserProjectEntity e where e.projectId=?", projectId);
		if (list.size()==0) {
			return null;
		}
		return list;
	}

	@Override
	public CollectUserProjectEntity findCollectOne(Integer userId, Integer projectId) {
		List<?> list = getHibernateTemplate().find("from CollectUserProjectEntity e where e.userId=? and e.projectId=?", userId,projectId);
		if (list.size()==0) {
			return null;
		}
		return (CollectUserProjectEntity)list.get(0);
	}

}
