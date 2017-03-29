package entity;

public class RelateGroupProjectBean {

	private int projectId;
	private int hasEntrusted;
	private String projectName;
	
	public RelateGroupProjectBean(){
		
	}
	public RelateGroupProjectBean(int projectId,int hasEntrusted,String name){
		this.projectId = projectId;
		this.hasEntrusted = hasEntrusted;
		this.projectName = name;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getHasEntrusted() {
		return hasEntrusted;
	}
	public void setHasEntrusted(int hasEntrusted) {
		this.hasEntrusted = hasEntrusted;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hasEntrusted;
		result = prime * result + projectId;
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		RelateGroupProjectBean other = (RelateGroupProjectBean) obj;
		if (hasEntrusted != other.hasEntrusted)
			return false;
		if (projectId != other.projectId)
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	
}
