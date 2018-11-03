package it.mb.service.flickr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.mb.service.flickr.bean.RequestToDownload;
import it.mb.service.flickr.bean.SearchRequest;
import it.mb.service.flickr.bean.dto.ImageInfosDTO;
import it.mb.service.flickr.service.RetrieveService;
import it.mb.service.flickr.service.SearchService;

/**
 * rest contro11er to use services
 */
@RestController
public class ServiceController {

	@Autowired
	private RetrieveService service;

	@Autowired
	private SearchService searchService;

	/**
	 * test the service
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ResponseEntity<String> index() {
		return new ResponseEntity<>("service available", HttpStatus.OK);
	}

	/**
	 * ping flickr API by echo service
	 * 
	 * @return
	 */
	@GetMapping("/pingFlickr")
	public ResponseEntity<String> testFlickrApi() {
		return new ResponseEntity<>(service.test(), HttpStatus.OK);
	}

	/**
	 * download images by tags
	 * 
	 * @param tags
	 * @return
	 */
	@PostMapping(value = "/download", consumes = "application/json")
	public ResponseEntity<String> download(@RequestBody RequestToDownload tags) {
		Integer num = service.download(tags.getTags());
		if (num != null) {
			return new ResponseEntity<>("images downloaded ", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * search imageInfos by tags (optional) and/or title (optional)
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/search", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<ImageInfosDTO>> search(@RequestBody SearchRequest request) {
		request.getTags().removeIf(String::isEmpty);
		List<ImageInfosDTO> response = searchService.search(request.getTags(), request.getTitle(),
				request.isTagsInORMode());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * retrieve all tags
	 * 
	 * @return
	 */
	@GetMapping(value = "/tags", produces = "application/json")
	public ResponseEntity<List<String>> getAllTags() {
		List<String> response = searchService.findAllTags();
		return new ResponseEntity<List<String>>(response, HttpStatus.OK);

	}

}
