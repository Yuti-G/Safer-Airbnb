package saferAirbnb.dal;

import saferAirbnb.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ReviewDao {
protected ConnectionManager connectionManager;
	

	private static ReviewDao instance = null;
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	public Review create(Review review) throws SQLException {
		String insertReview = "INSERT INTO Review(Date,Comments,ListingId,GuestId) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setDate(1, review.getDate());
			insertStmt.setString(2, review.getComments());
			insertStmt.setInt(3, review.getListing().getListingId());
			insertStmt.setInt(4,  review.getPerson().getPersonId());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reviewKey = -1;
			if (resultKey.next()) {
				reviewKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewKey(reviewKey);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Review getReviewById(int reviewId) throws SQLException {
		String select = "SELECT Date,Comments,ListingId,GuestId FROM Review WHERE ReviewKey =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			
			ListingDao listingDao = ListingDao.getInstance();
			PersonDao personDao = PersonDao.getInstance();
			
			if (results.next()) {
				Date date = results.getDate("Date");
				String comments = results.getString("Comments");
				int listingId = results.getInt("ListingId");
				int guestId = results.getInt("GuestId");
				
				Listing listing = listingDao.getListingById(listingId);
				Person person = personDao.getPersonById(guestId);
				
				return new Review(reviewId,date,comments,listing,person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Review> getReviewsByListingId(int listingId) throws SQLException {
		List<Review> list = new ArrayList<>();
		String select = "SELECT ReviewKey,Date,Comments,GuestId FROM Review LEFT JOIN Listing ON Listing.ListingId = Review.ListingId WHERE Review.ListingId =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			
			ListingDao listingDao = ListingDao.getInstance();
			PersonDao personDao = PersonDao.getInstance();
			
			while (results.next()) {
				int key = results.getInt("ReviewKey");
				Date date = results.getDate("Date");
				String comments = results.getString("Comments");
				int guestId = results.getInt("GuestId");
				
				Listing listing = listingDao.getListingById(listingId);
				Person person = personDao.getPersonById(guestId);
				
				Review review = new Review(key,date,comments,listing,person);
				
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return list;
	}
	
	public List<Review> getReviewsByGuestId(int guestId) throws SQLException {
		List<Review> list = new ArrayList<>();
		String select = "SELECT ReviewKey,Date,Comments,Review.ListingId FROM Review LEFT JOIN Listing ON Listing.ListingId = Review.ListingId WHERE Review.GuestId =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, guestId);
			results = selectStmt.executeQuery();
			
			ListingDao listingDao = ListingDao.getInstance();
			PersonDao personDao = PersonDao.getInstance();
			
			while (results.next()) {
				int key = results.getInt("ReviewKey");
				Date date = results.getDate("Date");
				String comments = results.getString("Comments");
				int listingId = results.getInt("ListingId");
				
				Listing listing = listingDao.getListingById(listingId);
				Person person = personDao.getPersonById(guestId);
				
				Review review = new Review(key,date,comments,listing,person);
				
				list.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return list;
	}
	
	public Review updateContent(Review review, String newContent) throws SQLException {
		String update = "UPDATE Review SET Comments=? WHERE ReviewKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
			updateStmt.setString(1, newContent);
			updateStmt.setInt(2, review.getReviewKey());
			updateStmt.executeUpdate();
			
			review.setComments(newContent);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Review delete(Review review) throws SQLException {
		String delete= "DELETE FROM Review WHERE ReviewKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, review.getReviewKey());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
