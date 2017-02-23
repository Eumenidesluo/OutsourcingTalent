package entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "re_school_exp", schema = "outsourcingtalent", catalog = "")
public class ReSchoolExpEntity {
    private int schoolExpId;
    private String activity;
    private String role;
    private Date start;
    private Date end;
    private String city;
    private String description;

    @Id
    @Column(name = "school_exp_Id")
    public int getSchoolExpId() {
        return schoolExpId;
    }

    public void setSchoolExpId(int schoolExpId) {
        this.schoolExpId = schoolExpId;
    }

    @Basic
    @Column(name = "activity")
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "start")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReSchoolExpEntity that = (ReSchoolExpEntity) o;

        if (schoolExpId != that.schoolExpId) return false;
        if (activity != null ? !activity.equals(that.activity) : that.activity != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schoolExpId;
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
