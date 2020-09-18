package saferAirbnb.model;

import java.sql.Date;

public class Crime {
	int crimeKey;
	String incidentNumber;
	Date dateTime;
	String description;
	OffenseCode offenseCode;
	Location location;
	boolean shooting;
	
	public Crime(int crimeKey, String incidentNumber, Date dateTime, String description, OffenseCode offenseCode,
			Location location, boolean shooting) {
		super();
		this.crimeKey = crimeKey;
		this.incidentNumber = incidentNumber;
		this.dateTime = dateTime;
		this.description = description;
		this.offenseCode = offenseCode;
		this.location = location;
		this.shooting = shooting;
	}

	public Crime(String incidentNumber, Date dateTime, String description, OffenseCode offenseCode,
			Location location, boolean shooting) {
		super();
		this.incidentNumber = incidentNumber;
		this.dateTime = dateTime;
		this.description = description;
		this.offenseCode = offenseCode;
		this.location = location;
		this.shooting = shooting;
	}

	public Crime(int crimeKey) {
		this.crimeKey = crimeKey;
	}

	public int getCrimeKey() {
		return crimeKey;
	}

	public void setCrimeKey(int crimeKey) {
		this.crimeKey = crimeKey;
	}

	public String getIncidentNumber() {
		return incidentNumber;
	}

	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OffenseCode getOffenseCode() {
		return offenseCode;
	}

	public void setOffenseCode(OffenseCode offenseCode) {
		this.offenseCode = offenseCode;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	
	
	
	
}
