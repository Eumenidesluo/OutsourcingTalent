package dao;

import entity.ResumeEntity;

public interface ResumeDao {
	public void saveResume(ResumeEntity entity);
	public void deleteResume(int resumeId);
	public void updateResume(ResumeEntity entity);
	public ResumeEntity findResume(int userId);
}
