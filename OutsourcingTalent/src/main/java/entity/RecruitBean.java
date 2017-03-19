package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class RecruitBean {

	private int recruitId;
    private String companyName;
    private String title;
    private String address;
    private BigDecimal salary;
    private String education;
    private String description;
    private Date finalTime;
    private String label;
    
    public RecruitBean() {
    	
    	address = "";
    	salary = new BigDecimal("0.00");
    	education  = "";
    	description = "";
    	label = "";
    	SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		try {      
			java.util.Date date = bartDateFormat.parse("9999-12-31");  
			Date sqldate = new Date(date.getTime());
			finalTime = sqldate;
		}catch(Exception e){
			e.printStackTrace();
		}
    	
	}
    public RecruitBean(CoRecruitEntity entity,String companyName){
    	this.companyName = companyName;
    	this.recruitId = entity.getRecruitId();
    	this.title = entity.getTitle();
    	this.address = entity.getAddress();
    	this.salary = entity.getSalary();
    	this.education = entity.getEducation();
    	this.description = entity.getDescription();
    	this.finalTime = entity.getFinalTime();
    	this.label = entity.getLabel();
    }

	public int getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(Date finalTime) {
		this.finalTime = finalTime;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((education == null) ? 0 : education.hashCode());
		result = prime * result + ((finalTime == null) ? 0 : finalTime.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + recruitId;
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		RecruitBean other = (RecruitBean) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (education == null) {
			if (other.education != null)
				return false;
		} else if (!education.equals(other.education))
			return false;
		if (finalTime == null) {
			if (other.finalTime != null)
				return false;
		} else if (!finalTime.equals(other.finalTime))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (recruitId != other.recruitId)
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

    
}
