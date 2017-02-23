package dao;

import java.util.List;

import entity.ProjectEntity;

public interface ProjectDao {
	
	public void addProject(ProjectEntity entity);
	public void deleteProject(ProjectEntity entity);
	public void updateProject(ProjectEntity entity);
	public ProjectEntity findProjectByProjectId(int projectId);
	public List<?> findProjectByCompanyId(int CompanyId);
	
}
