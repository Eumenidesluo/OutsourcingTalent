package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "RE_EVALUATION", schema = "outsourcingtalent", catalog = "")
public class ReEvaluationEntity {
    private int resumeId;
    private String evaluation;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "resumeId")
    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    @Basic
    @Column(name = "evaluation")
    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReEvaluationEntity that = (ReEvaluationEntity) o;

        if (resumeId != that.resumeId) return false;
        if (evaluation != null ? !evaluation.equals(that.evaluation) : that.evaluation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resumeId;
        result = 31 * result + (evaluation != null ? evaluation.hashCode() : 0);
        return result;
    }
}
