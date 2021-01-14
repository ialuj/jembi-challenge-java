package org.jembi.challenge.service;

import java.util.Collection;

import org.jembi.challenge.model.Patient;

/**
 * 
 *  Interface that exposes Patient Services
 * 
 * @author José Julai Ritsure
 *
 */
public interface PatientService {
	
	/**
	 * 
	 * Service responsible for saving <Patient> Objects
	 * 
	 * @param patients
	 * @return
	 */
	public Collection<Patient> registerPatients(final Collection<Patient> patients);

}
