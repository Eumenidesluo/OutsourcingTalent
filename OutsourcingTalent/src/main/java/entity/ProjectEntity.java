package entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Eumenides on 2017/2/25.
 */
@Entity
@Table(name = "project", schema = "outsourcingtalent", catalog = "")
public class ProjectEntity {
    private int projectId;
    private String description;
    private int reward;
    private Date relaseTime;
    private Date endTime;
    private Date finishTime;
    private String label;

    @Id
    @Column(name = "projectId")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "reward")
    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    @Basic
    @Column(name = "relaseTime")
    public Date getRelaseTime() {
        return relaseTime;
    }

    public void setRelaseTime(Date relaseTime) {
        this.relaseTime = relaseTime;
    }

    @Basic
    @Column(name = "endTime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "finishTime")
    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (projectId != that.projectId) return false;
        if (reward != that.reward) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (relaseTime != null ? !relaseTime.equals(that.relaseTime) : that.relaseTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + reward;
        result = 31 * result + (relaseTime != null ? relaseTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
