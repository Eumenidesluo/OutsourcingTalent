package dao;

import java.util.List;

import entity.ReScienceEntity;

public interface ReScienceDao {
	public Integer addScience(ReScienceEntity entity);
	public Boolean deleteScience(ReScienceEntity entity);
	public Boolean updateScience(ReScienceEntity entity);
	public ReScienceEntity findScienceByScienceId(int scienceId);
	public List<?> findSciencesByResumeId(int resumeId);
}
