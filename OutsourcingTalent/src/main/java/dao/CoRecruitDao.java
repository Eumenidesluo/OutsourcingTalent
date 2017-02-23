package dao;

import java.util.List;

import entity.CoRecruitEntity;

public interface CoRecruitDao {
	public void addRecruit(CoRecruitEntity entity);
	public void deleteRecruit(CoRecruitEntity entity);
	public void updateRecruit(CoRecruitEntity entity);
	public CoRecruitEntity findRecruitByRecruitId(int recruitId);
	public List<?> findRecruitsByCompanyId(int companyId);

}
