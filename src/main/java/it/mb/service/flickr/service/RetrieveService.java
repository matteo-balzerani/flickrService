package it.mb.service.flickr.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

import it.mb.service.flickr.configuration.ApplicationConfiguration;
import it.mb.service.flickr.restclient.FlickrClient;
import it.mb.service.flickr.service.util.FlickrServiceException;

@Service
public class RetrieveService {

	Logger log = LoggerFactory.getLogger(RetrieveService.class);

	@Autowired
	private FlickrClient flickrClient;

	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	@Autowired
	private StorageService storageService;

	@Autowired
	private MongoDbService mongoDbService;

	public Integer download(List<String> tags) {
		tags.removeIf(tag -> tag == null || tag.trim() == null || "".equals(tag.trim()));
		String[] tagsArr = tags.toArray(new String[tags.size()]);
		PhotoList<Photo> photos = null;
		try {
			photos = flickrClient.retrieveFlickrImagesByTags(tagsArr);
		} catch (FlickrServiceException e) {
			log.error("download methods interrupted by {}", e.getMessage());
			return 0;
		}
		photos.forEach(p -> {
			try {
				// refresh infos. Previuos call does not fill it
				Photo photoWithInfos = flickrClient.getInfoForFlickrPhoto(p);
				if (photoWithInfos != null) {
					p.setOriginalSecret(photoWithInfos.getOriginalSecret());
					p.setTags(photoWithInfos.getTags());
					if (!p.getOriginalSecret().isEmpty()) {
						String url = "https://farm" + p.getFarm() + ".staticflickr.com/" + p.getServer() + "/"
								+ p.getId() + "_" + p.getSecret() + "_z." + p.getOriginalFormat();
						String path = storageService.downloadPhoto(url, p.getId(), p.getOriginalFormat());
						mongoDbService.save(p, path);
					}
				}
			} catch (FlickrServiceException e) {
				log.error("photo {}  not downloaded due {}", p.getId(), e.getMessage());
			}
		});
		return photos.size();
	}

	@PostConstruct
	public void init() {
		applicationConfiguration.getInitTags()
				.removeIf(tag -> tag == null || tag.trim() == null || "".equals(tag.trim()));

		if (applicationConfiguration.getInitTags() != null || !applicationConfiguration.getInitTags().isEmpty()) {
			// log.info("initial tags are :
			// {}",applicationConfiguration.getInitTags().get(0));
			download(applicationConfiguration.getInitTags());
		} else
			log.info("initial tags not configured");
	}

	public String test() {
		try {
			return flickrClient.test();
		} catch (FlickrServiceException e) {
			log.error("test echo methods interrupted by {}", e.getMessage());
			return "KO";
		}

	}

}
