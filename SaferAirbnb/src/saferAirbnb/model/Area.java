package saferAirbnb.model;

public class Area {
	int areaNo;
	District district;
	
	public Area(int areaNo, District district) {
		super();
		this.areaNo = areaNo;
		this.district = district;
	}

	public int getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(int areaNo) {
		this.areaNo = areaNo;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	

}
