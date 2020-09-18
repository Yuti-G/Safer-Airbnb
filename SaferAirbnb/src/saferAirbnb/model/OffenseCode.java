package saferAirbnb.model;

public class OffenseCode {
	int offenseCodeKey;
	String name;
	OffenseCodeGroup offenseCodeGroup;
	
	public OffenseCode(int offenseCodeKey, String name, OffenseCodeGroup offenseCodeGroup) {
		super();
		this.offenseCodeKey = offenseCodeKey;
		this.name = name;
		this.offenseCodeGroup = offenseCodeGroup;
	}

	public OffenseCode(String name, OffenseCodeGroup offenseCodeGroup) {
		super();
		this.name = name;
		this.offenseCodeGroup = offenseCodeGroup;
	}

	public int getOffenseCodeKey() {
		return offenseCodeKey;
	}

	public void setOffenseCodeKey(int offenseCodeKey) {
		this.offenseCodeKey = offenseCodeKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OffenseCodeGroup getOffenseCodeGroup() {
		return offenseCodeGroup;
	}

	public void setOffenseCodeGroup(OffenseCodeGroup offenseCodeGroup) {
		this.offenseCodeGroup = offenseCodeGroup;
	}

}
