package serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProjectDao;
import entity.ProjectEntity;
import service.ProjectService;

@Service("projectService")
public class ProjectServiceImp implements ProjectService {
	
	@Autowired
	ProjectDao projectDao;

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
		return projectDao.findProjectByProjectId(projectId);
	}

}
