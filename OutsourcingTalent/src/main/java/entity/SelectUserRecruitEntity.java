package entity;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "SELECT_USER_RECRUIT", schema = "outsourcingtalent", catalog = "")
public class SelectUserRecruitEntity {
    private int userId;
    private int recruitId;
    private int status;
    
    private Date time;

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

	@Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recruitId;
		result = prime * result + status;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		SelectUserRecruitEntity other = (SelectUserRecruitEntity) obj;
		if (recruitId != other.recruitId)
			return false;
		if (status != other.status)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

    
  
}
