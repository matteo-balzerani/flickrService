package it.mb.service.flickr.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;

@Configuration
public class FlickrConfiguration {

	@Autowired
	private ApplicationConfiguration appConfig;

	@Value("${flickr.apiKey}")
	private String apiKey;

	@Value("${flickr.secret}")
	private String secret;

	private int perPage;
	private int pages;

	private Flickr flickrInterface;

	public Flickr getFlickrInterface() {
		return flickrInterface;
	}

	public int getPages() {
		return pages;
	}

	public int getPerPage() {
		return perPage;
	}

	@PostConstruct
	private void init() {
		if ("prod".equals(appConfig.getProfile())) {
			this.perPage = 500;
			this.pages = 1;
		} else {
			this.perPage = 100;
			this.pages = 1;
		}
		this.flickrInterface = new Flickr(this.apiKey, this.secret, new REST());
	}

}
