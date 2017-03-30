package serviceimpl;

import java.util.ArrayList;
import java.util.List;

import dao.GroupDao;
import dao.PersonalInfDao;
import dao.ProjectDao;
import dao.RelateGPDao;
import dao.UGRelateDao;
import dao.UserDao;
import entity.GroupEntity;
import entity.MemberBean;
import entity.PersonalInfEntity;
import entity.ProjectEntity;
import entity.RelateGroupProjectBean;
import entity.RelateGroupProjectEntity;
import entity.RelateUserGroupBean;
import entity.RelateUserGroupEntity;
import entity.UserEntity;
import service.GroupService;

public class GroupServiceImpl implements GroupService{

	GroupDao groupDao;
	UserDao userDao;
	UGRelateDao ugRelateDao;
	PersonalInfDao personalInfDao;
	RelateGPDao relateGPDao;
	ProjectDao projectDao;
	
	
	
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public RelateGPDao getRelateGPDao() {
		return relateGPDao;
	}

	public void setRelateGPDao(RelateGPDao relateGPDao) {
		this.relateGPDao = relateGPDao;
	}

	public PersonalInfDao getPersonalInfDao() {
		return personalInfDao;
	}

	public void setPersonalInfDao(PersonalInfDao personalInfDao) {
		this.personalInfDao = personalInfDao;
	}

	public UGRelateDao getUgRelateDao() {
		return ugRelateDao;
	}

	public void setUgRelateDao(UGRelateDao ugRelateDao) {
		this.ugRelateDao = ugRelateDao;
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Integer createGroup(Integer creatorId,String gourpName) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setLeaderId(creatorId);
		groupEntity.setName(gourpName);
		Integer groupId =  groupDao.addGroup(groupEntity);
		return groupId;
		
	}
	
	@Override
	public Boolean inviteMember(Integer groupId, String email) {
		UserEntity userEntity = userDao.findByEmail(email);
		if (userEntity == null) {
			return false;
		}
		RelateUserGroupEntity entity = new RelateUserGroupEntity();
		entity.setGroupId(groupId);
		entity.setUserId(userEntity.getId());
		entity.setPlace(1);
		if (ugRelateDao.addRelate(entity)<0) {
			return false;
		}
		return true;
	}
	
	
	public List<MemberBean> queryMembers(Integer groupId){
		List<?> relateList = ugRelateDao.findRelatesByGroupId(groupId);
		List<MemberBean> members = new ArrayList<>();
		for(Object o:relateList) {
			RelateUserGroupEntity entity = (RelateUserGroupEntity)o;
			UserEntity user = userDao.findById(String.valueOf(entity.getUserId()));
			PersonalInfEntity psersonal = personalInfDao.findByEmail(user.getEmail());
			members.add(new MemberBean(user.getId(),psersonal.getName()));
		}
		return members;
	}
	
	public Boolean deleteMenber(Integer groupId, Integer deleteId) {
		return ugRelateDao.deleteMember(deleteId, groupId);
	}
	
//	private Boolean deleteExcute(GroupEntity groupEntity,Integer deleteId){
//		Class<GroupEntity> groupClazz = GroupEntity.class;
//		for(Integer number = 1;number < 10 ;number++){
//			String methodName = "getMemberId"+number;
//			try {
//				Method method = groupClazz.getMethod(methodName);
//				if(method.invoke(groupEntity) == deleteId){
//					methodName = "setMemberId"+number;
//					method =groupClazz.getMethod(methodName,Integer.class);
//					Integer value = null;
//					method.invoke(groupEntity,value);
//					groupEntity.setAmount(number);
//					return true;
//				}
//					
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			} 
//		}
//		return false;
//	}

	@Override
	public List<RelateUserGroupBean> getUserGroupInfo(Integer userId) {
		List<?> eList = ugRelateDao.findRelatesByUserId(userId);
		List<RelateUserGroupBean> list = new ArrayList<>();
		for(Object o:eList) {
			RelateUserGroupEntity entity = (RelateUserGroupEntity)o;
			GroupEntity groupEntity = groupDao.findGroup(entity.getGroupId());
			list.add(new RelateUserGroupBean(entity,groupEntity.getName()));
		}
		return list;
	}

	@Override
	public List<RelateGroupProjectBean> findRelatesByGroupId(Integer groupId) {
		List<RelateGroupProjectBean> beanList = new ArrayList<>();
		List<?> entityList = relateGPDao.findRelatesByGroupId(groupId);
		if (entityList == null) {
			return null;
		}
		for(Object o:entityList) {
			RelateGroupProjectEntity entity = (RelateGroupProjectEntity)o;
			ProjectEntity projectEntity = projectDao.findProjectByProjectId(entity.getProjectId());
			beanList.add(new RelateGroupProjectBean(entity.getProjectId(),entity.getIsEntrusted(),projectEntity.getTitle()));
		}
		return beanList;
	}

	@Override
	public GroupEntity findGroupByLeaderId(Integer leader) {
		return groupDao.findGroupByLeaderId(leader);
	}
	
}
