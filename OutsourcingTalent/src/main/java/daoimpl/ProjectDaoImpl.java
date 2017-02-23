package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ProjectDao;
import entity.ProjectEntity;

public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addProject(ProjectEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteProject(ProjectEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateProject(ProjectEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public ProjectEntity findProjectByProjectId(int projectId) {
		return (ProjectEntity)getHibernateTemplate().find("from ProjectEntity e where e.projectId=?", projectId).get(0);
	}

	public List<?> findProjectByCompanyId(int companyId) {
		return getHibernateTemplate().find("from ProjectEntity e where e.companyId=?", companyId);
	}

}
