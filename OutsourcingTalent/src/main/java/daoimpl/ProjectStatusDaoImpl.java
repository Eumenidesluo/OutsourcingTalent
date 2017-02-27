package daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ProjectStatusDao;
import entity.ProjectStatusEntity;

public class ProjectStatusDaoImpl extends HibernateDaoSupport implements ProjectStatusDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Boolean updateProjectStatus(ProjectStatusEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	public ProjectStatusEntity queryProjectStatus(Integer projectId) {
		return (ProjectStatusEntity)getHibernateTemplate().find("from ProjectStatusEntity e where e.projectId = ?", projectId).get(0);
	}

}
