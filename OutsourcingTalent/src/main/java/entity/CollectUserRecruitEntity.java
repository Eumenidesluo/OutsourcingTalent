package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "COLLET_USER_RECRUIT", schema = "outsourcingtalent", catalog = "")
public class CollectUserRecruitEntity {
    private int userId;
    private int recruitId;
    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "recruitId")
	public int getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recruitId;
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
		CollectUserRecruitEntity other = (CollectUserRecruitEntity) obj;
		if (recruitId != other.recruitId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
    

}
