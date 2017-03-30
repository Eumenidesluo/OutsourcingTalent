package serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CoRecruitDao;
import dao.CompanyDao;
import entity.CoRecruitEntity;
import entity.CompanyEntity;
import entity.RecruitBean;
import service.RecruitService;

@Service("recruitService")
public class RecruitServiceImpl implements RecruitService{

	@Autowired
	CoRecruitDao coRecruitDao;
	@Autowired
	CompanyDao companyDao;
	@Override
	public List<RecruitBean> findRecruitsLimit(int begin, int max,String tag) {
		List<?> entityList = coRecruitDao.findRecruitsLimit(begin, max,tag);
		if (entityList == null) {
			return null;
		}
		List<RecruitBean> beanList = new ArrayList<>();
		
		for (Object o:entityList){
			CoRecruitEntity entity = (CoRecruitEntity)o;
			CompanyEntity companyEntity = companyDao.findCompanyById(entity.getCompanyId());
			RecruitBean bean = new RecruitBean(entity,companyEntity.getName());
			beanList.add(bean);
		}
		return beanList;
	}
	@Override
	public List<?> findByKey(String key) {
		return coRecruitDao.findByKeyWord(key);
	}
	
	@Override
	public RecruitBean findRecruitByRecruitId(int recruitId){
		CoRecruitEntity coRecruitEntity = coRecruitDao.findRecruitByRecruitId(recruitId);
		if (coRecruitEntity == null) {
			return null; 
		}
		CompanyEntity companyEntity = companyDao.findCompanyById(coRecruitEntity.getCompanyId());
		if (companyEntity == null) {
			return null;
		}
		RecruitBean recruitBean = new RecruitBean(coRecruitEntity,companyEntity.getName());
		return recruitBean;
	}
}
