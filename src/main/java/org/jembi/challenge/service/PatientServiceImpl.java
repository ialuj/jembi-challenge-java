package org.jembi.challenge.service;

import java.sql.SQLException;
import java.util.Collection;

import org.jembi.challenge.dao.PatientDAO;
import org.jembi.challenge.dao.PatientDAOImpl;
import org.jembi.challenge.model.Patient;

/**
 * 
 * Class that implements Patient Service Interface
 * 
 * @author José Julai Ritsure
 * 
 */
public class PatientServiceImpl implements PatientService {

	private PatientDAO patientDAO;

	public PatientServiceImpl() {
		this.patientDAO = new PatientDAOImpl();
	}

	@Override
	public Collection<Patient> registerPatients(Collection<Patient> patients) {
		try {
			return patientDAO.registerPatients(patients);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
