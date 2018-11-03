package it.mb.service.flickr.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * mongoDb template for ImageInfos
 *
 */
@Repository
public interface ImageInfosTemplate {

	/**
	 * retrieve all tags
	 * 
	 * @return
	 */
	List<String> findAllTags();

}
