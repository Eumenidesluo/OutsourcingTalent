package dao;

import java.util.List;

import entity.RelateUserGroupEntity;

public interface UGRelateDao {

	public void addRelate(RelateUserGroupEntity entity);
	public void deleteRelate(RelateUserGroupEntity entity);
	public void update(RelateUserGroupEntity entity);
	public List<?> findRelatesByUserId(int userId);
	public List<?> findRelatesByGroupId(int groupId);
}
