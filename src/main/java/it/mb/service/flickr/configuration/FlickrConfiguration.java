package it.mb.service.flickr.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;

@Configuration
public class FlickrConfiguration {

	@Value("${flickr.apiKey}")
	private String apiKey;

	@Value("${flickr.secret}")
	private String secret;

	private Flickr flickrInterface;

	public Flickr getFlickrInterface() {
		return flickrInterface;
	}

	@PostConstruct
	private void init() {
		this.flickrInterface = new Flickr(this.apiKey, this.secret, new REST());
	}

}
