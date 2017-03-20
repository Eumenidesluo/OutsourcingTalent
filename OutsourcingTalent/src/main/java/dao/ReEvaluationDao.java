package dao;

import entity.ReEvaluationEntity;

public interface ReEvaluationDao {
	public Integer addEvaluation(ReEvaluationEntity entity);
	public Boolean deleteEvaluation(int resumeId);
	public ReEvaluationEntity findEvaluation(int resumeId);
	public Boolean updateEvaluation(ReEvaluationEntity entity);
}
