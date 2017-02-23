package dao;

import java.util.List;

import entity.ReScienceEntity;

public interface ReScienceDao {
	public void addScience(ReScienceEntity entity);
	public void deleteScience(ReScienceEntity entity);
	public void updateScience(ReScienceEntity entity);
	public ReScienceEntity findScienceByScienceId(int scienceId);
	public List<?> findSciencesByResumeId(int resumeId);
}
