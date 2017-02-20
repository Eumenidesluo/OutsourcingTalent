package entity;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Eumenides on 2017/2/19.
 */
@Entity
@Table(name = "user", schema = "outsourcingtalent", catalog = "")
public class UserEntity {
    private int id;
    private String password;
    private String email;
    private int status;
    private String validateCode;
    private Timestamp registerTime;
//    private PersonalInfEntity personalInfByEmail;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "validateCode")
    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Basic
    @Column(name = "registerTime")
    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (validateCode != null ? !validateCode.equals(that.validateCode) : that.validateCode != null) return false;
        if (registerTime != null ? !registerTime.equals(that.registerTime) : that.registerTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (validateCode != null ? validateCode.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        return result;
    }

//    @OneToOne
//    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
//    public PersonalInfEntity getPersonalInfByEmail() {
//        return personalInfByEmail;
//    }
//
//    public void setPersonalInfByEmail(PersonalInfEntity personalInfByEmail) {
//        this.personalInfByEmail = personalInfByEmail;
//    }

    @Transient
    public Date getLastActivateTime() {
        Calendar cl = Calendar.getInstance();
        cl.setTime(registerTime);
        cl.add(Calendar.DATE , 2);
        return cl.getTime();
    }
}
