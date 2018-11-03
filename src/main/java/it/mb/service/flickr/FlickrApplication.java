package it.mb.service.flickr;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.mb.service.flickr.configuration.ApplicationConfiguration;
import it.mb.service.flickr.service.RetrieveService;

@SpringBootApplication
public class FlickrApplication {

//	@Autowired(required = false)
//	private RetrieveService service;

	private Logger log = LoggerFactory.getLogger(FlickrApplication.class);

//	@Autowired(required = false)
//	private ApplicationConfiguration applicationConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(FlickrApplication.class, args);

	}
//
//	@PostConstruct
//	private void init() {
//		if (service == null || applicationConfiguration == null)
//			return;
//		if ("dev".equalsIgnoreCase(applicationConfiguration.getProfile())) {
//			log.info("application is running in DEV profile.");
//			log.info("The results retrieved from Flickr are not completed");
//		}
//
//		if (applicationConfiguration.getInitTags() != null)
//			service.download(applicationConfiguration.getInitTags());
//		else
//			log.info("initial tags not configured");
//	}
}
