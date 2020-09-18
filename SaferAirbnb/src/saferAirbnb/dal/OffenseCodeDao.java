package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import saferAirbnb.model.OffenseCode;
import saferAirbnb.model.OffenseCodeGroup;

public class OffenseCodeDao {
	protected ConnectionManager connectionManager;
	private static OffenseCodeDao instance = null;
	
	protected OffenseCodeDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static OffenseCodeDao getInstance() {
		if(instance == null) {
			instance = new OffenseCodeDao();
		}
		return instance;
	}
	
	public OffenseCode create(OffenseCode offenseCode) throws SQLException {
		String insert = "INSERT INTO OffenseCode(OffenseCodeKey, Name, OffenseCodeGroupKey) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert);
			
			insertStmt.setInt(1, offenseCode.getOffenseCodeKey());
			insertStmt.setString(2, offenseCode.getName());
			insertStmt.setInt(3, offenseCode.getOffenseCodeGroup().getOffenseCodeGroupKey());
			insertStmt.executeUpdate();

			return offenseCode;
			
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
	
	public OffenseCode getOffenseCodeById(int offenseCodeId) throws SQLException {
		String select = "SELECT OffenseCodeKey, Name, OffenseCodeGroupKey FROM OffenseCode WHERE OffenseCodeKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, offenseCodeId);
			OffenseCodeGroupDao offenseCodeGroupDao = OffenseCodeGroupDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				String name = results.getString("Name");
				int offenseCodeGroupId = results.getInt("OffenseCodeGroupKey");
				OffenseCodeGroup offenseCodeGroup = offenseCodeGroupDao.getOffenseCodeGroupById(offenseCodeGroupId);
				OffenseCode offenseCode = new OffenseCode(offenseCodeId, name, offenseCodeGroup);
				return offenseCode;
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
	public List<OffenseCode> getOffenseCodesByCodeGroupName(String offenseCodeGroupName) throws SQLException {
		
		String select = "SELECT OffenseCodeKey, Name, OffenseCode.OffenseCodeGroupKey FROM OffenseCode "
				+ "LEFT JOIN OffenseCodeGroup ON OffenseCode.OffenseCodeGroupKey = OffenseCodeGroup.OffenseCodeGroupKey "
				+ "WHERE OffenseCodeGroup.GroupName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		List<OffenseCode> offenseCodes= new ArrayList<>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, offenseCodeGroupName);
			OffenseCodeGroupDao offenseCodeGroupDao = OffenseCodeGroupDao.getInstance();
			
			
			results = selectStmt.executeQuery();

			while(results.next()) {
				int offenseCodeKey = results.getInt("OffenseCodeKey");
				String name = results.getString("Name");
				int offenseCodeGroupId = results.getInt("OffenseCodeGroupKey");
				OffenseCodeGroup offenseCodeGroup = offenseCodeGroupDao.getOffenseCodeGroupById(offenseCodeGroupId);
				OffenseCode offenseCode = new OffenseCode(offenseCodeKey, name, offenseCodeGroup);
				offenseCodes.add(offenseCode);
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
		return offenseCodes;
	}
	

}
