package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
@Table(name = "RE_EDUCATION", schema = "outsourcingtalent", catalog = "")
public class ReEducationEntity {
    private int educationId;
    private int resumeId;
    private String school;
    private String city;
    private String marjor;
    private String education;
    private Date startTime;
    private Date endTime;
    private String courses;
    private String honor;

    public ReEducationEntity() {
    		school = "";
    		city = "";
    		marjor = "";
    		education = "";
    		
			try {
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				String nowDate = bartDateFormat.format(new java.util.Date(System.currentTimeMillis()));
	    		java.util.Date date;
				date = bartDateFormat.parse(nowDate);
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
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + educationId;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((honor == null) ? 0 : honor.hashCode());
		result = prime * result + ((marjor == null) ? 0 : marjor.hashCode());
		result = prime * result + resumeId;
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		ReEducationEntity other = (ReEducationEntity) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (education == null) {
			if (other.education != null)
				return false;
		} else if (!education.equals(other.education))
			return false;
		if (educationId != other.educationId)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (honor == null) {
			if (other.honor != null)
				return false;
		} else if (!honor.equals(other.honor))
			return false;
		if (marjor == null) {
			if (other.marjor != null)
				return false;
		} else if (!marjor.equals(other.marjor))
			return false;
		if (resumeId != other.resumeId)
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

    
}
