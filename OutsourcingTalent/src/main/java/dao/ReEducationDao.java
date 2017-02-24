package dao;

import java.util.List;

import entity.ReEducationEntity;

public interface ReEducationDao {
	public Integer addEducation(ReEducationEntity educationEntity);
	public Boolean deleteEducation(ReEducationEntity educationEntity);
	public List<?> findEducationsByResumeId(int ReusmeId);
	public Boolean updateEducation(ReEducationEntity entity);
	public ReEducationEntity findByEducationId(int EducationId);
	
}
