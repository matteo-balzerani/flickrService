package it.mb.service.flickr.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;

@Repository
public class ImagesInfoTemplateImpl implements ImagesInfoTemplate {
	
	private static final String collection="imagesInfo";

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<String> findAllTags() {
		DistinctIterable<String> iterable = mongoTemplate.getCollection(collection).distinct("tags", String.class);
		MongoCursor<String> cursor = iterable.iterator();
		List<String> list = new ArrayList<>();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

}
