package org.jembi.challenge.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.jembi.challenge.connection.ConnectionFactory;
import org.jembi.challenge.model.Patient;

/**
 * 
 * Class that implements Patient DAO Interface
 * 
 * @author José Julai Ritsure
 * 
 */
public class PatientDAOImpl implements PatientDAO {

	Statement statement = ConnectionFactory.getConnection();

	@Override
	public Collection<Patient> registerPatients(Collection<Patient> patients)
			throws SQLException {

		for (Patient patient : patients) {

			String insert = "insert into patients(identityNumber, firstName, lastName, phoneNumber, address, gender, race) "
					+ "values(\""
					+ patient.getIdentityNumber()
					+ "\", \""
					+ patient.getFirstName()
					+ "\", \""
					+ patient.getLastName()
					+ "\", \""
					+ patient.getPhoneNumber()
					+ "\", \""
					+ patient.getAddress()
					+ "\", \""
					+ patient.getGender()
					+ "\", \"" + patient.getRace() + "\")";

			filterDuplicatedAndPersistConsistentPatients(patient, insert);

		}

		return findAllPatients();
	}

	/**
	 * 
	 * Filter duplicated Patients and Persist the Consistent <Patient> Objects
	 * 
	 * If this is business... it should be on service layer
	 * 
	 * @param patient
	 * @param sqlStatement
	 * @throws SQLException
	 */
	private void filterDuplicatedAndPersistConsistentPatients(
			final Patient patient, final String sqlStatement)
			throws SQLException {
		ResultSet duplicates = statement.executeQuery("select * from patients "
				+ "where identityNumber =" + patient.getIdentityNumber()
				+ " or " + "(firstName = \"" + patient.getFirstName()
				+ "\" and lastName =\"" + patient.getLastName() + "\")");

		if (duplicates.isBeforeFirst()) {
			while (duplicates.next()) {
				if (patient.getIdentityNumber().equals(
						duplicates.getString("identityNumber"))) {
					continue;
				} else if (patient.getFirstName().equals(
						duplicates.getString("firstName"))
						&& patient.getLastName().equals(
								duplicates.getString("lastName"))) {
					if (patient.getGender().equals(
							duplicates.getString("gender"))
							&& patient.getRace().equals(
									duplicates.getString("race"))) {
						continue;
					}
				} else {
					statement.executeUpdate(sqlStatement);
				}
			}
		} else {
			statement.executeUpdate(sqlStatement);
		}

	}

	/**
	 * 
	 * Returns 10 Patients registered into the database
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Collection<Patient> findAllPatients() throws SQLException {

		ResultSet rows = this.statement
				.executeQuery("select * from patients limit 10");

		Collection<Patient> patients = new ArrayList<Patient>(0);

		while (rows.next()) {

			final Patient patient = new Patient(
					rows.getString("identityNumber"),
					rows.getString("firstName"), rows.getString("lastName"),
					rows.getString("phoneNumber"), rows.getString("address"),
					rows.getString("gender"), rows.getString("race"));

			patients.add(patient);
		}
		return patients;
	}

}
