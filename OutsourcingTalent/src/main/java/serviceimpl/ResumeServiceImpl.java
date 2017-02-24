package serviceimpl;

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

	public String queryResume(int resumeId){
		
		ResumeEntity resumeEntity = resumeDao.findResumeById(resumeId);
		if (resumeEntity != null) {
			return JSON.toJSONString(resumeEntity);
		}else
			return "Resume record does not exist";
	}
	public String queryPartByResumeId(String partName,int resumeId){
		String returnString;
		switch (partName) {
		case "education":
			returnString = JSON.toJSONString(reEducationDao.findEducationsByResumeId(resumeId));
			break;
		case "internship":
			returnString = JSON.toJSONString(reInternshipDao.findInternshipsByResumeId(resumeId));
			break;
		case "schoolExp":
			returnString = JSON.toJSONString(reSchoolExpDao.findExpsByResumeId(resumeId));
		case "science":
			returnString = JSON.toJSONString(reScienceDao.findSciencesByResumeId(resumeId));
			break;
		case "evaluation":
			returnString = JSON.toJSONString(reEducationDao.findEducationsByResumeId(resumeId));
			break;
		default:
			returnString = "Unknown parameter";
			break;
		}
		return returnString;
	}
	public String queryPartByMainId(String partName,int Id){
		String returnString;
		switch (partName) {
		case "education":
			returnString = JSON.toJSONString(reEducationDao.findByEducationId(Id));
			break;
		case "internship":
			returnString = JSON.toJSONString(reInternshipDao.findInternshipByinternshioId(Id));
			break;
		case "schoolExp":
			returnString = JSON.toJSONString(reSchoolExpDao.findExpBySchoolExpId(Id));
		case "science":
			returnString = JSON.toJSONString(reScienceDao.findScienceByScienceId(Id));
			break;
		case "evaluation":
			returnString = JSON.toJSONString(reEducationDao.findByEducationId(Id));
			break;
		default:
			returnString = "Unknown parameter";
			break;
		}
		return returnString;
	}

	public Boolean deleteResume(int resumeId){
		return resumeDao.deleteResume(resumeId);
	}
	public Boolean deletePartById(int Id,String partName){
		switch (partName) {
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
	public Boolean updateSingle(String json,String partName){
		
		if (partName.equals("resume")) {
			ResumeEntity entity = JSON.parseObject(json, ResumeEntity.class);
			if (entity == null) {
				return false;
			}
			try {
				resumeDao.updateResume(entity);
			} catch (Exception e) {
				return false;
			}
		}else if (partName.equals("evaluation")) {
			ReEvaluationEntity entity = JSON.parseObject(json,ReEvaluationEntity.class);
			if (entity == null) {
				return false;
			}
			try {
				reEvaluationDao.updateEvaluation(entity);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public String updateParts(String json,String partName){
		String returnStr = "";
		switch (partName) {
		case "education":
			List<ReEducationEntity> educationEntities = JSON.parseArray(json, ReEducationEntity.class);
			if (educationEntities == null) {
				return returnStr;
			}
			for(ReEducationEntity educationEntity:educationEntities){
				if(reEducationDao.updateEducation(educationEntity))
					returnStr += "S";
				else
					returnStr += "F";
			}
			break;
		case "internship":
			List<ReInternshipEntity> internshipEntities = JSON.parseArray(json, ReInternshipEntity.class);
			if (internshipEntities == null) {
				return returnStr;
			}
			for(ReInternshipEntity internshipEntity:internshipEntities){
				if(reInternshipDao.updateInternship(internshipEntity))
					returnStr += "S";
				else
					returnStr += "F";
			}
			break;
		case "schoolExp":
			List<ReSchoolExpEntity> schoolExpEntities = JSON.parseArray(json, ReSchoolExpEntity.class);
			if (schoolExpEntities == null) {
				return returnStr;
			}
			for(ReSchoolExpEntity schoolExpEntity:schoolExpEntities){
				if(reSchoolExpDao.updateSchoolExp(schoolExpEntity))
					returnStr += "S";
				else
					returnStr += "F";
			}
			break;
		case "science":
			List<ReScienceEntity> scienceEntities = JSON.parseArray(json, ReScienceEntity.class);
			if (scienceEntities == null) {
				return returnStr;
			}
			for(ReScienceEntity scienceEntity:scienceEntities){
				if(reScienceDao.updateScience(scienceEntity))
					returnStr += "S";
				else
					returnStr += "F";
			}
			break;
		default:
			return returnStr;
		}
		return returnStr;
	}
}
