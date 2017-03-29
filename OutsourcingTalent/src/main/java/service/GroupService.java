package service;

import java.util.List;

import entity.GroupEntity;
import entity.MemberBean;
import entity.RelateGroupProjectBean;
import entity.RelateUserGroupBean;

public interface GroupService {

	public Integer createGroup(Integer creator,String groupName);
	
	public List<MemberBean> queryMembers(Integer groupId);
	
	public Boolean inviteMember(Integer groupId,Integer inviteId);
	
	public Boolean deleteMenber(Integer groupId,Integer deleteId);
	
	public List<RelateUserGroupBean> getUserGroupInfo(Integer userId);
	
	public List<RelateGroupProjectBean> findRelatesByGroupId(Integer groupId);
	
	public GroupEntity findGroupByLeaderId(Integer leader);
	
}
