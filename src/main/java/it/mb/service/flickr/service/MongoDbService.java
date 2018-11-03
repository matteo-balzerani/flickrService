package it.mb.service.flickr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.photos.Photo;

import it.mb.service.flickr.bean.dto.ImageInfosDTO;
import it.mb.service.flickr.domain.ImageInfos;
import it.mb.service.flickr.repository.ImageInfosRepositoty;
import it.mb.service.flickr.service.util.ImageInfos2ImageInfosDTO;
import it.mb.service.flickr.service.util.Photo2ImageInfos;

@Service
public class MongoDbService {

	@Autowired
	private ImageInfosRepositoty repos;
/**
 * 
 * save imageinfos in mongodb
 * @param photo
 * @param path
 */
	public void save(Photo photo, String path) {
		ImageInfos ii = Photo2ImageInfos.convert(photo);
		ii.setImagePath(path);
		repos.save(ii);
	}

	/**
	 * find imageinfos by tags in AND or OR mode
	 * @param tags
	 * @param isOrMode
	 * @return
	 */
	public List<ImageInfosDTO> findByTags(List<String> tags, boolean isOrMode) {
		List<ImageInfosDTO> response = new ArrayList<>();
		if (isOrMode) {
			response = ImageInfos2ImageInfosDTO.convertList(repos.findByTagsIn(tags));
		} else {
			response = ImageInfos2ImageInfosDTO.convertList(repos.findByTagsContainingAll(tags));
		}
		return response;
	}

	
	/**
	 * get list of all tags
	 * @return
	 */
	public List<String> findAllTags() {
		return repos.findAllTags();
	}



	/**
	 * find imageinfos by title and tags in AND or OR mode
	 * @param title
	 * @param tags
	 * @param isOrMode
	 * @return
	 */
	public List<ImageInfosDTO> findByTitleAndTagsContaining(String title, List<String> tags, boolean isOrMode) {
		List<ImageInfosDTO> response = new ArrayList<>();
		if (isOrMode) {
			response = ImageInfos2ImageInfosDTO.convertList(repos.findByTitleContainingAndTagsIn(title, tags));
		} else {
			response = ImageInfos2ImageInfosDTO
					.convertList(repos.findByTitleContainingAndTagsContainingAll(title, tags));
		}
		return response;
	}

	/**
	 * find imageinfos by title
	 * @param title
	 * @return
	 */
	public List<ImageInfosDTO> findByTitle(String title) {
		List<ImageInfosDTO> response = new ArrayList<>();
		response = ImageInfos2ImageInfosDTO.convertList(repos.findByTitleContains(title));
		return response;
	}

}
