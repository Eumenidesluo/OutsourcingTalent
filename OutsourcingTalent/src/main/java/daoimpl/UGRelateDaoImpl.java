package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.UGRelateDao;
import entity.RelateUserGroupEntity;

public class UGRelateDaoImpl extends HibernateDaoSupport implements UGRelateDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addRelate(RelateUserGroupEntity entity) {
		getHibernateTemplate().save(entity);
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
}
