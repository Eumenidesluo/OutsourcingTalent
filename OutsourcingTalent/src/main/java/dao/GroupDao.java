package dao;

import entity.GroupEntity;

public interface GroupDao {
	
	public Integer addGroup(GroupEntity entity);
	public void deleteGroup(GroupEntity entity);
	public void updateGroup(GroupEntity entity);
	public GroupEntity findGroup(int groupId);
}
