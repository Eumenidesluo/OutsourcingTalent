package dao;

import java.util.List;

import entity.RelateGroupProjectEntity;

public interface RelateGPDao {
	
	public Integer addRelate(RelateGroupProjectEntity entity);
	public void deleteRelate(RelateGroupProjectEntity entity);
	public void updateRelate(RelateGroupProjectEntity entity);
	public RelateGroupProjectEntity findRelateByBidId(int bidId);
	public RelateGroupProjectEntity findRelateByProjectIdAndGroupId(Integer projectId,Integer GroupId);
	public List<?> findRelatesByProjectId(int projectId);
	public List<?> findRelatesByGroupId(int groupId);
}
