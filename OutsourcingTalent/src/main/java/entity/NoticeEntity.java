package entity;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/3/12.
 */
@Entity
@Table(name = "notice", schema = "outsourcingtalent", catalog = "")
public class NoticeEntity {
    private int noticeId;
    private int userId;
    private String notice;
    private Timestamp time;

    @Id
    @Column(name = "noticeId")
    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
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
    @Column(name = "notice")
    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notice == null) ? 0 : notice.hashCode());
		result = prime * result + noticeId;
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
		NoticeEntity other = (NoticeEntity) obj;
		if (notice == null) {
			if (other.notice != null)
				return false;
		} else if (!notice.equals(other.notice))
			return false;
		if (noticeId != other.noticeId)
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
