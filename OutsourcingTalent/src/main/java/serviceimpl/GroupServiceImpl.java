package serviceimpl;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GroupDao;
import dao.UserDao;
import entity.GroupEntity;
import service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupDao groupDao;
	@Autowired
	UserDao userDao;
	public Integer createGroup(Integer creatorId) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setLeaderId(creatorId);
		return groupDao.addGroup(groupEntity);
		
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
		
		if (addMember(groupEntity, inviteId)){
			groupDao.updateGroup(groupEntity);
			return true;
		}
		
		return false;
	}
	
	private Boolean addMember(GroupEntity groupEntity,Integer inviteId) {
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
					return true;
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		}
		return false;
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
