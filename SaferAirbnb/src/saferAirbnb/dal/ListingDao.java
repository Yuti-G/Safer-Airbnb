package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import saferAirbnb.model.*;

public class ListingDao {
protected ConnectionManager connectionManager;
	
	private static ListingDao instance = null;
	protected ListingDao() {
		connectionManager = new ConnectionManager();
	}
	public static ListingDao getInstance() {
		if(instance == null) {
			instance = new ListingDao();
		}
		return instance;
	}
	
	public Listing create(Listing listing) throws SQLException {
		String insertListing = "INSERT INTO Listing(ListingId, Name,Summary,URL,HostId,Availability,PropertyType,RoomType,Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,LocationKey,Neighborhood) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertListing);

			insertStmt.setInt(1, listing.getListingId());
			insertStmt.setString(2, listing.getName());
			insertStmt.setString(3, listing.getSummary());
			insertStmt.setString(4, listing.getUrl());
			insertStmt.setInt(5, listing.getHost().getHostId());
			insertStmt.setBoolean(6, listing.isAvailability());
			insertStmt.setString(7, listing.getPropertyType().getSqlName());
			insertStmt.setString(8, listing.getRoomType().getSqlName());
			insertStmt.setInt(9, listing.getAccommodates());
			insertStmt.setDouble(10, listing.getBathrooms());
			insertStmt.setDouble(11, listing.getBedrooms());
			insertStmt.setInt(12, listing.getBeds());
			insertStmt.setDouble(13,listing.getPrices());
			insertStmt.setDouble(14, listing.getSecurityDeposit());
			insertStmt.setInt(15, listing.getMinimumNights());
			insertStmt.setInt(16, listing.getMaximumNights());
			insertStmt.setInt(17, listing.getLocation().getLocationKey());
			insertStmt.setString(18, listing.getNeighborhood().getNeighborhoodName());
			
			insertStmt.executeUpdate();

			return listing;
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
	
	public Listing getListingById(int listingId) throws SQLException {
		String selectListing = "SELECT ListingId,Name,Summary,URL,HostId,Availability,PropertyType,RoomType," +
				"Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,LocationKey," +
				"Neighborhood FROM Listing WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, listingId);

			results = selectStmt.executeQuery();
			HostDao hostDao = HostDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			NeighborhoodDao neighborHoodDao = NeighborhoodDao.getInstance();

			if(results.next()) {
				int curListingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String summary = results.getString("Summary");
				String url = results.getString("URL");
				int hostId = results.getInt("HostId");
				boolean availability = results.getBoolean("Availability");
				Listing.PropertyType propertyType = Listing.PropertyType.getEnum(results.getString("PropertyType"));
				Listing.RoomType roomType = Listing.RoomType.getEnum(results.getString("RoomType"));
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				float bedrooms = results.getFloat("Bedrooms");
				int beds = results.getInt("Beds");
				float price = results.getFloat("Price");
				float deposit = results.getFloat("SecurityDeposit");
				int min = results.getInt("MinimumNights");
				int max = results.getInt("MaximumNights");
				String neigh = results.getString("Neighborhood");
				int locationKey = results.getInt("LocationKey");
				
				Host host = hostDao.getHostById(hostId);
				Location location = locationDao.getLocationById(locationKey);
				Neighborhood neighborHood = neighborHoodDao.getNeighborhoodByName(neigh);
				Listing listing = new Listing(curListingId,name,summary,url,host,availability,
						propertyType,roomType,accommodates,bathrooms,bedrooms,beds,price,deposit,
						min,max,location,neighborHood);
				return listing;
			}
		}  catch (SQLException e) {
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
	
	public Listing getListingByLocationId(int locationKey) throws SQLException {
		String selectListing = "SELECT ListingId,Name,Summary,URL,HostId,Availability,PropertyType,RoomType,Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,Neighborhood FROM Listing WHERE LocationKey=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, locationKey);

			results = selectStmt.executeQuery();
			HostDao hostDao = HostDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();

			if(results.next()) {
				int curListingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String summary = results.getString("Summary");
				String url = results.getString("URL");
				int hostId = results.getInt("HostId");
				boolean availability = results.getBoolean("Availability");
				Listing.PropertyType propertyType = Listing.PropertyType.getEnum(results.getString("PropertyType"));
				Listing.RoomType roomType = Listing.RoomType.getEnum(results.getString("RoomType"));
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				float bedrooms = results.getFloat("Bedrooms");
				int beds = results.getInt("Beds");
				float price = results.getFloat("Price");
				float deposit = results.getFloat("SecurityDeposit");
				int min = results.getInt("MinimumNights");
				int max = results.getInt("MaximumNights");
				String neigh = results.getString("Neighborhood");
				
				Host host = hostDao.getHostById(hostId);
				Location location = locationDao.getLocationById(locationKey);
				Neighborhood neighbor = neighborhoodDao.getNeighborhoodByName(neigh);
				
				Listing listing = new Listing(curListingId,name,summary,url,host,availability,propertyType,roomType,
						accommodates,bathrooms,bedrooms,beds,price,deposit,min,max,location,neighbor);
				return listing;
			}
		}  catch (SQLException e) {
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

	public List<Listing> getListingsById(int listingId) throws SQLException {
		List<Listing> list = new ArrayList<Listing>();
		String selectListing = "SELECT ListingId,Name,Summary,URL,HostId,Availability,PropertyType,RoomType," +
				"Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,LocationKey," +
				"Neighborhood FROM Listing WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, listingId);

			results = selectStmt.executeQuery();
			HostDao hostDao = HostDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			NeighborhoodDao neighborHoodDao = NeighborhoodDao.getInstance();

			if(results.next()) {
				int curListingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String summary = results.getString("Summary");
				String url = results.getString("URL");
				int hostId = results.getInt("HostId");
				boolean availability = results.getBoolean("Availability");
				Listing.PropertyType propertyType = Listing.PropertyType.getEnum(results.getString("PropertyType"));
				Listing.RoomType roomType = Listing.RoomType.getEnum(results.getString("RoomType"));
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				float bedrooms = results.getFloat("Bedrooms");
				int beds = results.getInt("Beds");
				float price = results.getFloat("Price");
				float deposit = results.getFloat("SecurityDeposit");
				int min = results.getInt("MinimumNights");
				int max = results.getInt("MaximumNights");
				String neigh = results.getString("Neighborhood");
				int locationKey = results.getInt("LocationKey");

				Host host = hostDao.getHostById(hostId);
				Location location = locationDao.getLocationById(locationKey);
				Neighborhood neighborHood = neighborHoodDao.getNeighborhoodByName(neigh);
				Listing listing = new Listing(curListingId,name,summary,url,host,availability,
						propertyType,roomType,accommodates,bathrooms,bedrooms,beds,price,deposit,
						min,max,location,neighborHood);

				list.add(listing);
			}
		}  catch (SQLException e) {
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
	
	public List<Listing> getListingsByPropertyType(Listing.PropertyType propertyType) throws SQLException {
		List<Listing> list = new ArrayList<Listing>();
		String select = "SELECT ListingId,Name,Summary,URL,HostId,Availability,RoomType,Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,LocationKey,Neighborhood FROM Listing WHERE PropertyType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, propertyType.name());
			results = selectStmt.executeQuery();
			
			LocationDao locationDao = LocationDao.getInstance();
			HostDao hostDao = HostDao.getInstance();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			
			while (results.next()) {
				int listingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String summary = results.getString("Summary");
				String url = results.getString("URL");
				int hostId = results.getInt("HostId");
				boolean availability = results.getBoolean("Availability");
				Listing.RoomType roomType = Listing.RoomType.getEnum(results.getString("RoomType"));
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				float bedrooms = results.getFloat("Bedrooms");
				int beds = results.getInt("Beds");
				float price = results.getFloat("Price");
				float deposit = results.getFloat("SecurityDeposit");
				int min = results.getInt("MinimumNights");
				int max = results.getInt("MaximumNights");
				int locationKey= results.getInt("LocationKey");
				String neigh = results.getString("Neighborhood");
				
				Host host = hostDao.getHostById(hostId);
				Location location = locationDao.getLocationById(locationKey);
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByName(neigh);
				
				Listing listing = new Listing(listingId,name,summary,url,host,availability,propertyType,roomType,accommodates,bathrooms,bedrooms,beds,price,deposit,min,max,location,neighborhood);
				
				list.add(listing);
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
	
	public List<Listing> getListingsByRoomType(Listing.RoomType roomType) throws SQLException {
		List<Listing> list = new ArrayList<Listing>();
		String select = "SELECT ListingId,Name,Summary,URL,HostId,Availability,PropertyType,Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,LocationKey,Neighborhood FROM Listing WHERE RoomType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, roomType.name());
			results = selectStmt.executeQuery();
			
			LocationDao locationDao = LocationDao.getInstance();
			HostDao hostDao = HostDao.getInstance();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			
			while (results.next()) {
				int listingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String summary = results.getString("Summary");
				String url = results.getString("URL");
				int hostId = results.getInt("HostId");
				boolean availability = results.getBoolean("Availability");
				Listing.PropertyType propertyType = Listing.PropertyType.getEnum(results.getString("PropertyType"));
				
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				float bedrooms = results.getFloat("Bedrooms");
				int beds = results.getInt("Beds");
				float price = results.getFloat("Price");
				float deposit = results.getFloat("SecurityDeposit");
				int min = results.getInt("MinimumNights");
				int max = results.getInt("MaximumNights");
				int locationKey = results.getInt("LocationKey");
				String neigh = results.getString("Neighborhood");
				
				Host host = hostDao.getHostById(hostId);
				Location location = locationDao.getLocationById(locationKey);
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByName(neigh);
				
				
				Listing listing = new Listing(listingId,name,summary,url,host,availability,propertyType,roomType,accommodates,bathrooms,bedrooms,beds,price,deposit,min,max,location,neighborhood);
				list.add(listing);
			}
		}  catch (SQLException e) {
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
	
	public List<Listing> getListingsByNeighborhood(String neighborhood) throws SQLException {
		List<Listing> list = new ArrayList<Listing>();
		String select = "SELECT ListingId,Name,Summary,URL,HostId,Availability,PropertyType,RoomType,Accommodates,Bathrooms,Bedrooms,Beds,Price,SecurityDeposit,MinimumNights,MaximumNights,LocationKey,Neighborhood FROM Listing WHERE Neighborhood=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, neighborhood);
			results = selectStmt.executeQuery();
			
			LocationDao locationDao = LocationDao.getInstance();
			HostDao hostDao = HostDao.getInstance();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			
			while (results.next()) {
				int listingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String summary = results.getString("Summary");
				String url = results.getString("URL");
				int hostId = results.getInt("HostId");
				boolean availability = results.getBoolean("Availability");
				Listing.PropertyType propertyType = Listing.PropertyType.getEnum(results.getString("PropertyType"));

				Listing.RoomType roomType = Listing.RoomType.getEnum(results.getString("RoomType"));
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				float bedrooms = results.getFloat("Bedrooms");
				int beds = results.getInt("Beds");
				float price = results.getFloat("Price");
				float deposit = results.getFloat("SecurityDeposit");
				int min = results.getInt("MinimumNights");
				int max = results.getInt("MaximumNights");
				int locationKey = results.getInt("LocationKey");
				
				Host host = hostDao.getHostById(hostId);
				Location location = locationDao.getLocationById(locationKey);
				Neighborhood neighborhoodClass = neighborhoodDao.getNeighborhoodByName(neighborhood);
				
				Listing listing = new Listing(listingId,name,summary,url,host,availability,propertyType,roomType,accommodates,bathrooms,bedrooms,beds,price,deposit,min,max,location,neighborhoodClass);
				list.add(listing);
			}
		}  catch (SQLException e) {
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
	
	public Listing updatePrice(Listing listing, double price) throws SQLException {
		String updateListing = "UPDATE Listing SET Price=? WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateListing, Statement.RETURN_GENERATED_KEYS);
			updateStmt.setDouble(1, price);
			updateStmt.setInt(2, listing.getListingId());
			updateStmt.executeUpdate();

			listing.setPrices(price);
			return listing;
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

	public Listing updateAvailability(Listing listing, Boolean availability) throws SQLException {
		String updateListing = "UPDATE Listing SET Availability=? WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateListing);
			updateStmt.setBoolean(1, availability);
			updateStmt.setInt(2, listing.getListingId());
			updateStmt.executeUpdate();
			
			listing.setAvailability(availability);
			return listing;
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

	public Listing updateSummary(Listing listing, String summary) throws SQLException {
		String updateListing = "UPDATE Listing SET Summary=? WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateListing);
			updateStmt.setString(1, summary);
			updateStmt.setInt(2, listing.getListingId());
			updateStmt.executeUpdate();

			listing.setSummary(summary);
			return listing;
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
	
	public Listing delete(Listing listing) throws SQLException {
		String  delete= "DELETE FROM Listing WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, listing.getListingId());
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
