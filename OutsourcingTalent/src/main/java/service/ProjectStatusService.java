package service;

public interface ProjectStatusService {

	public Boolean apply(Integer projectId,Integer groupId);
	public Integer status(Integer projectId,Integer groupId);
	public Boolean appoint(Integer projectId,Integer appointor);
	
	public Boolean finish(Integer projectId,Integer groupId);
}
