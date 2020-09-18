package saferAirbnb.model;

import java.sql.Date;

public class Host extends Person {
	protected Date hostSince;
	protected String hostLocation;
	protected String hostAbout;
	protected String hostResponseTime;
	protected String hostResponseRate;
	protected String hostAcceptanceRate;
	protected String hostUrl;
	
	
	public Host(int personId, Date hostSince, String hostLocation, String hostAbout,
			String hostResponseTime, String hostResponseRate, String hostAcceptanceRate, String hostUrl) {
		super(personId);
		this.hostSince = hostSince;
		this.hostLocation = hostLocation;
		this.hostAbout = hostAbout;
		this.hostResponseTime = hostResponseTime;
		this.hostResponseRate = hostResponseRate;
		this.hostAcceptanceRate = hostAcceptanceRate;
		this.hostUrl = hostUrl;
	}

	public Host(int personId, String name, Date hostSince, String hostLocation, String hostAbout,
				String hostResponseTime, String hostResponseRate, String hostAcceptanceRate, String hostUrl) {
		super(personId, name);
		this.hostSince = hostSince;
		this.hostLocation = hostLocation;
		this.hostAbout = hostAbout;
		this.hostResponseTime = hostResponseTime;
		this.hostResponseRate = hostResponseRate;
		this.hostAcceptanceRate = hostAcceptanceRate;
		this.hostUrl = hostUrl;
	}

	public Host(int hostId) {
		super(hostId);
	}

	public int getHostId() {
		return this.personId;
	}

	public String displayInfo() {
		return this.getHostName();

	}

	public String getHostName() {
		return this.name;
	}
	
	public void setHostName(String name) {
		this.name = name;
	}
	
	public Date getHostSince() {
		return hostSince;
	}
	
	public void setHostSince(Date hostSince) {
		this.hostSince = hostSince;
	}
	
	public String getHostLocation() {
		return hostLocation;
	}
	
	public void setHostLocation(String hostLocation) {
		this.hostLocation = hostLocation;
	}
	
	public String getHostAbout() {
		return hostAbout;
	}
	
	public void setHostAbout(String hostAbout) {
		this.hostAbout = hostAbout;
	}
	
	public String getHostResponseTime() {
		return hostResponseTime;
	}
	
	public void setHostResponseTime(String hostResponseTime) {
		this.hostResponseTime = hostResponseTime;
	}
	
	public String getHostResponseRate() {
		return hostResponseRate;
	}
	
	public void setHostResponseRate(String hostResponseRate) {
		this.hostResponseRate = hostResponseRate;
	}
	
	public String getHostAcceptanceRate() {
		return hostAcceptanceRate;
	}
	
	public void setHostAcceptanceRate(String hostAcceptanceRate) {
		this.hostAcceptanceRate = hostAcceptanceRate;
	}
	
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	
}
