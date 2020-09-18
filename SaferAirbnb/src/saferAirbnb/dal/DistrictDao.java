package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import saferAirbnb.model.*;



public class DistrictDao {
	
	protected ConnectionManager connectionManager;
	
	private static DistrictDao instance = null;
	
	protected DistrictDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static DistrictDao getInstance() {
		if(instance == null) {
			instance = new DistrictDao();
		}
		return instance;
	}
	
	public District create (District district) throws SQLException {
		String insertDistrict = "INSERT INTO District(DistrictCode) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDistrict);

			insertStmt.setString(1, district.getDistrictCode());
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int districtKey = -1;
			if(resultKey.next()) {
				districtKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			district.setDistrictKey(districtKey);
			return district;
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
	
	public District getDistrictByDistrictId(int districtId) throws SQLException {
		String selectDistrict = "SELECT DistrictKey, DistrictCode, DistrictName FROM District WHERE DistrictKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDistrict);
			selectStmt.setInt(1, districtId);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				String districtCode = results.getString("DistrictCode");
				String name = results.getNString("DistrictName");
				District district = new District(districtId, districtCode, name);
				return district;
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
	
	public District getDistrictByDistrictCode(String districtCode) throws SQLException {
		String selectDistrict = "SELECT DistrictKey, DistrictCode, DistrictName FROM District WHERE DistrictCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDistrict);
			selectStmt.setString(1, districtCode);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				int districtKey = results.getInt("DistrictKey");
				String name = results.getNString("DistrictName");
				District district = new District(districtKey, districtCode, name);
				return district;
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
	
	public District getDistrictByDistrictName(String districtName) throws SQLException {
		String selectDistrict = "SELECT DistrictKey, DistrictCode, DistrictName FROM District WHERE DistrictName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDistrict);
			selectStmt.setString(1, districtName);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				int districtKey = results.getInt("DistrictKey");
				String districtCode = results.getString("DistrictCode");
				District district = new District(districtKey, districtCode, districtName);
				return district;
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
	
	public District delete(District district) throws SQLException {
		String delete = "DELETE FROM District WHERE DistrictKey=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try{
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, district.getDistrictKey());
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
