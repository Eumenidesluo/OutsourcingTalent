package dao;

import java.util.List;

import entity.RelateGroupProjectEntity;

public interface RelateGPDao {
	
	public void addRelate(RelateGroupProjectEntity entity);
	public void deleteRelate(RelateGroupProjectEntity entity);
	public void updateRelate(RelateGroupProjectEntity entity);
	public RelateGroupProjectEntity findRelateByBidId(int bidId);
	public List<?> findRelatesByProjectId(int projectId);
	public List<?> findRelatesByGroupId(int groupId);
}
