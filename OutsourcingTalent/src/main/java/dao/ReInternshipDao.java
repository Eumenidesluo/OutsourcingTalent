package dao;

import java.util.List;

import entity.ReInternshipEntity;

public interface ReInternshipDao {

	public Integer addInternship(ReInternshipEntity entity);
	public Boolean deleteInternship(ReInternshipEntity entity);
	public List<?> findInternshipsByResumeId(int resumeId);
	public ReInternshipEntity findInternshipByinternshioId(int internshipId);
	public Boolean updateInternship(ReInternshipEntity entity);
	
}
