package it.mb.service.flickr.restclient;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

import it.mb.service.flickr.service.util.FlickrServiceException;

public interface FlickrClient {

	/**
	 * test the connection to flickr API
	 * @return
	 * @throws FlickrServiceException
	 */
	String test() throws FlickrServiceException;

	/**
	 *  retrieve Images By Tags
	 * @param tags
	 * @return
	 * @throws FlickrServiceException
	 */
	PhotoList<Photo> retrieveFlickrImagesByTags(String[] tags) throws FlickrServiceException;

	/**
	 * retrieve Photo information ofs
	 * @param photo
	 * @return
	 * @throws FlickrServiceException
	 */
	Photo getInfoForFlickrPhoto(Photo photo) throws FlickrServiceException;

}
