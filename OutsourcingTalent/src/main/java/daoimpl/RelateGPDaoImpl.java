package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.RelateGPDao;
import entity.RelateGroupProjectEntity;

public class RelateGPDaoImpl extends HibernateDaoSupport implements RelateGPDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addRelate(RelateGroupProjectEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteRelate(RelateGroupProjectEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateRelate(RelateGroupProjectEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public RelateGroupProjectEntity findRelateByBidId(int bidId) {
		return (RelateGroupProjectEntity)getHibernateTemplate().find("from RelateGroupProjectEntity e where e.bidId", bidId).get(0);
	}

	public List<?> findRelatesByProjectId(int projectId) {
		List<?> list = getHibernateTemplate().find("from RelateGroupProjectEntity e where e.projectId=?", projectId);
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	public List<?> findRelatesByGroupId(int groupId) {
		List<?> list = getHibernateTemplate().find("from RelateGroupProjectEntity e where e.groupId=?", groupId);
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

}
