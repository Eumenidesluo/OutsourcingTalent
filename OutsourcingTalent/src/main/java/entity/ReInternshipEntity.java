package entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "re_internship", schema = "outsourcingtalent", catalog = "")
public class ReInternshipEntity {
    private int internshipId;
    private String company;
    private String position;
    private Date startTime;
    private Date endtime;
    private String city;
    private String describe;

    @Id
    @Column(name = "InternshipId")
    public int getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(int internshipId) {
        this.internshipId = internshipId;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endtime")
    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReInternshipEntity that = (ReInternshipEntity) o;

        if (internshipId != that.internshipId) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (describe != null ? !describe.equals(that.describe) : that.describe != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = internshipId;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        return result;
    }
}
