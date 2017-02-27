package service;

import entity.ProjectEntity;

public interface ProjectService {

	public Integer releaseProject(ProjectEntity projectEntity);
	
	public Boolean updateProject(ProjectEntity projectEntity);
	
	public Boolean deleteProject(Integer projectId);
	
	public ProjectEntity queryProject(Integer projectId);
}
