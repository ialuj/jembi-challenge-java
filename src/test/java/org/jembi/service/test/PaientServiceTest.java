package org.jembi.service.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.jembi.challenge.model.Patient;
import org.jembi.challenge.service.PatientService;
import org.jembi.challenge.service.PatientServiceImpl;
import org.jembi.challenge.utils.FileReader;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * 
 * Class that is responsible for Patient Service Tests
 * 
 * @author José Julai Ritsure
 *
 */
public class PaientServiceTest {
	
	private PatientService patientService = new PatientServiceImpl();
	
	private FileReader fileReader;
	
	@Test
	public void shouldRegisterPatients() {
      
		try {			
			URL resource = PaientServiceTest.class
		            .getResource("/patients.json");
			String url = resource.getFile().replace("file:/", "file:///");
			fileReader = new FileReader("file://"+url);
			//fileReader = new FileReader("file:///C:/Users/Acer/git/challenge-java/src/test/resources/patients.json");
		} catch (MalformedURLException e) {
			Assert.fail();
		}
		
		Collection<Patient> patients = fileReader.getPatientRecords();
		Assert.assertTrue(!patients.isEmpty());
		Assert.assertEquals(11, patients.size());
		
		Collection<Patient> savedPatients = patientService.registerPatients(patients);
		Assert.assertTrue(!savedPatients.isEmpty());
		Assert.assertEquals(9, savedPatients.size());
		
	}

}
