package it.mb.service.flickr.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Value("${flickr.initial.tags:#{null}}")
	private List<String> initTags;

	@Value("${spring.profiles.active}")
	private String profile;

	public List<String> getInitTags() {
		return initTags;
	}

	public String getProfile() {
		return profile;
	}

}
