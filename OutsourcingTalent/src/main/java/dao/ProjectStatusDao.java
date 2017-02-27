package dao;

import entity.ProjectStatusEntity;

public interface ProjectStatusDao {

	public Boolean updateProjectStatus(ProjectStatusEntity entity);
	
	public ProjectStatusEntity queryProjectStatus(Integer projectId);
}
