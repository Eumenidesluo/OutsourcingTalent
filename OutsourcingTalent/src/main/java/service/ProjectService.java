package service;

import java.util.List;

import entity.ProjectEntity;

public interface ProjectService {

	public Integer releaseProject(ProjectEntity projectEntity);
	
	public Boolean updateProject(ProjectEntity projectEntity);
	
	public Boolean deleteProject(Integer projectId);
	
	public ProjectEntity queryProject(Integer projectId);
	
	public List<?> queryManyProjects(int begin,int max);
	
	public List<?> findProjectByKey(String key);
}
