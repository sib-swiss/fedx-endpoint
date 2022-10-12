package swiss.sib.swissprot.readonly;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.rdf4j.federated.FedXFactory;
import org.eclipse.rdf4j.http.server.readonly.QueryResponder;
import org.eclipse.rdf4j.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = { "org.eclipse.rdf4j", "swiss.sib.swissprot" })
@ConfigurationPropertiesScan({ "swiss.sib.swissprot" })
@Import(QueryResponder.class)
public class FedXServer {
	private static final Logger logger = LoggerFactory.getLogger(FedXServer.class);
	
	@Autowired
	private FedXConfig config;

	
	public FedXServer() throws FileNotFoundException, IOException {
	}

	@Bean
	public Repository getRepository() throws FileNotFoundException, IOException {
		return FedXFactory.newFederation()
				.withSparqlEndpoint("https://sparql.uniprot.org/sparql")
				.withSparqlEndpoint("https://sparql.rhea-db.org/sparql")
				.create();
	}

	public static void main(String[] args) {
		SpringApplication.run(FedXServer.class, args);
	}
}
