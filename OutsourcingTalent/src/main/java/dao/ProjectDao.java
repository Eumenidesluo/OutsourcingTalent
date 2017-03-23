package dao;

import java.util.List;

import entity.ProjectEntity;

public interface ProjectDao {
	
	public Integer addProject(ProjectEntity entity);
	public Boolean deleteProject(ProjectEntity entity);
	public Boolean updateProject(ProjectEntity entity);
	public ProjectEntity findProjectByProjectId(int projectId);
	public List<?> findProjectByCompanyId(int CompanyId);
	public List<?> findProjectsLimit(int begin,int max);
	public List<?> findProjectsByKey(String key);
}
