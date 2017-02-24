package dao;

import java.util.List;

import entity.ReSchoolExpEntity;

public interface ReSchoolExpDao {
	public Integer addSchoolExp(ReSchoolExpEntity entity);
	public Boolean deleteSchoolExp(ReSchoolExpEntity entity);
	public Boolean updateSchoolExp(ReSchoolExpEntity entity);
	public ReSchoolExpEntity findExpBySchoolExpId(int schoolExpId);
	public List<?> findExpsByResumeId(int resumeId);
}
