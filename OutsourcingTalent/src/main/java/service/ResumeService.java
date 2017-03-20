package service;

import java.util.List;

import entity.ResumeEntity;

public interface ResumeService {
	public Integer addResumeInformations(String partName,String json,Object... values);
	public ResumeEntity queryResume(int resumeId);
	public List<?> queryPartByResumeId(String partName,int resumeId);
	public Object queryPartByMainId(String partName,int Id);
	public Boolean deleteResume(int resumeId);
	public Boolean deletePartById(int Id,String partName);
	public Boolean updatePart(String json,String partName);
}
