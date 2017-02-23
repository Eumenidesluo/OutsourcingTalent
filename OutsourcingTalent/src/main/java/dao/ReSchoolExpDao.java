package dao;

import java.util.List;

import entity.ReSchoolExpEntity;

public interface ReSchoolExpDao {
	public void addSchoolExp(ReSchoolExpEntity entity);
	public void deleteSchoolExp(ReSchoolExpEntity entity);
	public void updateSchoolExp(ReSchoolExpEntity entity);
	public ReSchoolExpEntity findExpBySchoolExpId(int schoolExpId);
	public List<?> findExpsByResumeId(int resumeId);
}
