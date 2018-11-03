package it.mb.service.flickr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.mb.service.flickr.domain.ImageInfos;

/**
 * mongoDb repositoty for ImageInfos
 *
 */
@Repository
public interface ImageInfosMongoRepository extends MongoRepository<ImageInfos, String> {

	/**
	 * this method return image if it contains ALL tags
	 * 
	 * @param tags
	 * @return
	 */
	@Query("{tags: { $all:?0 }}")
	List<ImageInfos> findByTagsContainingAll(List<String> tags);

	/**
	 * this method return image if it contains 1 OR MORE tags
	 * 
	 * @param tags
	 * @return
	 */
	List<ImageInfos> findByTagsIn(List<String> tags);

	/**
	 * this method return image if it contains ALL tags
	 * 
	 * @param title
	 * @param tags
	 * @return
	 */
	@Query("{title: {$regex: ?0,  $options: 'i' } , tags: { $all:?1 }}")
	List<ImageInfos> findByTitleContainingAndTagsContainingAll(String regTitle, List<String> tags);

	/**
	 * this method return image if it contains 1 OR MORE tags
	 * 
	 * @param title
	 * @param tags
	 * @return
	 */
	@Query("{title: {$regex: ?0,  $options: 'i' } , tags: { $in:?1 }}")
	List<ImageInfos> findByTitleContainingAndTagsIn(String title, List<String> tags);

	/**
	 * find ImageInfos by title
	 * 
	 * @param title
	 * @return
	 */
	List<ImageInfos> findByTitleContains(String title);

}
