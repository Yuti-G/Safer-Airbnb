package saferAirbnb.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import saferAirbnb.model.OffenseCodeGroup;

public class OffenseCodeGroupDao {

	protected ConnectionManager connectionManager;
	private static OffenseCodeGroupDao instance = null;
	
	protected OffenseCodeGroupDao() {
		this.connectionManager = new ConnectionManager();
	}
	
	public static OffenseCodeGroupDao getInstance() {
		if(instance == null) {
			instance = new OffenseCodeGroupDao();
		}
		return instance;
	}
	
	public OffenseCodeGroup create(OffenseCodeGroup offenseCodeGroup) throws SQLException {
		String insert = "INSERT INTO OffenseCodeGroup(GroupName) VALUES(?);"
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, offenseCodeGroup.getGroupName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int offenseCodeGroupKey = -1;
			if(resultKey.next()) {
				offenseCodeGroupKey = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			offenseCodeGroup.setOffenseCodeGroupKey(offenseCodeGroupKey);
			return offenseCodeGroup;
			
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
	
	
	public OffenseCodeGroup getOffenseCodeGroupById(int offenseCodeGroupId) throws SQLException {
		String select = "SELECT OffenseCodeGroupKey, GroupName FROM OffenseCodeGroup WHERE OffenseCodeGroupKey=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setInt(1, offenseCodeGroupId);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				String groupName = results.getString("GroupName");
				OffenseCodeGroup offenseCodeGroup = new OffenseCodeGroup(offenseCodeGroupId, groupName);
				return offenseCodeGroup;
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
	
	public OffenseCodeGroup getOffenseCodeGroupByName(String groupName) throws SQLException {
		String select = "SELECT OffenseCodeGroupKey, GroupName FROM OffenseCodeGroup WHERE GroupName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(select);
			selectStmt.setString(1, groupName);
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				int offenseCodeGroupKey = results.getInt("OffenseCodeGroupKey");
				OffenseCodeGroup offenseCodeGroup = new OffenseCodeGroup(offenseCodeGroupKey, groupName);
				return offenseCodeGroup;
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
}
