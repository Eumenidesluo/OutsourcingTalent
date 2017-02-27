package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/2/25.
 */
@Entity
@Table(name = "project_status", schema = "outsourcingtalent", catalog = "")
public class ProjectStatusEntity {
    private int projectId;
    private Integer isAppoint;
    private Integer isFinish;
    private String applyGroup;
    private Integer appointGroup;

    public ProjectStatusEntity() {
    	isAppoint = 0;
    	isFinish = 0;
    	applyGroup = "";
	}
    @Id
    @Column(name = "projectId")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "isAppoint")
    public Integer getIsAppoint() {
        return isAppoint;
    }

    public void setIsAppoint(Integer isAppoint) {
        this.isAppoint = isAppoint;
    }

    @Basic
    @Column(name = "isFinish")
    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    @Basic
    @Column(name = "applyGroup")
    public String getApplyGroup() {
        return applyGroup;
    }

    public void setApplyGroup(String applyGroup) {
        this.applyGroup = applyGroup;
    }

    @Basic
    @Column(name = "appointGroup")
    public Integer getAppointGroup() {
        return appointGroup;
    }

    public void setAppointGroup(Integer appointGroup) {
        this.appointGroup = appointGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectStatusEntity that = (ProjectStatusEntity) o;

        if (projectId != that.projectId) return false;
        if (isAppoint != null ? !isAppoint.equals(that.isAppoint) : that.isAppoint != null) return false;
        if (isFinish != null ? !isFinish.equals(that.isFinish) : that.isFinish != null) return false;
        if (applyGroup != null ? !applyGroup.equals(that.applyGroup) : that.applyGroup != null) return false;
        if (appointGroup != null ? !appointGroup.equals(that.appointGroup) : that.appointGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + (isAppoint != null ? isAppoint.hashCode() : 0);
        result = 31 * result + (isFinish != null ? isFinish.hashCode() : 0);
        result = 31 * result + (applyGroup != null ? applyGroup.hashCode() : 0);
        result = 31 * result + (appointGroup != null ? appointGroup.hashCode() : 0);
        return result;
    }
}
