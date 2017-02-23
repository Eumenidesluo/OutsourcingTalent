package dao;

import java.util.List;

import entity.ReInternshipEntity;

public interface ReInternshipDao {

	public void addInternship(ReInternshipEntity entity);
	public void deleteInternship(ReInternshipEntity entity);
	public List<?> findInternshipsByResumeId(int resumeId);
	public ReInternshipEntity findInternshipByinternshioId(int internshipId);
	public void deleteInternshipByInternshipId(int internshipId);
	public void deleteInternshipsByResumeId(int resumeId);
	public void updateInternship(ReInternshipEntity entity);
	
}
