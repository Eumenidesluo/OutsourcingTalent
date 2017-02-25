package entity;

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
    private Integer amount;

    public GroupEntity() {
    	amount = 0;
	}
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Basic
    @Column(name = "amount")
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + groupId;
		result = prime * result + ((leaderId == null) ? 0 : leaderId.hashCode());
		result = prime * result + ((memberId1 == null) ? 0 : memberId1.hashCode());
		result = prime * result + ((memberId2 == null) ? 0 : memberId2.hashCode());
		result = prime * result + ((memberId3 == null) ? 0 : memberId3.hashCode());
		result = prime * result + ((memberId4 == null) ? 0 : memberId4.hashCode());
		result = prime * result + ((memberId5 == null) ? 0 : memberId5.hashCode());
		result = prime * result + ((memberId6 == null) ? 0 : memberId6.hashCode());
		result = prime * result + ((memberId7 == null) ? 0 : memberId7.hashCode());
		result = prime * result + ((memberId8 == null) ? 0 : memberId8.hashCode());
		result = prime * result + ((memberId9 == null) ? 0 : memberId9.hashCode());
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
		GroupEntity other = (GroupEntity) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (groupId != other.groupId)
			return false;
		if (leaderId == null) {
			if (other.leaderId != null)
				return false;
		} else if (!leaderId.equals(other.leaderId))
			return false;
		if (memberId1 == null) {
			if (other.memberId1 != null)
				return false;
		} else if (!memberId1.equals(other.memberId1))
			return false;
		if (memberId2 == null) {
			if (other.memberId2 != null)
				return false;
		} else if (!memberId2.equals(other.memberId2))
			return false;
		if (memberId3 == null) {
			if (other.memberId3 != null)
				return false;
		} else if (!memberId3.equals(other.memberId3))
			return false;
		if (memberId4 == null) {
			if (other.memberId4 != null)
				return false;
		} else if (!memberId4.equals(other.memberId4))
			return false;
		if (memberId5 == null) {
			if (other.memberId5 != null)
				return false;
		} else if (!memberId5.equals(other.memberId5))
			return false;
		if (memberId6 == null) {
			if (other.memberId6 != null)
				return false;
		} else if (!memberId6.equals(other.memberId6))
			return false;
		if (memberId7 == null) {
			if (other.memberId7 != null)
				return false;
		} else if (!memberId7.equals(other.memberId7))
			return false;
		if (memberId8 == null) {
			if (other.memberId8 != null)
				return false;
		} else if (!memberId8.equals(other.memberId8))
			return false;
		if (memberId9 == null) {
			if (other.memberId9 != null)
				return false;
		} else if (!memberId9.equals(other.memberId9))
			return false;
		return true;
	}

    
}
