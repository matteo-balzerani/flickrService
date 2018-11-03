package it.mb.service.flickr.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ImagesInfoTemplate {

	List<String> findAllTags();

}
