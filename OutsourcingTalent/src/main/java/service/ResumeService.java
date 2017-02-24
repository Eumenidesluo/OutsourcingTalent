package service;

public interface ResumeService {
	public String addResumeInformations(String partName,String json,Object... values);
	public String queryResume(int resumeId);
	public String queryPartByResumeId(String partName,int resumeId);
	public String queryPartByMainId(String partName,int Id);
	public Boolean deleteResume(int resumeId);
	public Boolean deletePartById(int Id,String partName);
	public Boolean updateSingle(String json,String partName);
	public String updateParts(String json,String partName);
}
