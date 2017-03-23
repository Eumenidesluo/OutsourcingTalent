package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import component.Education;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "RE_EDUCATION", schema = "outsourcingtalent", catalog = "")
public class ReEducationEntity {
    private int educationId;
    private int resumeId;
    private String school;
    private String city;
    private String marjor;
    private Education education;
    private Date startTime;
    private Date endTime;
    private String courses;
    private String honor;

    public ReEducationEntity() {
    		school = "";
    		city = "";
    		marjor = "";
    		education = Education.Undergraduate;
    		
			try {
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");     
	    		java.util.Date date;
				date = bartDateFormat.parse("0000-00-00");
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
	    		startTime = sqldate;
	    		endTime = sqldate;
			} catch (ParseException e) {
				e.printStackTrace();
			}  
    		
    		courses = "";
    		honor = "";
    	
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "educationId")
    public int getEducationId() {
        return educationId;
    }

    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    @Basic
    @Column(name = "resumeId")
    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
    @Column(name = "marjor")
    public String getMarjor() {
        return marjor;
    }

    public void setMarjor(String marjor) {
        this.marjor = marjor;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "education")
    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
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
    @Column(name = "endTime")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "courses")
    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    @Basic
    @Column(name = "honor")
    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReEducationEntity that = (ReEducationEntity) o;

        if (educationId != that.educationId) return false;
        if (resumeId != that.resumeId) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (marjor != null ? !marjor.equals(that.marjor) : that.marjor != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (courses != null ? !courses.equals(that.courses) : that.courses != null) return false;
        if (honor != null ? !honor.equals(that.honor) : that.honor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = educationId;
        result = 31 * result + resumeId;
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (marjor != null ? marjor.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        result = 31 * result + (honor != null ? honor.hashCode() : 0);
        return result;
    }
}
