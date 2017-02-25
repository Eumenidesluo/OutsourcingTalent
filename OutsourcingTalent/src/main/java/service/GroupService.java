package service;

import entity.GroupEntity;

public interface GroupService {

	public Integer createGroup(Integer creator);
	
	public GroupEntity findGroup(Integer groupId);
	
	public Boolean inviteMember(Integer groupId,Integer inviteId);
	
	public Boolean deleteMenber(Integer groupId,Integer deleteId);
	
}
