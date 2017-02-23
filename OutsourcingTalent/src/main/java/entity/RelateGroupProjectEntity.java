package entity;

import javax.persistence.*;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "relate_group_project", schema = "outsourcingtalent", catalog = "")
public class RelateGroupProjectEntity {
    private int bidId;
    private Integer isEntrusted;
    private ProjectEntity projectByProjectId;
    private GroupEntity groupByGroupId;

    @Id
    @Column(name = "bidId")
    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    @Basic
    @Column(name = "isEntrusted")
    public Integer getIsEntrusted() {
        return isEntrusted;
    }

    public void setIsEntrusted(Integer isEntrusted) {
        this.isEntrusted = isEntrusted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelateGroupProjectEntity that = (RelateGroupProjectEntity) o;

        if (bidId != that.bidId) return false;
        if (isEntrusted != null ? !isEntrusted.equals(that.isEntrusted) : that.isEntrusted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bidId;
        result = 31 * result + (isEntrusted != null ? isEntrusted.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "teamProjectId")
    public ProjectEntity getProjectByProjectId() {
        return projectByProjectId;
    }

    public void setProjectByProjectId(ProjectEntity projectByProjectId) {
        this.projectByProjectId = projectByProjectId;
    }

    @ManyToOne
    @JoinColumn(name = "groupId", referencedColumnName = "groupId")
    public GroupEntity getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(GroupEntity groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }
}
