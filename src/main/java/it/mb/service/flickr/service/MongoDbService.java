package it.mb.service.flickr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.photos.Photo;

import it.mb.service.flickr.bean.dto.ImagesInfoDTO;
import it.mb.service.flickr.domain.ImagesInfo;
import it.mb.service.flickr.repository.ImageInfosRepositoty;
import it.mb.service.flickr.service.util.ImagesInfo2ImagesInfoDTO;
import it.mb.service.flickr.service.util.Photo2ImagesInfo;

@Service
public class MongoDbService {

	@Autowired
	private ImageInfosRepositoty repos;

	public void save(Photo photo, String path) {
		ImagesInfo ii = Photo2ImagesInfo.convert(photo);
		ii.setImagePath(path);
		repos.save(ii);
	}

	public List<ImagesInfoDTO> findByTags(List<String> tags, boolean isOrMode) {
		List<ImagesInfoDTO> response = new ArrayList<>();
		if (isOrMode) {
			response = ImagesInfo2ImagesInfoDTO.convertList(repos.findByTagsIn(tags));
		} else {
			response = ImagesInfo2ImagesInfoDTO.convertList(repos.findByTagsContainingAll(tags));
		}
		return response;
	}

	public List<String> findAllTags() {
		return repos.findAllTags();
	}

	public List<ImagesInfoDTO> findByTitleAndTagsContaining(String title, List<String> tags, boolean isOrMode) {
		List<ImagesInfoDTO> response = new ArrayList<>();
		if (isOrMode) {
			response = ImagesInfo2ImagesInfoDTO.convertList(repos.findByTitleContainingAndTagsIn(title, tags));
		} else {
			response = ImagesInfo2ImagesInfoDTO
					.convertList(repos.findByTitleContainingAndTagsContainingAll(title, tags));
		}
		return response;
	}

	public List<ImagesInfoDTO> findByTitle(String title) {
		List<ImagesInfoDTO> response = new ArrayList<>();
		response = ImagesInfo2ImagesInfoDTO.convertList(repos.findByTitleContains(title));
		return response;
	}

}
