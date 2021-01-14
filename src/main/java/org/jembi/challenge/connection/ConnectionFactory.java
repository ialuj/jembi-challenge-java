package org.jembi.challenge.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Class that manages the connections and creates the database strutcure
 * 
 * @author José Julai Ritsure
 * 
 */
public class ConnectionFactory {

	public static Statement statement;

	/**
	 * 
	 * Returns the statement to be used by DAO Classes
	 * 
	 * @return
	 */
	public static Statement getConnection() {

		final String dbUrl = "jdbc:sqlite::memory:";

		try {
			Connection db = DriverManager.getConnection(dbUrl);
			ConnectionFactory.statement = db.createStatement();
			ConnectionFactory.createDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statement;
	}

	/**
	 * 
	 * Method that creates the Patients Table into the memory db
	 * 
	 * @throws SQLException
	 */
	private static void createDatabase() throws SQLException {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("create table if not exists patients (id primary key, identityNumber string, ");
		stringBuilder
				.append("firstName string, lastName string, phoneNumber string, address string, gender string, race string)");

		ConnectionFactory.statement.executeUpdate(stringBuilder.toString());
	}

}
