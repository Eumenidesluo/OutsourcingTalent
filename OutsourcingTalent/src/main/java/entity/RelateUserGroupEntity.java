package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/2/23.
 */
@Entity
@Table(name = "relate_user_group", schema = "outsourcingtalent", catalog = "")
public class RelateUserGroupEntity {
	private int relateId;
    private int userId;
    private int groupId;
    private int index;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "relateId")
    public int getRelateId() {
		return relateId;
	}

	public void setRelateId(int relateId) {
		this.relateId = relateId;
	}

	@Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    @Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupId;
		result = prime * result + index;
		result = prime * result + relateId;
		result = prime * result + userId;
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
		RelateUserGroupEntity other = (RelateUserGroupEntity) obj;
		if (groupId != other.groupId)
			return false;
		if (index != other.index)
			return false;
		if (relateId != other.relateId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
}
