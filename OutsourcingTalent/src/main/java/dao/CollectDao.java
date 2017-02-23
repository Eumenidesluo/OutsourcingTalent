package dao;

import java.util.List;

import entity.CollectUserRecruitEntity;

public interface CollectDao {

	public void addCollect(CollectUserRecruitEntity entity);
	public void deleteCollect(CollectUserRecruitEntity entity);
	public void updateCollect(CollectUserRecruitEntity entity);
	public List<?> findCollectsByUserId(int userId);
	public List<?> findCollectsByRecruitId(int recruitId);
}
