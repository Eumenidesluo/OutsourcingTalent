package serviceimpl;

import java.util.List;

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

//	private ProjectStatusEntity queryStatus(Integer projectId) {
//		return projectStatusDao.queryProjectStatus(projectId);
//	}
	
	public Boolean appoint(Integer projectId,Integer appointor){
		Boolean flag = true;
		RelateGroupProjectEntity entity = relateGPDao.findRelateByProjectIdAndGroupId(projectId, appointor);
		List<?> list = relateGPDao.findRelatesByProjectId(projectId);
		if (entity == null) {
			return false;
		}
		for(Object o:list) {
			RelateGroupProjectEntity relate = (RelateGroupProjectEntity)o;
			if (relate.getIsEntrusted() == 1) {
				flag = false;
				break;
			}
		}
		if (!flag) {
			return false;
		}
		entity.setIsEntrusted(1);
		relateGPDao.updateRelate(entity);
		return true;
	}
//	public String appoint(Integer projectId,Integer appointor) throws ServiceException{
//		ProjectStatusEntity projectStatusEntity = queryStatus(projectId);
//		if (projectStatusEntity == null) {
//			throw new ServiceException("Invalid parameter");
//		}
//		String apply = projectStatusEntity.getApplyGroup();
//		String[] applyArray = apply.split(" ");
//		List<String> applyList = Arrays.asList(applyArray);
//		if (!applyList.contains(appointor.toString())) {
//			throw new ServiceException("Unacceptable appoint");
//		}
//		if (projectStatusEntity.getIsAppoint()==1) {
//			throw new ServiceException("Not repeat appoint");
//		}
//		
//		projectStatusEntity.setIsAppoint(1);
//		projectStatusEntity.setAppointGroup(appointor);
//		
//		List<?> list = relateGPDao.findRelatesByProjectId(projectId);
//		for(Object object:list){
//			RelateGroupProjectEntity entity = (RelateGroupProjectEntity)object;
//			if (entity.getGroupId() != appointor) {
//				entity.setIsEntrusted(-1);
//				relateGPDao.updateRelate(entity);
//			}else{
//				entity.setIsEntrusted(1);
//				relateGPDao.updateRelate(entity);
//			}
//		}
//		return "success";
//	}
	
//	public Boolean apply(Integer projectId,Integer groupId){
//		String applyStr ;
//		ProjectStatusEntity projectStatusEntity = projectStatusDao.queryProjectStatus(projectId);
//		if (projectStatusEntity.getIsAppoint() != 0) {
//			return false;
//		}
//		if (projectStatusEntity.getApplyGroup() == null) {
//			applyStr = "";
//		}else{
//			applyStr = projectStatusEntity.getApplyGroup();
//			applyStr += groupId + " ";
//			projectStatusEntity.setApplyGroup(applyStr);
//		}
//		RelateGroupProjectEntity entity = new RelateGroupProjectEntity();
//		entity.setProjectId(projectId);
//		entity.setGroupId(groupId);
//		entity.setIsEntrusted(0);
//		projectStatusDao.updateProjectStatus(projectStatusEntity);
//		relateGPDao.addRelate(entity);	
//		return true;
//	}
	public Boolean apply(Integer projectId,Integer groupId) {
		RelateGroupProjectEntity entity = new RelateGroupProjectEntity();
		entity.setGroupId(groupId);
		entity.setProjectId(projectId);
		entity.setIsEntrusted(0);
		if(relateGPDao.addRelate(entity)<0)
			return false;
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

	@Override
	public Integer status(Integer projectId, Integer groupId) {
		RelateGroupProjectEntity entity = relateGPDao.findRelateByProjectIdAndGroupId(projectId, groupId);
		if (entity == null) {
			return -1;
		}
		
		return entity.getIsEntrusted();
	}

}
