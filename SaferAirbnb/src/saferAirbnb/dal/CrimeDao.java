package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import saferAirbnb.model.*;

public class CrimeDao {
	
	protected ConnectionManager connectionManager;
	
	private static CrimeDao instance = null;
	protected CrimeDao() {
		connectionManager = new ConnectionManager();
	}
	public static CrimeDao getInstance() {
		if(instance == null) {
			instance = new CrimeDao();
		}
		return instance;
	}
	
	public Crime create(Crime crime) throws SQLException {
		String insert = "INSERT INTO Crime(IncidentNumber, DateTime, OffenseCodeKey, Description, OffenseCodeGroupKey, LocationKey, Shooting) "
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, crime.getIncidentNumber());
			insertStmt.setTimestamp(2, new Timestamp(crime.getDateTime().getTime()));
			insertStmt.setInt(3, crime.getOffenseCode().getOffenseCodeKey());
			insertStmt.setString(4, crime.getDescription());
			insertStmt.setInt(5, crime.getOffenseCode().getOffenseCodeGroup().getOffenseCodeGroupKey());
			insertStmt.setInt(6, crime.getLocation().getLocationKey());
			insertStmt.setBoolean(7, crime.isShooting());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int crimeKey = -1;
			if (resultKey.next()) {
				crimeKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			crime.setCrimeKey(crimeKey);
			return crime;
			
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
	
	public Crime getCrimeById(int crimeId) throws SQLException {
		String select = "SELECT CrimeKey, IncidentNumber, DateTime, OffenseCodeKey, Description, "
				+ "LocationKey, Shooting FROM Crime WHERE CrimeKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		Crime crime = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, crimeId);
			OffenseCodeDao offenseCodeDao = OffenseCodeDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			if (results.next()) {
				int crimeKey = results.getInt("CrimeKey");
				Date date = results.getDate("DateTime");
				String incidentNo = results.getString("IncidentNumber");
				int offenseCodeKey = results.getInt("OffenseCodeKey");
				String description = results.getString("Description");
				int locationKey = results.getInt("LocationKey");
				boolean shooting = results.getBoolean("Shooting");
				
				OffenseCode offenseCode = offenseCodeDao.getOffenseCodeById(offenseCodeKey);
				Location location = locationDao.getLocationById(locationKey);
				
				crime = new Crime(crimeKey, incidentNo, date, description, offenseCode, location, shooting);
			}
			return crime;
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
	}
	
	public List<Crime> getCrimesByIncidentNo(String incidentNo) throws SQLException {
		String select = "SELECT CrimeKey, IncidentNumber, DateTime, OffenseCodeKey, Description, "
				+ "LocationKey, Shooting FROM Crime WHERE IncidentNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Crime> crimes= new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, incidentNo);
			OffenseCodeDao offenseCodeDao = OffenseCodeDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int crimeKey = results.getInt("CrimeKey");
				Date date = results.getDate("DateTime");
				int offenseCodeKey = results.getInt("OffenseCodeKey");
				String description = results.getString("Description");
				int locationKey = results.getInt("LocationKey");
				boolean shooting = results.getBoolean("Shooting");
				
				OffenseCode offenseCode = offenseCodeDao.getOffenseCodeById(offenseCodeKey);
				Location location = locationDao.getLocationById(locationKey);
				
				Crime crime = new Crime(crimeKey, incidentNo, date, description, offenseCode, location, shooting);
				crimes.add(crime);
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
		return crimes;
	}
	
	public int countCrimesByDistrict(int districtId) throws SQLException {
		String select = "SELECT COUNT(*) AS Count FROM Crime LEFT JOIN Location ON Crime.LocationKey = Location.LocationKey "
				+ "LEFT JOIN Area ON Location.AreaNo = Area.AreaNo WHERE Area.DistrictKey=?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		
		int count = 0;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, districtId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				count = results.getInt("Count");
				return count;
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
		return count;
		
	}
	
	public List<Crime> getCrimesByDistrictName(String districtName) throws SQLException {
		String select = "SELECT CrimeKey, IncidentNumber, DateTime, OffenseCodeKey, Description, "
				+ "Crime.LocationKey, Shooting FROM Crime LEFT JOIN Location ON Crime.LocationKey = Location.LocationKey "
				+ "LEFT JOIN Area ON Location.AreaNo = Area.AreaNo LEFT JOIN District ON Area.DistrictKey = District.DistrictKey "
				+ "WHERE District.DistrictName=? LIMIT 50;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Crime> crimes= new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, districtName);
			OffenseCodeDao offenseCodeDao = OffenseCodeDao.getInstance();
			OffenseCodeGroupDao offenseCodeGroupDao = OffenseCodeGroupDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int crimeKey = results.getInt("CrimeKey");
				String incidentNo = results.getString("IncidentNumber");
				Date date = results.getDate("DateTime");
				int offenseCodeKey = results.getInt("OffenseCodeKey");
				String description = results.getString("Description");
				int locationKey = results.getInt("LocationKey");
				boolean shooting = results.getBoolean("Shooting");
				
				OffenseCode offenseCode = offenseCodeDao.getOffenseCodeById(offenseCodeKey);
				Location location = locationDao.getLocationById(locationKey);
				
				Crime crime = new Crime(crimeKey, incidentNo, date, description, offenseCode, location, shooting);
				crimes.add(crime);
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
		return crimes;
		
	}
	
	public List<Crime> getCrimesByOffenseCodeGroup(int offenseCodeGroupKey) throws SQLException {
		
		String select = "SELECT CrimeKey IncidentNumber, DateTime, OffenseCodeKey, Description, "
				+ "LocationKey, Shooting FROM Crime WHERE offenseCodeGroupKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Crime> crimes= new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, offenseCodeGroupKey);
			OffenseCodeDao offenseCodeDao = OffenseCodeDao.getInstance();
			OffenseCodeGroupDao offenseCodeGroupDao = OffenseCodeGroupDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int crimeKey = results.getInt("CrimeKey");
				String incidentNo = results.getString("IncidentNumber");
				Date date = results.getDate("DateTime");
				int offenseCodeKey = results.getInt("OffenseCodeKey");
				String description = results.getString("Description");
				int locationKey = results.getInt("LocationKey");
				boolean shooting = results.getBoolean("Shooting");
				
				OffenseCode offenseCode = offenseCodeDao.getOffenseCodeById(offenseCodeKey);
				Location location = locationDao.getLocationById(locationKey);
				
				Crime crime = new Crime(crimeKey, incidentNo, date, description, offenseCode, location, shooting);
				crimes.add(crime);
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
		return crimes;
	}
	
	public List<Crime> getCrimesByOffenseCode(int offenseCodeKey) throws SQLException {
		String select = "SELECT CrimeKey IncidentNumber, DateTime, OffenseCodeKey, Description, "
				+ "LocationKey, Shooting FROM Crime WHERE OffenseCodeKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Crime> crimes= new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, offenseCodeKey);
			OffenseCodeDao offenseCodeDao = OffenseCodeDao.getInstance();
			OffenseCodeGroupDao offenseCodeGroupDao = OffenseCodeGroupDao.getInstance();
			LocationDao locationDao = LocationDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int crimeKey = results.getInt("CrimeKey");
				String incidentNo = results.getString("IncidentNumber");
				Date date = results.getDate("DateTime");
				String description = results.getString("Description");
				int locationKey = results.getInt("LocationKey");
				boolean shooting = results.getBoolean("Shooting");
				
				OffenseCode offenseCode = offenseCodeDao.getOffenseCodeById(offenseCodeKey);
				Location location = locationDao.getLocationById(locationKey);
				
				Crime crime = new Crime(crimeKey, incidentNo, date, description, offenseCode, location, shooting);
				crimes.add(crime);
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
		return crimes;
	}

	public Crime updateShooting(Crime crime, Boolean shooting) throws SQLException {
		String updateListing = "UPDATE Crime SET Shooting=? WHERE CrimeKey=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateListing);
			updateStmt.setBoolean(1, shooting);
			updateStmt.setInt(2, crime.getCrimeKey());
			updateStmt.executeUpdate();

			crime.setShooting(shooting);
			return crime;
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
	
	public Crime delete(Crime crime) throws SQLException {

		String delete = "DELETE FROM Crime WHERE CrimeKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try{
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, crime.getCrimeKey());
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
