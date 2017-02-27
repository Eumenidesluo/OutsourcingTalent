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
@Table(name = "relate_group_project", schema = "outsourcingtalent", catalog = "")
public class RelateGroupProjectEntity {
    private int bidId;
    private int projectId;
    private int groupId;
    private Integer isEntrusted;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "bidId")
    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
    
    @Basic
    @Column(name = "projectId")
    public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Basic
	@Column(name = "groupId")
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bidId;
		result = prime * result + groupId;
		result = prime * result + ((isEntrusted == null) ? 0 : isEntrusted.hashCode());
		result = prime * result + projectId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelateGroupProjectEntity other = (RelateGroupProjectEntity) obj;
		if (bidId != other.bidId)
			return false;
		if (groupId != other.groupId)
			return false;
		if (isEntrusted == null) {
			if (other.isEntrusted != null)
				return false;
		} else if (!isEntrusted.equals(other.isEntrusted))
			return false;
		if (projectId != other.projectId)
			return false;
		return true;
	}

    
}
