package service;

import java.util.List;

import entity.RecruitBean;

public interface RecruitService {
	
	public List<RecruitBean> findRecruitsLimit(int begin,int max,String tag);
	public List<?> findByKey(String key);
}
