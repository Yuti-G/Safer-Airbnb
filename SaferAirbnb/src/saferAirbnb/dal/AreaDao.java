package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import saferAirbnb.model.*;

public class AreaDao {

	protected ConnectionManager connectionManager;
	
	private static AreaDao instance = null;

	public AreaDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static AreaDao getInstance() {
		if(instance == null) {
			instance = new AreaDao();
		}
		return instance;
	}
	
	public Area create(Area area) throws SQLException {
		String insertArea = "INSERT INTO Area(AreaNo, DistrictKey) VALUES(?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertArea);

			insertStmt.setInt(1, area.getAreaNo());
			insertStmt.setInt(2, area.getDistrict().getDistrictKey());
			insertStmt.executeUpdate();
			
			return area;
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
	
	public Area getAreaByAreaNo(int areaNo) throws SQLException {
		String selectDistrict = "SELECT AreaNo, DistrictKey FROM Area WHERE AreaNo=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDistrict);
			selectStmt.setInt(1, areaNo);
			
			results = selectStmt.executeQuery();
			DistrictDao districtDao = DistrictDao.getInstance();

			if(results.next()) {
				int districtKey = results.getInt("DistrictKey");
				District district = districtDao.getDistrictByDistrictId(districtKey);
				Area area = new Area(areaNo, district);
				return area;

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
	
	public List<Area> getAreaByDistrict(int districtId) throws SQLException {
		String selectDistrict = "SELECT AreaNo, DistrictKey FROM Area WHERE DistrictKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<Area> areas= new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDistrict);
			selectStmt.setInt(1,districtId);
			results = selectStmt.executeQuery();
			
			DistrictDao districtDao = DistrictDao.getInstance();
			while(results.next()) {
				int areaNo = results.getInt("AreaNo");
				District district = districtDao.getDistrictByDistrictId(districtId);
				Area area = new Area(areaNo, district);
				areas.add(area);
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
		
		return areas;
		
	}
	
	public Area delete(Area area) throws SQLException {
		String delete = "DELETE FROM Area WHERE AreaNo=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try{
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setInt(1, area.getAreaNo());
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
