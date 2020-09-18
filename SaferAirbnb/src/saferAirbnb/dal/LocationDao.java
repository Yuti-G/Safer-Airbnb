package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import saferAirbnb.model.*;

public class LocationDao {
	
	protected ConnectionManager connectionManager;
	private static LocationDao instance = null;
	
	protected LocationDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static LocationDao getInstance() {
		if(instance == null) {
			instance = new LocationDao();
		}
		return instance;
	}
	
	public Location create(Location location) throws SQLException {
		String insert = "INSERT INTO Location(Address1, Address2, Latitude, Longitude, City, State, AreaNo) "
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, location.getAddress1());
			insertStmt.setString(2, location.getAddress2());
			insertStmt.setDouble(3, location.getLatitude());
			insertStmt.setDouble(4, location.getLongitude());
			insertStmt.setString(5, location.getCity());
			insertStmt.setString(6, location.getState());
			insertStmt.setInt(7, location.getArea().getAreaNo());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int locationKey = -1;
			if(resultKey.next()) {
				locationKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			location.setLocationKey(locationKey);
			return location;
			
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
	
	public Location getLocationById(int locationId) throws SQLException {
		String select = "SELECT LocationKey, Address1, Address2, Latitude, Longitude, City, State, AreaNo FROM Location WHERE LocationKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, locationId);
			results = selectStmt.executeQuery();
			
			AreaDao areaDao = AreaDao.getInstance();
			
			if(results.next()) {
				int locationKey = results.getInt("LocationKey");
				String address1 = results.getNString("Address1");
				String address2 = results.getNString("Address2");
				double latitude = results.getDouble("Latitude");
				double longitude = results.getDouble("Longitude");
				String city = results.getString("City");
				String state = results.getNString("State");
				int areaNo = results.getInt("AreaNo");
				Area area = areaDao.getAreaByAreaNo(areaNo);
				
				Location location = new Location(locationKey, address1, address2, latitude, longitude, city, state, area);
				
				return location;
				
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
	
	public List<Location> getLocationsByAreaNo(int areaNo) throws SQLException {
		String select = "SELECT LocationKey, Address1, Address2, Latitude, Longitude, City, State, AreaNo FROM Location WHERE AreaNo=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Location> locations = new ArrayList<>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, areaNo);
			results = selectStmt.executeQuery();
			
			AreaDao areaDao = AreaDao.getInstance();
			
			while(results.next()) {
				int locationKey = results.getInt("LocationKey");
				String address1 = results.getNString("Address1");
				String address2 = results.getNString("Address2");
				double latitude = results.getDouble("Latitude");
				double longitude = results.getDouble("Longitude");
				String city = results.getString("City");
				String state = results.getNString("State");
				Area area = areaDao.getAreaByAreaNo(areaNo);
				
				Location location = new Location(locationKey, address1, address2, latitude, longitude, city, state, area);
				
				locations.add(location);
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
		return locations;
	}
	
	public List<Location> getLocationsByDistrictCode(int districtCode) throws SQLException {
		String select = "SELECT LocationKey, Address1, Address2, Latitude, Longitude, City, State, Location.AreaNo FROM Location "
				+ "LEFT JOIN Area ON Location.AreaNo = Area.AreaNo "
				+ "WHERE Area.DistrictKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Location> locations = new ArrayList<>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, districtCode);
			results = selectStmt.executeQuery();
			
			AreaDao areaDao = AreaDao.getInstance();
			
			while(results.next()) {
				int locationKey = results.getInt("LocationKey");
				String address1 = results.getNString("Address1");
				String address2 = results.getNString("Address2");
				double latitude = results.getDouble("Latitude");
				double longitude = results.getDouble("Longitude");
				String city = results.getString("City");
				String state = results.getString("State");
				int areaNo = results.getInt("AreaNo");
				Area area = areaDao.getAreaByAreaNo(areaNo);
				
				Location location = new Location(locationKey, address1, address2, latitude, longitude, city, state, area);
				
				locations.add(location);
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
		return locations;
	}
	
	public Location delete(Location location) throws SQLException {
		String delete = "DELETE FROM Location WHERE LocationKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try{
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, location.getLocationKey());
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
