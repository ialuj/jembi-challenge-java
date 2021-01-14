package org.jembi.challenge.dao;

import java.sql.SQLException;
import java.util.Collection;

import org.jembi.challenge.model.Patient;

/**
 * 
 * Interface that exposes Patient DAO Methods
 * 
 * @author José Julai Ritsure
 *
 */
public interface PatientDAO {
		
	/**
	 * 
	 * Receive a Collection of Patients and store it into the database
	 * 
	 * @param patients
	 * @return
	 * @throws SQLException 
	 */
	public Collection<Patient> registerPatients(final Collection<Patient> patients) throws SQLException;

}
