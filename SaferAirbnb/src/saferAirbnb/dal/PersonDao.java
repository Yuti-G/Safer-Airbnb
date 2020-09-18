package saferAirbnb.dal;

import saferAirbnb.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class PersonDao {
	protected ConnectionManager connectionManager;
	
	private static PersonDao instance = null;
	protected PersonDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static PersonDao getInstance() {
		if (instance == null) {
			instance = new PersonDao();
		}
		return instance;
	}
	
	public Person create(Person person) throws SQLException {
		String insertPerson = "INSERT INTO Person(PersonId, Name) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPerson);
			insertStmt.setInt(1, person.getPersonId());
			insertStmt.setString(2, person.getName());
			insertStmt.executeUpdate();
			return person;
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
	
	public Person getPersonById(int personId) throws SQLException {
		String selectPerson = "SELECT PersonId, Name FROM Person WHERE PersonId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, personId);
			
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				int curPersonId = results.getInt("PersonId");
				String name = results.getString("Name");
				Person person = new Person(curPersonId, name);
				return person;
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

	/*
	public Person getPersonByName(String personName) throws SQLException {
		String selectPerson = "SELECT PersonId, Name FROM Person WHERE Name=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, personName);

			results = selectStmt.executeQuery();

			if (results.next()) {
				int personId = results.getInt("PersonId");
				String resultName = results.getString("Name");
				Person person = new Person(personId, resultName);
				return person;
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
	*/
	
	public List<Person> getPeopleByName(String personName) throws SQLException {
		List<Person> people = new ArrayList<>();
		String selectPerson = "SELECT PersonId, Name FROM Person WHERE Name=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, personName);

			results = selectStmt.executeQuery();

			while (results.next()) {
				int personId = results.getInt("PersonId");
				String resultName = results.getString("Name");
				Person person = new Person(personId, resultName);
				people.add(person);
			}
			return people;
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

	/**
	 * Update the Name of the Persons instance.
	 * This runs a UPDATE statement.
	 */
	public Person updateName(Person person, String newName) throws SQLException {
		String updatePerson = "UPDATE Person SET Name=? WHERE PersonId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, person.getPersonId());
			updateStmt.executeUpdate();
			// Update the person param before returning to the caller.
			person.setName(newName);
			return person;
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


	
	public Person delete(Person person) throws SQLException {
		String deletePerson = "DELETE FROM Person WHERE PersonId=?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setInt(1, person.getPersonId());
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
