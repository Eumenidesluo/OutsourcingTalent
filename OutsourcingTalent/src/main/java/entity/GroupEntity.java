package entity;

import javax.persistence.*;

/**
 * Created by Eumenides on 2017/2/22.
 */
@Entity
@Table(name = "group", schema = "outsourcingtalent", catalog = "")
public class GroupEntity {
    private int groupId;
    private Integer leaderId;
    private Integer memberId1;
    private Integer memberId2;
    private Integer memberId3;
    private Integer memberId4;
    private Integer memberId5;
    private Integer memberId6;
    private Integer memberId7;
    private Integer memberId8;
    private Integer memberId9;

    @Id
    @Column(name = "groupId")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "leaderId")
    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    @Basic
    @Column(name = "memberId_1")
    public Integer getMemberId1() {
        return memberId1;
    }

    public void setMemberId1(Integer memberId1) {
        this.memberId1 = memberId1;
    }

    @Basic
    @Column(name = "memberId_2")
    public Integer getMemberId2() {
        return memberId2;
    }

    public void setMemberId2(Integer memberId2) {
        this.memberId2 = memberId2;
    }

    @Basic
    @Column(name = "memberId_3")
    public Integer getMemberId3() {
        return memberId3;
    }

    public void setMemberId3(Integer memberId3) {
        this.memberId3 = memberId3;
    }

    @Basic
    @Column(name = "memberId_4")
    public Integer getMemberId4() {
        return memberId4;
    }

    public void setMemberId4(Integer memberId4) {
        this.memberId4 = memberId4;
    }

    @Basic
    @Column(name = "memberId_5")
    public Integer getMemberId5() {
        return memberId5;
    }

    public void setMemberId5(Integer memberId5) {
        this.memberId5 = memberId5;
    }

    @Basic
    @Column(name = "memberId_6")
    public Integer getMemberId6() {
        return memberId6;
    }

    public void setMemberId6(Integer memberId6) {
        this.memberId6 = memberId6;
    }

    @Basic
    @Column(name = "memberId_7")
    public Integer getMemberId7() {
        return memberId7;
    }

    public void setMemberId7(Integer memberId7) {
        this.memberId7 = memberId7;
    }

    @Basic
    @Column(name = "memberId_8")
    public Integer getMemberId8() {
        return memberId8;
    }

    public void setMemberId8(Integer memberId8) {
        this.memberId8 = memberId8;
    }

    @Basic
    @Column(name = "memberId_9")
    public Integer getMemberId9() {
        return memberId9;
    }

    public void setMemberId9(Integer memberId9) {
        this.memberId9 = memberId9;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntity that = (GroupEntity) o;

        if (groupId != that.groupId) return false;
        if (leaderId != null ? !leaderId.equals(that.leaderId) : that.leaderId != null) return false;
        if (memberId1 != null ? !memberId1.equals(that.memberId1) : that.memberId1 != null) return false;
        if (memberId2 != null ? !memberId2.equals(that.memberId2) : that.memberId2 != null) return false;
        if (memberId3 != null ? !memberId3.equals(that.memberId3) : that.memberId3 != null) return false;
        if (memberId4 != null ? !memberId4.equals(that.memberId4) : that.memberId4 != null) return false;
        if (memberId5 != null ? !memberId5.equals(that.memberId5) : that.memberId5 != null) return false;
        if (memberId6 != null ? !memberId6.equals(that.memberId6) : that.memberId6 != null) return false;
        if (memberId7 != null ? !memberId7.equals(that.memberId7) : that.memberId7 != null) return false;
        if (memberId8 != null ? !memberId8.equals(that.memberId8) : that.memberId8 != null) return false;
        if (memberId9 != null ? !memberId9.equals(that.memberId9) : that.memberId9 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (leaderId != null ? leaderId.hashCode() : 0);
        result = 31 * result + (memberId1 != null ? memberId1.hashCode() : 0);
        result = 31 * result + (memberId2 != null ? memberId2.hashCode() : 0);
        result = 31 * result + (memberId3 != null ? memberId3.hashCode() : 0);
        result = 31 * result + (memberId4 != null ? memberId4.hashCode() : 0);
        result = 31 * result + (memberId5 != null ? memberId5.hashCode() : 0);
        result = 31 * result + (memberId6 != null ? memberId6.hashCode() : 0);
        result = 31 * result + (memberId7 != null ? memberId7.hashCode() : 0);
        result = 31 * result + (memberId8 != null ? memberId8.hashCode() : 0);
        result = 31 * result + (memberId9 != null ? memberId9.hashCode() : 0);
        return result;
    }
}
