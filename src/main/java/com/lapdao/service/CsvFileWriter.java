package com.lapdao.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lapdao.model.Issuer;
import com.lapdao.repository.IssuerRepository;
@Component
public class CsvFileWriter {
	@Autowired
	private  IssuerRepository issuerService;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,issuerCode,name,status,lastUpdate";
	
	public CsvFileWriter() {}
	
	public CsvFileWriter(IssuerRepository issuerService) {
		this.issuerService = issuerService;
	}
	
	public void writerCsvFile(String fileName) {
		System.out.println("  \n\n ==========> " + issuerService);
		Optional<List<Issuer>> list = issuerService.findAllIssuer();
		FileWriter fileWirter = null;
		if (list.isPresent()) {
			try {
				fileWirter = new FileWriter(fileName);
				fileWirter.append(FILE_HEADER.toString());
				fileWirter.append(NEW_LINE_SEPARATOR);

				for (Issuer issuer : list.get()) {
					fileWirter.append(String.valueOf(issuer.getId()));
					fileWirter.append(COMMA_DELIMITER);

					fileWirter.append(issuer.getIssuerCode());
					fileWirter.append(COMMA_DELIMITER);

					fileWirter.append(issuer.getName());
					fileWirter.append(COMMA_DELIMITER);

					fileWirter.append(String.valueOf(issuer.getStatus()));
					fileWirter.append(COMMA_DELIMITER);
					fileWirter.append(issuer.getLastUpdate().toString());
					fileWirter.append(NEW_LINE_SEPARATOR);

				}
				System.out.println("CSV file was created successfully !!!");
			} catch (Exception e) {
				
			} finally {

				try {

					fileWirter.flush();

					fileWirter.close();

				} catch (IOException e) {

					System.out.println("Error while flushing/closing fileWriter !!!");

					e.printStackTrace();
				}

			}

		}
	}
}
