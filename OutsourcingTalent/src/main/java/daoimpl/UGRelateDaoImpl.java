package daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.UGRelateDao;
import entity.RelateUserGroupEntity;

public class UGRelateDaoImpl extends HibernateDaoSupport implements UGRelateDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Integer addRelate(RelateUserGroupEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public void deleteRelate(RelateUserGroupEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void update(RelateUserGroupEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public List<?> findRelatesByUserId(int userId) {
		return getHibernateTemplate().find("from RelateUserGroupEntity e where e.userId=?", userId);
	}

	public List<?> findRelatesByGroupId(int groupId) {
		return getHibernateTemplate().find("from RelateUserGroupEntity e where e.groupId=?", groupId);
	}

	public RelateUserGroupEntity findRelate(Integer userId,Integer groupId){
		return (RelateUserGroupEntity)getHibernateTemplate().find("from RelateUserGroupEntity e where e.groupId=? and e.userId=?", groupId,userId).get(0);
	}

	@Override
	public Boolean deleteMember(Integer userId, Integer groupId) {
		Session session = getSessionFactory().getCurrentSession();
		try {		
			Query query = session.createQuery("delete RelateUserGroupEntity e where e.userId = ? and e.groupId = ?");
			query.setInteger(0, userId);
			query.setInteger(1, groupId);
			query.executeUpdate();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
