package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ProjectDao;
import entity.ProjectEntity;

public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Integer addProject(ProjectEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public Boolean deleteProject(ProjectEntity entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean updateProject(ProjectEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ProjectEntity findProjectByProjectId(int projectId) {
		return (ProjectEntity)getHibernateTemplate().find("from ProjectEntity e where e.projectId=?", projectId).get(0);
	}

	public List<?> findProjectByCompanyId(int companyId) {
		return getHibernateTemplate().find("from ProjectEntity e where e.companyId=?", companyId);
	}

}
