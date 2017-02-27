package service;

import component.ServiceException;

public interface ProjectStatusService {

	public Boolean apply(Integer projectId,Integer groupId);
	
	public String appoint(Integer projectId,Integer appointor) throws ServiceException;
	
	public Boolean finish(Integer projectId,Integer groupId);
}
