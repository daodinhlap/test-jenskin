package com.lapdao.job;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.lapdao.model.Issuer;
import com.lapdao.model.QIssuer;
import com.lapdao.repository.IssuerRepository;
import com.lapdao.service.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

@Component
public class JobExportCsv {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private IssuerRepository issuerService;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,issuerCode,name,status,lastUpdate";

	private @Autowired IssuerRepository issuerRepository;
	private @Autowired SlackService slackService;

	public JobExportCsv() {
		System.out.println("\n ---> Init Job Policy Bean \n");
		System.out.println("\n\n----->     start job------------------111111111111");
	}

	@Scheduled(cron = "*/10 * *  * * *")
	public void startJob() {

		/*
		 * CsvFileWriter csvFileWriter = new CsvFileWriter(issuerRepository);
		 * CsvFileWriter csvFileWriter = new CsvFileWriter();
		 * csvFileWriter.writerCsvFile("test.csv");
		 */

		BooleanBuilder booleanIssuer = new BooleanBuilder();
		booleanIssuer.and(QIssuer.issuer.status.eq(Issuer.Status.ACTIVE));

		QIssuer qIssuer = QIssuer.issuer;
		JPAQuery<?> query = new JPAQuery<Void>(entityManager);
		List<Issuer> listIssuer = query.select(qIssuer).from(qIssuer).where(booleanIssuer).fetch();

		for (Issuer issuer : listIssuer) {
			System.out.println(issuer.toString());
		}

		FileWriter fileWirter = null;
		if (!(CollectionUtils.isEmpty(listIssuer))) {
			try {
				fileWirter = new FileWriter("test.csv");
				fileWirter.append(FILE_HEADER.toString());
				fileWirter.append(NEW_LINE_SEPARATOR);

				for (Issuer issuer : listIssuer) {

					fileWirter.append(String.valueOf(issuer.getId()));
					fileWirter.append(COMMA_DELIMITER);

					fileWirter.append(issuer.getIssuerCode());
					fileWirter.append(COMMA_DELIMITER);

					fileWirter.append(issuer.getName());
					fileWirter.append(COMMA_DELIMITER);

					fileWirter.append(String.valueOf(issuer.getStatus()));
					fileWirter.append(COMMA_DELIMITER);
					fileWirter.append(String.valueOf(issuer.getLastUpdate()));
					fileWirter.append(NEW_LINE_SEPARATOR);

				}

				System.out.println("writer successfully !");

			} catch (IOException e) {
				slackService.send("  " + e.getMessage());
				e.printStackTrace();
			}
		}

	}

}
