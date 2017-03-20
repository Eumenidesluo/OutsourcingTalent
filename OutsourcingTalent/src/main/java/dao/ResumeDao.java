package dao;

import java.util.List;

import entity.ResumeEntity;

public interface ResumeDao {
	public Integer saveResume(ResumeEntity entity);
	public Boolean deleteResume(int resumeId);
	public Boolean updateResume(ResumeEntity entity);
	public List<?> findResume(int userId);
	public ResumeEntity findResumeById(int resumeId);
}
