package serviceimpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.NoticeDao;
import dao.ResumeDao;
import dao.SelectDao;
import entity.NoticeEntity;
import entity.ResumeEntity;
import entity.SelectUserRecruitEntity;
import service.DeliveryRecruitService;

@Service("DeliveryRecruitService")
public class DeliveryRecruitServiceImpl implements DeliveryRecruitService {

	@Autowired
	SelectDao selectDao;
	@Autowired
	NoticeDao noticeDao;
	@Autowired
	ResumeDao resumeDao;
	
	public Boolean delivery(Integer recruitId,Integer userId,Integer resumeId) {
		List<?> resumeList = resumeDao.findResume(userId);
		Boolean flag = false;
		for(Object o:resumeList){
			ResumeEntity entity = (ResumeEntity)o;
			if (entity.getResumeId() == resumeId) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return false;
		}	
		SelectUserRecruitEntity selectUserRecruitEntity = new SelectUserRecruitEntity();
		selectUserRecruitEntity.setRecruitId(recruitId);
		selectUserRecruitEntity.setUserId(userId);
		selectUserRecruitEntity.setResumeId(resumeId);
		selectUserRecruitEntity.setTime(new Date(System.currentTimeMillis()));
		if (selectDao.addSelect(selectUserRecruitEntity) == -1) {
			return false;
		}
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeEntity.setNotice("Í¶µÝ³É¹¦");
		noticeEntity.setUserId(userId);
		noticeEntity.setTime(new Date(System.currentTimeMillis()));
		noticeDao.saveNotice(noticeEntity);
		return true;
	}
	public Boolean hasDeliveried(Integer userId,Integer recruitId) {
		SelectUserRecruitEntity entity = selectDao.findSelectByUserIdAndRecruitId(recruitId, userId);
		if (entity == null) {
			return false;
		}
		return true;
	}
}
