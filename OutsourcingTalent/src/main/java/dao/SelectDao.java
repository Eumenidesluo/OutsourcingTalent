package dao;

import java.util.List;

import entity.SelectUserRecruitEntity;

public interface SelectDao {
	public void addSelect(SelectUserRecruitEntity entity);
	public void deleteSelect(SelectUserRecruitEntity entity);
	public void updateSelect(SelectUserRecruitEntity entity);
	public List<?> findSelectsByUserId(int userId);
	public List<?> findSelectsByRecruitId(int recruitId);
}
