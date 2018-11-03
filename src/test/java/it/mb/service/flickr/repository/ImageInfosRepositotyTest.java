package it.mb.service.flickr.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import it.mb.service.flickr.domain.ImageInfos;

@RunWith(SpringRunner.class)
// @SpringBootTest
// @ContextConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@DataMongoTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ImageInfosRepositotyTest {

	@Autowired
	private ImageInfosRepositoty repository;

	@Before
	public void init() {

		repository.deleteAll();

		// 2 Tags, No Title
		ImageInfos ii = new ImageInfos();
		List<String> tags = new ArrayList<>();
		tags.add("test");
		tags.add("target");
		ii.setTags(tags);
		ii.setFlickrId("uniqueIdTest1");
		repository.save(ii);

		// 1 Tag, No Title
		ImageInfos ii2 = new ImageInfos();
		List<String> tag2 = new ArrayList<>();
		tag2.add("target");
		ii2.setTags(tag2);
		ii2.setFlickrId("uniqueIdTest2");
		repository.save(ii2);

		// 2 Tags And Title
		ImageInfos ii3 = new ImageInfos();
		List<String> tags3 = new ArrayList<>();
		tags3.add("test");
		tags3.add("target");
		ii3.setTags(tags3);
		ii3.setFlickrId("uniqueIdTest3");
		ii3.setTitle("title");
		repository.save(ii3);

		// 1 Tags And Title
		ImageInfos ii4 = new ImageInfos();
		List<String> tags4 = new ArrayList<>();
		tags4.add("target");
		ii4.setTags(tags4);
		ii4.setFlickrId("uniqueIdTest4");
		ii4.setTitle("title");
		repository.save(ii4);

	}

	@Test
	public void findByTagsIn() {
		List<String> filter = new ArrayList<>();
		filter.add("target");
		List<ImageInfos> response = repository.findByTagsIn(filter);
		assertThat(response.size()).isEqualTo(4);
	}

	@Test
	public void findByTagsContainingAll() {
		List<String> filter = new ArrayList<>();
		filter.add("target");
		filter.add("test");
		List<ImageInfos> response = repository.findByTagsContainingAll(filter);
		assertThat(response.isEmpty());
		assertThat(response.size()).isEqualTo(2);
	}

	@Test
	public void findByTitleContainingAndTagsContainingAll() {
		List<String> filter = new ArrayList<>();
		filter.add("target");
		filter.add("test");
		String title = "title";
		List<ImageInfos> response = repository.findByTitleContainingAndTagsContainingAll(title, filter);
		assertThat(response.size()).isEqualTo(1);
	}

	@Test
	public void findByTitleContainingAndTagsIn() {
		List<String> filter = new ArrayList<>();
		filter.add("target");
		String title = "title";
		List<ImageInfos> response = repository.findByTitleContainingAndTagsIn(title, filter);
		assertThat(response.size()).isEqualTo(2);
	}

	@Test
	public void findByTitleContains() {
		String title = "tit";
		List<ImageInfos> response = repository.findByTitleContains(title);
		assertThat(response.size()).isEqualTo(2);
	}

}