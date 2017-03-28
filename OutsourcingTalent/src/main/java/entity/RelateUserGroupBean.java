package entity;

public class RelateUserGroupBean {

	private int relateId;
    private int userId;
    private int groupId;
    private int place;
    private String name;
    
    public RelateUserGroupBean(){
    	
    }
    public RelateUserGroupBean(RelateUserGroupEntity entity,String name) {
    	relateId = entity.getRelateId();
    	userId = entity.getUserId();
    	groupId = entity.getGroupId();
    	place = entity.getPlace();
    	this.name = name;
    }
	public int getRelateId() {
		return relateId;
	}
	public void setRelateId(int relateId) {
		this.relateId = relateId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + place;
		result = prime * result + relateId;
		result = prime * result + userId;
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
		RelateUserGroupBean other = (RelateUserGroupBean) obj;
		if (groupId != other.groupId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (place != other.place)
			return false;
		if (relateId != other.relateId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
    
	
}
