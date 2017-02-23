package entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "select_user_recruit", schema = "outsourcingtalent", catalog = "")
public class SelectUserRecruitEntity {
    private int userId;
    private int status;
    private Date time;
    private CoRecruitEntity coRecruitByRecruitId;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectUserRecruitEntity that = (SelectUserRecruitEntity) o;

        if (userId != that.userId) return false;
        if (status != that.status) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + status;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "recruitId", referencedColumnName = "recruitId", nullable = false)
    public CoRecruitEntity getCoRecruitByRecruitId() {
        return coRecruitByRecruitId;
    }

    public void setCoRecruitByRecruitId(CoRecruitEntity coRecruitByRecruitId) {
        this.coRecruitByRecruitId = coRecruitByRecruitId;
    }
}
