package entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Eumenides on 2017/2/19.
 */
@Entity
@Table(name = "personal_inf", schema = "outsourcingtalent", catalog = "")
public class PersonalInfEntity {
    private String email;
    private String name;
    private String address;
    private Date birthday;
    private String education;
    private String graduationTime;
    private String school;
    private String major;
    private String phoneNumber;
    private String sex;

    @Id
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    @Column(name = "graduationTime")
    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
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
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "PhoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalInfEntity entity = (PersonalInfEntity) o;

        if (email != null ? !email.equals(entity.email) : entity.email != null) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (address != null ? !address.equals(entity.address) : entity.address != null) return false;
        if (birthday != null ? !birthday.equals(entity.birthday) : entity.birthday != null) return false;
        if (education != null ? !education.equals(entity.education) : entity.education != null) return false;
        if (graduationTime != null ? !graduationTime.equals(entity.graduationTime) : entity.graduationTime != null)
            return false;
        if (school != null ? !school.equals(entity.school) : entity.school != null) return false;
        if (major != null ? !major.equals(entity.major) : entity.major != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(entity.phoneNumber) : entity.phoneNumber != null) return false;
        if (sex != null ? !sex.equals(entity.sex) : entity.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (graduationTime != null ? graduationTime.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }
}
