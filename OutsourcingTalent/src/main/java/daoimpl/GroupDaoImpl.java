package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.GroupDao;
import entity.GroupEntity;

public class GroupDaoImpl extends HibernateDaoSupport implements GroupDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Integer addGroup(GroupEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public void deleteGroup(GroupEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateGroup(GroupEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public GroupEntity findGroup(int groupId) {
		List<?> list = getHibernateTemplate().find("from GroupEntity e where e.groupId=?", groupId);
		if (list.size() == 0) {
			return null;
		}
		return (GroupEntity)list.get(0);
	}

}
