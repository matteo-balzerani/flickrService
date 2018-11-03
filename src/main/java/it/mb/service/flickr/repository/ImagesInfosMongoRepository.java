package it.mb.service.flickr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import it.mb.service.flickr.domain.ImagesInfo;

@Repository
public interface ImagesInfosMongoRepository extends MongoRepository<ImagesInfo, String> {

	/**
	 * this method return image if it contains ALL tags
	 * 
	 * @param tags
	 * @return
	 */
	@Query("{tags: { $all:?0 }}")
	List<ImagesInfo> findByTagsContainingAll(List<String> tags);

	/**
	 * this method return image if it contains 1 OR MORE tags
	 * 
	 * @param tags
	 * @return
	 */
	List<ImagesInfo> findByTagsIn(List<String> tags);

	/**
	 * this method return image if it contains ALL tags
	 * 
	 * @param title
	 * @param tags
	 * @return
	 */
	@Query("{title: {$regex: ?0,  $options: 'i' } , tags: { $all:?1 }}")
	List<ImagesInfo> findByTitleContainingAndTagsContainingAll(String regTitle, List<String> tags);

	/**
	 * this methos return image if it contains 1 OR MORE tags
	 * @param title
	 * @param tags
	 * @return
	 */
	@Query("{title: {$regex: ?0,  $options: 'i' } , tags: { $in:?1 }}")
	List<ImagesInfo> findByTitleContainingAndTagsIn(String title, List<String> tags);

	List<ImagesInfo> findByTitleContains(String title);
	
}
