package saferAirbnb.model;

public class OffenseCodeGroup {
	int offenseCodeGroupKey;
	String groupName;
	
	public OffenseCodeGroup(int offenseCodeGroupKey, String groupName) {
		super();
		this.offenseCodeGroupKey = offenseCodeGroupKey;
		this.groupName = groupName;
	}

	public OffenseCodeGroup(String groupName) {
		super();
		this.groupName = groupName;
	}

	public int getOffenseCodeGroupKey() {
		return offenseCodeGroupKey;
	}

	public void setOffenseCodeGroupKey(int offenseCodeGroupKey) {
		this.offenseCodeGroupKey = offenseCodeGroupKey;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
