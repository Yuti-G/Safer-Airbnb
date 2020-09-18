package saferAirbnb.dal;

import saferAirbnb.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostDao extends PersonDao{
	protected ConnectionManager connectionManager;
	
	private static HostDao instance = null;
	protected HostDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static HostDao getInstance() {
		if (instance == null) {
			instance = new HostDao();
		}
		return instance;
	}
	
	public Host create(Host host) throws SQLException {
		Person person = create(new Person(host.getHostId(), host.getHostName()));
		
		String insertHost = "INSERT INTO Host(HostId,HostSince,HostLocation,HostAbout,HostResponseTime,HostResponseRate,HostAcceptanceRate,HostURL) VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHost);
			
			insertStmt.setInt(1, person.getPersonId());
			insertStmt.setDate(2, host.getHostSince());
			insertStmt.setString(3, host.getHostLocation());
			insertStmt.setString(4, host.getHostAbout());
			insertStmt.setString(5, host.getHostResponseTime());
			insertStmt.setString(6, host.getHostResponseRate());
			insertStmt.setString(7, host.getHostAcceptanceRate());
			insertStmt.setString(8, host.getHostUrl());
			
			insertStmt.executeUpdate();
			return host;
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

	public Host getHostById(int hostId) throws SQLException {
		String selectHost = "SELECT Person.PersonId as HostId,Person.Name as HostName, HostSince,HostLocation,HostAbout,HostResponseTime," +
				"HostResponseRate,HostAcceptanceRate,HostURL FROM Host INNER JOIN Person ON" +
				" Host.HostId = Person.PersonId WHERE Person.PersonId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setInt(1, hostId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int curHostId = results.getInt("HostId");
				String hostName = results.getString("HostName");
				Date hostSince = results.getDate("HostSince");
				String location = results.getString("HostLocation");
				String hostAbout = results.getString("HostAbout");
				String hostResponseTime = results.getString("HostResponseTime");
				String hostResponseRate = results.getString("HostResponseRate");
				String hostAcceptanceRate = results.getString("HostAcceptanceRate");
				String hostUrl = results.getString("HostURL");
				Host host = new Host(curHostId,hostName,hostSince,location,hostAbout,hostResponseTime,hostResponseRate,hostAcceptanceRate,hostUrl);
				return host;
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


	public Host getHostByName(String name) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectHost = "SELECT Person.Name as Name,Person.PersonId as HostId, HostSince,HostLocation,HostAbout,HostResponseTime," +
				"HostResponseRate,HostAcceptanceRate,HostURL FROM Host INNER JOIN Person ON" +
				" Host.HostId = Person.PersonId WHERE Person.Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setString(1, name);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int hostId = results.getInt("HostId");
				String curHostName = results.getString("Name");
				Date hostSince = results.getDate("HostSince");
				String location = results.getString("HostLocation");
				String hostAbout = results.getString("HostAbout");
				String hostResponseTime = results.getString("HostResponseTime");
				String hostResponseRate = results.getString("HostResponseRate");
				String hostAcceptanceRate = results.getString("HostAcceptanceRate");
				String hostUrl = results.getString("HostURL");
				Host host = new Host(hostId, curHostName ,hostSince,location,hostAbout,hostResponseTime,hostResponseRate,hostAcceptanceRate,hostUrl);
				return host;
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


	public List<Host> getHostsByName(String name) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		List<Host> hosts = new ArrayList<Host>();
		String selectHost = "SELECT Person.Name AS Name, Person.PersonId AS Pid, HostSince, HostLocation, HostAbout, HostResponseTime," +
				" HostResponseRate, HostAcceptanceRate, HostURL FROM Host INNER JOIN Person ON " +
				" Person.PersonId = HostId WHERE Person.Name =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setString(1, name);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int hostId = results.getInt("Pid");
				String curHostName = results.getString("Name");
				Date hostSince = results.getDate("HostSince");
				String location = results.getString("HostLocation");
				String hostAbout = results.getString("HostAbout");
				String hostResponseTime = results.getString("HostResponseTime");
				String hostResponseRate = results.getString("HostResponseRate");
				String hostAcceptanceRate = results.getString("HostAcceptanceRate");
				String hostUrl = results.getString("HostURL");
				Host host = new Host(hostId, curHostName ,hostSince,location,hostAbout,hostResponseTime,hostResponseRate,hostAcceptanceRate,hostUrl);
				hosts.add(host);
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
		return hosts;
	}

	public List<Host> getHostsById(int hostId) throws SQLException {
		List<Host> hosts = new ArrayList<Host>();
		String selectHost = "SELECT Person.PersonId as HostId,Person.Name as HostName, HostSince,HostLocation," +
				"HostAbout,HostResponseTime," +
				"HostResponseRate,HostAcceptanceRate,HostURL FROM Host INNER JOIN Person ON" +
				" Host.HostId = Person.PersonId WHERE Person.PersonId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setInt(1, hostId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				int curHostId = results.getInt("HostId");
				String hostName = results.getString("HostName");
				Date hostSince = results.getDate("HostSince");
				String location = results.getString("HostLocation");
				String hostAbout = results.getString("HostAbout");
				String hostResponseTime = results.getString("HostResponseTime");
				String hostResponseRate = results.getString("HostResponseRate");
				String hostAcceptanceRate = results.getString("HostAcceptanceRate");
				String hostUrl = results.getString("HostURL");
				Host host = new Host(curHostId,hostName,hostSince,location,hostAbout,hostResponseTime,hostResponseRate,hostAcceptanceRate,hostUrl);
				hosts.add(host);
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
		return hosts;
	}


	public Host updateHostByName(Host host, String newName) throws SQLException {
		super.updateName(host, newName);
		return host;
	}

	
	public Host delete(Host host) throws SQLException {
		HostDao tempHost;
		tempHost = HostDao.getInstance();

		if (tempHost.getHostsById(host.getHostId()).size() > 0) {
			String delete = "DELETE FROM Host WHERE HostId=?;";
			Connection connection = null;
			PreparedStatement deleteStmt = null;
			try {
				connection = connectionManager.getConnection();
				deleteStmt = connection.prepareStatement(delete);
				deleteStmt.setInt(1, host.getHostId());
				deleteStmt.executeUpdate();

				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (connection != null) {
					connection.close();
				}
				if (deleteStmt != null) {
					deleteStmt.close();
				}
			}
		} else {
			return host;
		}
	}
}
