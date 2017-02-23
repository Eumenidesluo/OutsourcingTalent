package dao;

import java.util.List;

import entity.ReEducationEntity;

public interface ReEducationDao {
	public void addEducation(ReEducationEntity educationEntity);
	public void deleteEducation(ReEducationDao educationEntityDao);
	public List<?> findEducationsByResumeId(int ReusmeId);
	public void updateEducation(ReEducationEntity entity);
	public ReEducationEntity findByEducationId(int EducationId);
	
}
