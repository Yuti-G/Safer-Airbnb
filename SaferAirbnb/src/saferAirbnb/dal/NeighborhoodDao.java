package saferAirbnb.dal;

import saferAirbnb.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeighborhoodDao {
protected ConnectionManager connectionManager;
	
	private static NeighborhoodDao instance = null;
	protected NeighborhoodDao() {
		connectionManager = new ConnectionManager();
	}
	public static NeighborhoodDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodDao();
		}
		return instance;
	}
	
	public Neighborhood create(Neighborhood neighborhood) throws SQLException {
		String insert = "INSERT INTO Neighborhood (NeighborhoodName,DistrictFk) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);
			
			insertStmt.setString(1, neighborhood.getNeighborhoodName());
			insertStmt.setInt(2, neighborhood.getDistrict().getDistrictKey());
			insertStmt.executeUpdate();
			
			return neighborhood;
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
	
	public Neighborhood getNeighborhoodByName(String neighborhoodName) throws SQLException {
		String select = "SELECT NeighborhoodName,DistrictFk FROM Neighborhood WHERE NeighborhoodName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, neighborhoodName);

			results = selectStmt.executeQuery();
			DistrictDao districtDao = DistrictDao.getInstance();
			
			if (results.next()) {
				String name = results.getString("NeighborhoodName");
				int districtId = results.getInt("DistrictFk");
				
				District district = districtDao.getDistrictByDistrictId(districtId);
				Neighborhood neighbor = new Neighborhood(name,district);
				
				return neighbor;
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
	
	public Neighborhood delete(Neighborhood neighborhood) throws SQLException {
		String  delete = "DELETE FROM Neighborhood WHERE NeighborhoodName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setString(1, neighborhood.getNeighborhoodName());
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
