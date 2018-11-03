package it.mb.service.flickr.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;

@Configuration
public class FlickrConfiguration {

	private static final int perPageMin = 100;
	private static final int perPageMax = 500;
	private static final int pagesMin = 1;

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

	/**
	 * method to initializer FlickrInterface. it manage profile constant to
	 * configure results perPage and pages
	 */
	@PostConstruct
	private void init() {
		if ("prod".equals(appConfig.getProfile())) {
			this.perPage = perPageMax;
			this.pages = pagesMin;
		} else {
			this.perPage = perPageMin;
			this.pages = pagesMin;
		}
		this.flickrInterface = new Flickr(this.apiKey, this.secret, new REST());
	}

}
