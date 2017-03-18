package serviceimpl;

import java.util.Arrays;
import java.util.List;

import component.ServiceException;
import dao.ProjectStatusDao;
import dao.RelateGPDao;
import entity.ProjectStatusEntity;
import entity.RelateGroupProjectEntity;
import service.ProjectStatusService;

public class ProjectStatusServiceImpl implements ProjectStatusService {

	ProjectStatusDao projectStatusDao;
	
	RelateGPDao relateGPDao;
	

	public ProjectStatusDao getProjectStatusDao() {
		return projectStatusDao;
	}

	public void setProjectStatusDao(ProjectStatusDao projectStatusDao) {
		this.projectStatusDao = projectStatusDao;
	}

	public RelateGPDao getRelateGPDao() {
		return relateGPDao;
	}

	public void setRelateGPDao(RelateGPDao relateGPDao) {
		this.relateGPDao = relateGPDao;
	}

	private ProjectStatusEntity queryStatus(Integer projectId) {
		return projectStatusDao.queryProjectStatus(projectId);
	}
	
	public String appoint(Integer projectId,Integer appointor) throws ServiceException{
		ProjectStatusEntity projectStatusEntity = queryStatus(projectId);
		if (projectStatusEntity == null) {
			throw new ServiceException("Invalid parameter");
		}
		String apply = projectStatusEntity.getApplyGroup();
		String[] applyArray = apply.split(" ");
		List<String> applyList = Arrays.asList(applyArray);
		if (!applyList.contains(appointor.toString())) {
			throw new ServiceException("Unacceptable appoint");
		}
		if (projectStatusEntity.getIsAppoint()==1) {
			throw new ServiceException("Not repeat appoint");
		}
		
		projectStatusEntity.setIsAppoint(1);
		projectStatusEntity.setAppointGroup(appointor);
		
		List<?> list = relateGPDao.findRelatesByProjectId(projectId);
		for(Object object:list){
			RelateGroupProjectEntity entity = (RelateGroupProjectEntity)object;
			if (entity.getGroupId() != appointor) {
				entity.setIsEntrusted(-1);
				relateGPDao.updateRelate(entity);
			}else{
				entity.setIsEntrusted(1);
				relateGPDao.updateRelate(entity);
			}
		}
		return "success";
	}
	
	public Boolean apply(Integer projectId,Integer groupId){
		String applyStr ;
		ProjectStatusEntity projectStatusEntity = projectStatusDao.queryProjectStatus(projectId);
		if (projectStatusEntity.getIsAppoint() != 0) {
			return false;
		}
		if (projectStatusEntity.getApplyGroup() == null) {
			applyStr = "";
		}else{
			applyStr = projectStatusEntity.getApplyGroup();
			applyStr += groupId + " ";
			projectStatusEntity.setApplyGroup(applyStr);
		}
		RelateGroupProjectEntity entity = new RelateGroupProjectEntity();
		entity.setProjectId(projectId);
		entity.setGroupId(groupId);
		entity.setIsEntrusted(0);
		projectStatusDao.updateProjectStatus(projectStatusEntity);
		relateGPDao.addRelate(entity);	
		return true;
	}
	
	public Boolean finish(Integer projectId,Integer groupId){
		ProjectStatusEntity statusEntity = projectStatusDao.queryProjectStatus(projectId);
		if (statusEntity == null) {
			return false;
		}
		if (statusEntity.getIsFinish() == 1) {
			return false;
		}
		statusEntity.setIsFinish(1);
		projectStatusDao.updateProjectStatus(statusEntity);
		return true;
	}

}
