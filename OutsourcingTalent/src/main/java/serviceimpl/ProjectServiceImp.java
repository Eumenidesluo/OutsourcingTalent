package serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompanyDao;
import dao.ProjectDao;
import entity.CompanyEntity;
import entity.ProjectEntity;
import service.ProjectService;

@Service("projectService")
public class ProjectServiceImp implements ProjectService {
	
	@Autowired
	ProjectDao projectDao;
	@Autowired
	CompanyDao companyDao;

	public Integer releaseProject(ProjectEntity projectEntity) {
		return projectDao.addProject(projectEntity);
	}

	public Boolean updateProject(ProjectEntity projectEntity) {
		return projectDao.updateProject(projectEntity);
	}

	public Boolean deleteProject(Integer projectId) {
		ProjectEntity entity = projectDao.findProjectByProjectId(projectId);
		if (entity == null) {
			return false;
		}
		return projectDao.deleteProject(entity);
	}

	public ProjectEntity queryProject(Integer projectId) {
		ProjectEntity projectEntity = projectDao.findProjectByProjectId(projectId);
		if (projectEntity == null) {
			return null;
		}
		CompanyEntity companyEntity = companyDao.findCompanyById(projectEntity.getCompanyId());
		if (companyEntity == null) {
			return null;
		}
		projectEntity.setCompanyName(companyEntity.getName());
		return projectEntity;
	}
	
	public List<?> queryManyProjects(int begin,int max) {
		List<?> list = projectDao.findProjectsLimit(begin, max);
		if (list.size() == 0) {
			return null;
		}
		for (Object o:list) {
			ProjectEntity entity = (ProjectEntity) o;
			CompanyEntity companyEntity = companyDao.findCompanyById(entity.getCompanyId());
			if (companyEntity == null) {
				return null;
			}
			entity.setCompanyName(companyEntity.getName());
		}
		return list;
	}

	@Override
	public List<?> findProjectByKey(String key) {
		
		return projectDao.findProjectsByKey(key);
	}

}
