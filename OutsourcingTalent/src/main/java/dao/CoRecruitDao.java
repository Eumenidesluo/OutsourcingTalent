package dao;

import java.util.List;

import entity.CoRecruitEntity;

public interface CoRecruitDao {
	public void addRecruit(CoRecruitEntity entity);
	public void deleteRecruit(CoRecruitEntity entity);
	public void updateRecruit(CoRecruitEntity entity);
	public CoRecruitEntity findRecruitByRecruitId(int recruitId);
	public List<?> findRecruitsByCompanyId(int companyId);
	public List<?> findRecruitsLimit(int begin,int max,String tag);
	public List<?> findByKeyWord(String keyWord);
}
