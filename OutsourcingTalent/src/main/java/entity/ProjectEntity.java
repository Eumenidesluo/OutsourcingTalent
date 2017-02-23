package entity;

import javax.persistence.*;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "project", schema = "outsourcingtalent", catalog = "")
public class ProjectEntity {
    private int teamProjectId;
    private Integer isAppoint;
    private Integer isFinish;
    private String applyGroup;
    private Integer appointGroup;
    private CompanyEntity companyByCompanyId;

    @Id
    @Column(name = "teamProjectId")
    public int getTeamProjectId() {
        return teamProjectId;
    }

    public void setTeamProjectId(int teamProjectId) {
        this.teamProjectId = teamProjectId;
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

        ProjectEntity that = (ProjectEntity) o;

        if (teamProjectId != that.teamProjectId) return false;
        if (isAppoint != null ? !isAppoint.equals(that.isAppoint) : that.isAppoint != null) return false;
        if (isFinish != null ? !isFinish.equals(that.isFinish) : that.isFinish != null) return false;
        if (applyGroup != null ? !applyGroup.equals(that.applyGroup) : that.applyGroup != null) return false;
        if (appointGroup != null ? !appointGroup.equals(that.appointGroup) : that.appointGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamProjectId;
        result = 31 * result + (isAppoint != null ? isAppoint.hashCode() : 0);
        result = 31 * result + (isFinish != null ? isFinish.hashCode() : 0);
        result = 31 * result + (applyGroup != null ? applyGroup.hashCode() : 0);
        result = 31 * result + (appointGroup != null ? appointGroup.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    public CompanyEntity getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(CompanyEntity companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }
}
