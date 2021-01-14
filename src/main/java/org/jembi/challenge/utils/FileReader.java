package org.jembi.challenge.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import org.jembi.challenge.model.Patient;

/**
 * 
 * Class that is responsible for reading and converting <Patient> json file into
 * <Patient> Objects Collections
 * 
 * @author José Julai Ritsure
 * 
 */
public class FileReader {
	
	private URL url;

	private Scanner input;

	/**
	 * 
	 * Receives the url of the json remote file
	 * 
	 * @param url
	 * @throws MalformedURLException
	 */
	public FileReader(final String url) throws MalformedURLException {
		super();
		this.url = new URL(url);
		this.input = getFileContent(this.url);
	}

	private Scanner getFileContent(final URL url) {
		Scanner input = null;
		try {
			input = new Scanner(new BufferedReader(new InputStreamReader(
					url.openStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	/**
	 * 
	 * Reads the json records and converts them into <Patient> Objects
	 * 
	 * @return
	 */
	public Collection<Patient> getPatientRecords() {

		final Collection<Patient> patients = new ArrayList<Patient>(0);
		
		while (this.input.hasNextLine()) {
			String line = input.nextLine();
			line = line.replace("[", "");
			line = line.replace("]", "");
			line = line.replace("{", "");
			line = line.replace("}", "");
			line = line.replace("\"", "");

			String[] fields = line.split(",");

			for (int i = 0; i < fields.length; i++) {
				fields[i] = fields[i].substring(fields[i].indexOf(":") + 1);
			}

			final Patient patient = new Patient(fields[0], fields[1],
					fields[2], fields[3], fields[4], fields[5], fields[6]);

			patients.add(patient);

		}

		return patients;
	}


}
