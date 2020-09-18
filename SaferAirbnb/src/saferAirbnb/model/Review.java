package saferAirbnb.model;

import java.sql.Date;

public class Review {
	protected int reviewKey;
	protected Date date;
	protected String comments;

	protected Listing listing;
	protected Person person;
	
	public Review(int reviewKey) {
		this.reviewKey = reviewKey;
	}
	
	public Review(int reviewKey, Date date, String comments, Listing listing, Person person) {
		super();
		this.reviewKey = reviewKey;
		this.date = date;
		this.comments = comments;
		this.listing = listing;
		this.person = person;
	}

	public Review(Date date, String comments, Listing listing, Person person) {
		super();
		this.date = date;
		this.comments = comments;
		this.listing = listing;
		this.person = person;
	}

	public int getReviewKey() {
		return reviewKey;
	}

	public void setReviewKey(int reviewKey) {
		this.reviewKey = reviewKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
