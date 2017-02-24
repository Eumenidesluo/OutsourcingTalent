package dao;

import entity.ReEvaluationEntity;

public interface ReEvaluationDao {
	public Integer addEvaluation(ReEvaluationEntity entity);
	public void deleteEvaluation(int resumeId);
	public ReEvaluationEntity findEvaluation(int resumeId);
	public void updateEvaluation(ReEvaluationEntity entity);
}
