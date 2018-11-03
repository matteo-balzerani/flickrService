package it.mb.service.flickr.restclient;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

import it.mb.service.flickr.service.util.FlickrServiceException;

public interface FlickrClient {

	void test();

	PhotoList<Photo> retrieveFlickrImagesByTags(String[] tags) throws FlickrServiceException;

	Photo getInfoForFlickrPhoto(Photo photo) throws FlickrServiceException;

}
