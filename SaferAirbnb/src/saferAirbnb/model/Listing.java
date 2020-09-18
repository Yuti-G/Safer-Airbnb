package saferAirbnb.model;

public class Listing {
	protected int listingId;
	protected String name;
	protected String summary;
	protected String url;
	protected Host host;
	protected boolean availability;
	protected PropertyType propertyType;

    public enum PropertyType {
		Villa("Villa"), House("House"), Apartment("Apartment"), Condominium("Condominium"),
		Townhouse("Townhouse"), BedBreakfast("Bed & Breakfast"), Loft("Loft"),
		Boat("Boat"), Other("Other"), Guesthouse("Guesthouse"), Dorm("Dorm"),
		CamperRV("Camper/RV"), EntireFloor("Entire Floor"), None("");

		private String sqlName;
		PropertyType(String s) {
			this.sqlName = s;
		}

		public static PropertyType getEnum(String s) {
			for (PropertyType t : PropertyType.values()) {
				if (t.getSqlName().equalsIgnoreCase(s)) {
					return t;
				}
			}
			throw new IllegalArgumentException();
		}

		public String getSqlName() {
			return this.sqlName;
		}
	}
	protected RoomType roomType;
	public enum RoomType {
		EntireHomeApt("Entire home/apt"), PrivateRoom("Private room"), SharedRoom("Shared room");

		private String sqlName;
		RoomType(String s) {
			this.sqlName = s;
		}

		public static RoomType getEnum(String s) {
			for (RoomType t : RoomType.values()) {
				if (t.getSqlName().equalsIgnoreCase(s)) {
					return t;
				}
			}
			throw new IllegalArgumentException();
		}

		public String getSqlName() {
			return this.sqlName;
		}
	}
	protected int accommodates;
	protected double bathrooms;
	protected double bedrooms;
	protected int beds;
	protected double prices;
	protected double securityDeposit;
	protected int minimumNights;
	protected int maximumNights;
	protected Location location;
	protected Neighborhood neighborhood;

	public Listing(int listingId, String name, String summary, String url, Host host, boolean availability,
			PropertyType propertyType, RoomType roomType, int accommodates, double bathrooms, double bedrooms, int beds,
			double prices, double securityDeposit, int minimumNights, int maximumNights, Location location,
			Neighborhood neighborhood) {
		super();
		this.listingId = listingId;
		this.name = name;
		this.summary = summary;
		this.url = url;
		this.host = host;
		this.availability = availability;
		this.propertyType = propertyType;
		this.roomType = roomType;
		this.accommodates = accommodates;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.prices = prices;
		this.securityDeposit = securityDeposit;
		this.minimumNights = minimumNights;
		this.maximumNights = maximumNights;
		this.location = location;
		this.neighborhood = neighborhood;
	}

	public Listing(int listingId) {
		this.listingId = listingId;
	}

	public String availabilityStr() {
		if (this.availability) {
			return "Yes";
		}
		return "No";
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public int getAccommodates() {
		return accommodates;
	}

	public void setAccommodates(int accommodates) {
		this.accommodates = accommodates;
	}

	public double getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(double bathrooms) {
		this.bathrooms = bathrooms;
	}

	public double getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(double bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public double getPrices() {
		return prices;
	}

	public void setPrices(double prices) {
		this.prices = prices;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public int getMinimumNights() {
		return minimumNights;
	}

	public void setMinimumNights(int minimumNights) {
		this.minimumNights = minimumNights;
	}

	public int getMaximumNights() {
		return maximumNights;
	}

	public void setMaximumNights(int maximumNights) {
		this.maximumNights = maximumNights;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
}
