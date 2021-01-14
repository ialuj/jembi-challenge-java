package org.jembi.challenge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Collection;

import org.jembi.challenge.utils.FileReader;

import org.jembi.challenge.model.Patient;
import org.jembi.challenge.service.PatientService;
import org.jembi.challenge.service.PatientServiceImpl;

/**
 * 
 * Class that has the main point of the App and Responsible for running the App
 * 
 * @author José Julai Ritsure
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException, SQLException {

		final String url = "https://www.mockaroo.com/04de5930/download?count=1000&key=e27814e0";

		FileReader fileReader = null;

		try {
			fileReader = new FileReader(url);
		} catch (MalformedURLException e) {
			// Should treat this exception
			e.printStackTrace();
		}

		PatientService patientService = new PatientServiceImpl();
		try {
			Collection<Patient> patients = fileReader.getPatientRecords();
			Collection<Patient> registeredPatients = patientService
					.registerPatients(patients);

			System.out.println("Registered Patients: "
					+ registeredPatients.size());
			System.out
					.println("---------------------------------------------------------------------------------------------------------");
			for (Patient patient : registeredPatients) {
				System.out.println(patient.getIdentityNumber() + ", "
						+ patient.getFirstName() + ", " + patient.getLastName()
						+ ", " + patient.getPhoneNumber() + ", "
						+ patient.getAddress() + ", " + patient.getGender()
						+ ", " + patient.getRace());
			}
			System.out
					.println("---------------------------------------------------------------------------------------------------------");

		} catch (Exception e) {
			// Should treat this exception
			e.printStackTrace();
		}

	}
}
