package saferAirbnb.model;

public class Person {
	protected int personId;
	protected String name;
		
	public Person(int personId, String name) {
		this.personId = personId;
		this.name = name;
	}
	
	public Person(int personId) {
		this.personId = personId;
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
