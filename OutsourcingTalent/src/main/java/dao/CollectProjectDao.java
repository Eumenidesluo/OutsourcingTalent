package dao;

import java.util.List;

import entity.CollectUserProjectEntity;

public interface CollectProjectDao {
	
	public Integer addCollect(CollectUserProjectEntity entity);
	public Boolean deleteCollect(Integer userId,Integer projectId) ;
	public List<?> findCollectsByUserId(int userId);
	public List<?> findCollectsByProjectId(int projectId);
	public CollectUserProjectEntity findCollectOne(Integer userId,Integer projectId);

}
