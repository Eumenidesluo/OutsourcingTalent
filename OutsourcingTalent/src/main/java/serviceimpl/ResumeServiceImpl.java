package serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import dao.ReEducationDao;
import dao.ReEvaluationDao;
import dao.ReInternshipDao;
import dao.ReSchoolExpDao;
import dao.ReScienceDao;
import dao.ResumeDao;
import entity.ReEducationEntity;
import entity.ReEvaluationEntity;
import entity.ReInternshipEntity;
import entity.ReSchoolExpEntity;
import entity.ReScienceEntity;
import entity.ResumeEntity;
import service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	ResumeDao resumeDao;
	@Autowired
	ReEducationDao reEducationDao;
	@Autowired
	ReEvaluationDao reEvaluationDao;
	@Autowired
	ReInternshipDao reInternshipDao;
	@Autowired
	ReSchoolExpDao reSchoolExpDao;
	@Autowired
	ReScienceDao reScienceDao;
	
	
	public String addResumeInformations(String partName, String json,Object... values) {
		
		String reportIdStr = "";
		
		switch (partName) {
		
		case "resume":
			ResumeEntity entity = JSON.parseObject(json,ResumeEntity.class);
			Integer resumeId = resumeDao.saveResume(entity);
			reportIdStr = resumeId.toString();
			break;
		case "evaluation":
			ReEvaluationEntity evaluationEntity = JSON.parseObject(json,ReEvaluationEntity.class);
			evaluationEntity.setResumeId((Integer)values[0]);
			Integer eInteger = reEvaluationDao.addEvaluation(evaluationEntity);
			reportIdStr = eInteger.toString();
			break;
		case "education":
			List<ReEducationEntity> list = JSON.parseArray(json,ReEducationEntity.class);
			for(ReEducationEntity reEducationEntity:list){
				reEducationEntity.setResumeId((Integer)values[0]);
				Integer tmInteger = reEducationDao.addEducation(reEducationEntity);
				reportIdStr += tmInteger.toString()+" ";
			}
			break;
		case "internship":
			List<ReInternshipEntity> internshipEntities = JSON.parseArray(json,ReInternshipEntity.class);
			for(ReInternshipEntity reInternshipEntity:internshipEntities){
				reInternshipEntity.setResumeId((Integer)values[0]);
				Integer tmInteger = reInternshipDao.addInternship(reInternshipEntity);
				reportIdStr += tmInteger.toString()+" ";
			}
			break;
		case "schoolExp":
			List<ReSchoolExpEntity> schoolExpEntities = JSON.parseArray(json,ReSchoolExpEntity.class);
			for (ReSchoolExpEntity reSchoolExpEntity:schoolExpEntities){
				reSchoolExpEntity.setResumeId((Integer)values[0]);
				Integer tmInteger = reSchoolExpDao.addSchoolExp(reSchoolExpEntity);
				reportIdStr += tmInteger.toString()+" ";
			}
			break;
		case "science":
			List<ReScienceEntity> scienceEntities = JSON.parseArray(json,ReScienceEntity.class);
			for (ReScienceEntity scienceEntity : scienceEntities){
				scienceEntity.setResumeId((Integer)values[0]);
				Integer tmInteger = reScienceDao.addScience(scienceEntity);
				reportIdStr += tmInteger.toString()+" ";
			}
			break;

		default:
			break;
		}
		return reportIdStr;
	}

	public ResumeEntity queryResume(int resumeId){
		
		ResumeEntity resumeEntity = resumeDao.findResumeById(resumeId);
		if (resumeEntity != null) {
			return resumeEntity;
		}else
			return null;
	}
	public List<?> queryPartByResumeId(String partName,int resumeId){
		List<?> list = new ArrayList<>();
		switch (partName) {
		case "education":
			list = reEducationDao.findEducationsByResumeId(resumeId);
			break;
		case "internship":
			list = reInternshipDao.findInternshipsByResumeId(resumeId);
			break;
		case "schoolExp":
			list = reSchoolExpDao.findExpsByResumeId(resumeId);
		case "science":
			list = reScienceDao.findSciencesByResumeId(resumeId);
			break;
		case "evaluation":
			list = reEducationDao.findEducationsByResumeId(resumeId);
			break;
		default:
			list = null;
			break;
		}
		return list;
	}
	public Object queryPartByMainId(String partName,int Id){
		Object object ;
		switch (partName) {
		case "education":
			object = reEducationDao.findByEducationId(Id);
			break;
		case "internship":
			object = reInternshipDao.findInternshipByinternshioId(Id);
			break;
		case "schoolExp":
			object = reSchoolExpDao.findExpBySchoolExpId(Id);
		case "science":
			object = reScienceDao.findScienceByScienceId(Id);
			break;
		case "evaluation":
			object = reEducationDao.findByEducationId(Id);
			break;
		default:
			object = null;
			break;
		}
		return object;
	}

	public Boolean deleteResume(int resumeId){
		return resumeDao.deleteResume(resumeId);
	}
	public Boolean deletePartById(int Id,String partName){
		switch (partName) {
		case "evaluation":
			return reEvaluationDao.deleteEvaluation(Id);
		case "education":
			return reEducationDao.deleteEducation(reEducationDao.findByEducationId(Id));
		case "internship":
			return reInternshipDao.deleteInternship(reInternshipDao.findInternshipByinternshioId(Id));
		case "schoolExp":
			return reSchoolExpDao.deleteSchoolExp(reSchoolExpDao.findExpBySchoolExpId(Id));
		case "science":
			return reScienceDao.deleteScience(reScienceDao.findScienceByScienceId(Id));
		default:
			return false;
		}
	}

	public Boolean updatePart(String json,String partName){
		switch (partName) {
		case "resume":
			ResumeEntity resumeEntity = JSON.parseObject(json,ResumeEntity.class);
			if (resumeEntity == null) {
				return false;
			}
			if (resumeDao.updateResume(resumeEntity)) {
				return true;
			}
			break;
		case "evaluation":
			ReEvaluationEntity evaluationEntity = JSON.parseObject(json,ReEvaluationEntity.class);
			if (evaluationEntity == null) {
				return false;
			}
			if (reEvaluationDao.updateEvaluation(evaluationEntity)) {
				return true;
			}
			break;
		case "education":
			ReEducationEntity educationEntity = JSON.parseObject(json, ReEducationEntity.class);
			if (educationEntity == null) {
				return false;
			}
			if (reEducationDao.updateEducation(educationEntity)) {
				return true;
			}
			break;
		case "internship":
			ReInternshipEntity internshipEntity = JSON.parseObject(json, ReInternshipEntity.class);
			if (internshipEntity == null) {
				return false;
			}
			if (reInternshipDao.updateInternship(internshipEntity)) {
				return true;
			}
			break;
		case "schoolExp":
			ReSchoolExpEntity schoolExpEntity = JSON.parseObject(json, ReSchoolExpEntity.class);
			if (schoolExpEntity == null) {
				return false;
			}
			if (reSchoolExpDao.updateSchoolExp(schoolExpEntity)) {
				return true;
			}
			break;
		case "science":
			ReScienceEntity scienceEntity = JSON.parseObject(json, ReScienceEntity.class);
			if (scienceEntity == null) {
				return false;
			}
			if (reScienceDao.updateScience(scienceEntity)) {
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}
}
