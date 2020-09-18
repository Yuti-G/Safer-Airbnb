package saferAirbnb.model;

public class District {
	int districtKey;
	String districtCode;
	String districtName;
	
	public District(int districtKey, String districtCode, String districtName) {
		this.districtKey = districtKey;
		this.districtCode = districtCode;
		this.districtName = districtName;
	}

	public District(String districtCode, String districtName) {
		this.districtCode = districtCode;
		this.districtName = districtName;
	}

	public int getDistrictKey() {
		return districtKey;
	}

	public void setDistrictKey(int districtKey) {
		this.districtKey = districtKey;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	
	
	
	
}
