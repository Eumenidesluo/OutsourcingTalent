package dao;

import java.util.List;

import entity.CollectUserRecruitEntity;

public interface CollectRecruitDao {

	public Integer addCollect(CollectUserRecruitEntity entity);
	public Boolean deleteCollect(Integer userId,Integer recruitId) ;
	public void updateCollect(CollectUserRecruitEntity entity);
	public List<?> findCollectsByUserId(int userId);
	public List<?> findCollectsByRecruitId(int recruitId);
	public CollectUserRecruitEntity findCollectOne(Integer userId,Integer recruitId);
}
