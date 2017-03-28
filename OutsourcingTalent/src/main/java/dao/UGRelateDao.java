package dao;

import java.util.List;

import entity.RelateUserGroupEntity;

public interface UGRelateDao {

	public Integer addRelate(RelateUserGroupEntity entity);
	public void deleteRelate(RelateUserGroupEntity entity);
	public Boolean deleteMember(Integer userId,Integer groupId);
	public void update(RelateUserGroupEntity entity);
	public List<?> findRelatesByUserId(int userId);
	public List<?> findRelatesByGroupId(int groupId);
	public RelateUserGroupEntity findRelate(Integer userId,Integer groupId);
}
