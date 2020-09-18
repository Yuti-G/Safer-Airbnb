package saferAirbnb.model;

public class Location {
	int locationKey;
	String address1;
	String address2;
	double latitude;
	double longitude;
	String city;
	String state;
	Area area;
	
	public Location(int locationKey, String address1, String address2, double latitude, double longitude, String city,
			String state, Area area) {
		super();
		this.locationKey = locationKey;
		this.address1 = address1;
		this.address2 = address2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.area = area;
	}

	public Location(String address1, String address2, double latitude, double longitude, String city, String state,
			Area area) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.area = area;
	}

	public int getLocationKey() {
		return locationKey;
	}

	public void setLocationKey(int locationKey) {
		this.locationKey = locationKey;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String displayInfo() {
		return this.address1 + ' ' + this.city + ' ' + this.state;
	}
	
	
}
