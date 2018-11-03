package it.mb.service.flickr.restclient;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.test.TestInterface;

import it.mb.service.flickr.configuration.FlickrConfiguration;
import it.mb.service.flickr.service.util.FlickrServiceException;

@Service
@Profile("dev")
public class FlickrClientDev implements FlickrClient {

	private Logger log = LoggerFactory.getLogger(FlickrClientDev.class);

	@Autowired
	private FlickrConfiguration flickr;

	@Override
	public void test() {
		TestInterface testInterface = flickr.getFlickrInterface().getTestInterface();
		Collection<Element> results = null;
		try {
			results = testInterface.echo(Collections.EMPTY_MAP);
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		if (results != null)
			log.debug(results.toString());
		else
			log.debug("[nil]");
	}

	@Override
	public PhotoList<Photo> retrieveFlickrImagesByTags(String[] tags) throws FlickrServiceException {
		// tags are previously cleaned
		PhotosInterface photoInter = flickr.getFlickrInterface().getPhotosInterface();
		PhotoList<Photo> response = null;
		try {
			SearchParameters params = new SearchParameters();
			params.setTags(tags);
			response = photoInter.search(params, 100, 1);
			log.debug("results num:" + response.getTotal() + "    size:: " + response.size());
		} catch (FlickrException e) {
			log.error("error " + e.getMessage());
			throw new FlickrServiceException(e, "exception during search");
		}
		return response;
	}

	@Override
	public Photo getInfoForFlickrPhoto(Photo photo) throws FlickrServiceException {
		Photo response = null;
		if (photo == null) {
			log.error("the required photo is null");
			return response;
		}
		PhotosInterface photoInter = flickr.getFlickrInterface().getPhotosInterface();
		try {
			response=photoInter.getInfo(photo.getId(), photo.getSecret());
		} catch (FlickrException e) {
			log.error("error " + e.getMessage());
			throw new FlickrServiceException(e, "exception retriving info about photo : " + photo.getId());
		}
		return response;
	}

}
