package saferAirbnb.model;

public class Neighborhood {


	protected String neighborhoodName;
	protected District district;
	
	public Neighborhood(String neighborhoodName, District district) {
		super();
		this.neighborhoodName = neighborhoodName;
		this.district = district;
	}

	public String getNeighborhoodName() {
		return neighborhoodName;
	}

	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String displayInfo() {
		return this.neighborhoodName;
	}


}
