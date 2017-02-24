package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Eumenides on 2017/2/23.
 */
@Entity
@Table(name = "relate_user_group", schema = "outsourcingtalent", catalog = "")
public class RelateUserGroupEntity {
    private int userId;
    private int groupId;
    private String index;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "groupId")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "index")
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelateUserGroupEntity that = (RelateUserGroupEntity) o;

        if (userId != that.userId) return false;
        if (groupId != that.groupId) return false;
        return index != null ? index.equals(that.index) : that.index == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + groupId;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }
}
