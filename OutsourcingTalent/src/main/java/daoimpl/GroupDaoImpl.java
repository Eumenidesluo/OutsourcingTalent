package daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.GroupDao;
import entity.GroupEntity;

public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addGroup(GroupEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteGroup(GroupEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateGroup(GroupEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public GroupEntity findGroup(int groupId) {
		return (GroupEntity)getHibernateTemplate().find("from GroupEntity e where e.groupId=?", groupId).get(0);
	}

}
