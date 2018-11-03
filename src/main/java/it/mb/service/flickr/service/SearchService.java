package it.mb.service.flickr.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mb.service.flickr.bean.dto.ImagesInfoDTO;

@Service
public class SearchService {

	private Logger log = LoggerFactory.getLogger(SearchService.class);

	@Autowired
	private MongoDbService mongoDbService;

	public List<ImagesInfoDTO> search(List<String> tags, String title, boolean isOrMode) {
		List<ImagesInfoDTO> images = new ArrayList<>();
		if (tags == null || tags.isEmpty()) {
			images = mongoDbService.findByTitle(title);
		} else {
			if (title != null && !title.isEmpty()) {
				log.debug("you are searching by title");
				images = mongoDbService.findByTitleAndTagsContaining(title, tags, isOrMode);
			} else {
				images = mongoDbService.findByTags(tags, isOrMode);
			}
		}
		return images;
	}

	public List<String> findAllTags() {
		return mongoDbService.findAllTags();
	}
}
