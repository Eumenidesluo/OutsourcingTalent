package serviceimpl;

import java.lang.reflect.Method;

import dao.GroupDao;
import dao.UGRelateDao;
import dao.UserDao;
import entity.GroupEntity;
import entity.RelateUserGroupEntity;
import service.GroupService;

public class GroupServiceImpl implements GroupService{

	GroupDao groupDao;
	UserDao userDao;
	UGRelateDao ugRelateDao;
	
	
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

	public Integer createGroup(Integer creatorId) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setLeaderId(creatorId);
		groupEntity.setAmount(1);
		RelateUserGroupEntity relateEntity = new RelateUserGroupEntity();
		Integer groupId =  groupDao.addGroup(groupEntity);
		relateEntity.setUserId(creatorId);
		relateEntity.setGroupId(groupId);
		relateEntity.setPlace(0);
		ugRelateDao.addRelate(relateEntity);
		return groupId;
		
	}
	
	@Override
	public Boolean inviteMember(Integer groupId, Integer inviteId) {
		GroupEntity groupEntity = groupDao.findGroup(groupId) ;
		if (groupEntity ==null) {
			return false;
		}
		Integer number = groupEntity.getAmount();
		if (number > 9||number < 1){
			return false;
		}
		Integer index = addMember(groupEntity, inviteId);
		if (index != -1){
			groupDao.updateGroup(groupEntity);
			RelateUserGroupEntity relateEntity = new RelateUserGroupEntity();
			relateEntity.setGroupId(groupId);
			relateEntity.setUserId(inviteId);
			relateEntity.setPlace(index);
			ugRelateDao.addRelate(relateEntity);
			return true;
		}
		
		return false;
	}
	
	private Integer addMember(GroupEntity groupEntity,Integer inviteId) {
		Class<GroupEntity> groupClazz = GroupEntity.class;
		for(Integer number = 1;number < 10 ;number++){
			String methodName = "getMemberId"+number;
			try {
				Method method = groupClazz.getMethod(methodName);
				if(method.invoke(groupEntity) == null){
					methodName = "setMemberId"+number;
					method =groupClazz.getMethod(methodName, Integer.class);
					method.invoke(groupEntity, inviteId);
					groupEntity.setAmount(number+1);
					return number;
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			} 
		}
		return -1;
	}
	
	public GroupEntity findGroup(Integer groupId){
		
		return groupDao.findGroup(groupId);
	}
	
	public Boolean deleteMenber(Integer groupId, Integer deleteId) {
		
		GroupEntity groupEntity = groupDao.findGroup(groupId) ;
		if (groupEntity ==null) {
			return false;
		}
		Integer number = groupEntity.getAmount();
		if (number > 10||number < 2){
			return false;
		}
		
		if (deleteExcute(groupEntity, deleteId)){
			groupDao.updateGroup(groupEntity);
			ugRelateDao.deleteRelate(ugRelateDao.findRelate(deleteId, groupId));
			return true;
		}
		return false;
	}
	
	private Boolean deleteExcute(GroupEntity groupEntity,Integer deleteId){
		Class<GroupEntity> groupClazz = GroupEntity.class;
		for(Integer number = 1;number < 10 ;number++){
			String methodName = "getMemberId"+number;
			try {
				Method method = groupClazz.getMethod(methodName);
				if(method.invoke(groupEntity) == deleteId){
					methodName = "setMemberId"+number;
					method =groupClazz.getMethod(methodName,Integer.class);
					Integer value = null;
					method.invoke(groupEntity,value);
					groupEntity.setAmount(number);
					return true;
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		}
		return false;
	}
	
}
