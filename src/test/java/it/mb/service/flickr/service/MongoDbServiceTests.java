package it.mb.service.flickr.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import it.mb.service.flickr.bean.dto.ImageInfosDTO;
import it.mb.service.flickr.domain.ImageInfos;
import it.mb.service.flickr.repository.ImageInfosRepositoty;
import it.mb.service.flickr.service.util.ImageInfos2ImageInfosDTO;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MongoDbServiceTests {

	@MockBean
	private ImageInfosRepositoty repository;

	@InjectMocks
	private MongoDbService service;

	@Before
	public void setUp() {
		ImageInfos ii3 = new ImageInfos();
		List<String> tags3 = new ArrayList<>();
		tags3.add("test");
		tags3.add("target");
		ii3.setTags(tags3);
		ii3.setFlickrId("uniqueIdTest3");
		ii3.setTitle("title");
		List<ImageInfos> iis = new ArrayList<>();
		iis.add(ii3);
		Mockito.when(repository.findByTagsContainingAll(tags3)).thenReturn(iis);
		Mockito.when(repository.findByTagsIn(tags3)).thenReturn(iis);
		Mockito.when(repository.findByTitleContainingAndTagsContainingAll("title", tags3)).thenReturn(iis);
		Mockito.when(repository.findByTitleContainingAndTagsIn("title", tags3)).thenReturn(iis);
		Mockito.when(repository.findByTitleContains("title")).thenReturn(iis);
	}

	@Test
	public void findByTags() {
		ImageInfosDTO iiDTO = new ImageInfosDTO();
		List<String> tags3 = new ArrayList<>();
		tags3.add("test");
		tags3.add("target");
		String title = "title";
		iiDTO.setTitle(title);
		iiDTO.setTags(tags3);
		iiDTO.setFlickrId("uniqueIdTest3");
		List<ImageInfosDTO> listToCheck = new ArrayList<>();
		listToCheck.add(iiDTO);
		// isOrMode=true
		assertThat(ImageInfos2ImageInfosDTO.convertList(repository.findByTagsIn(tags3))).isEqualTo(listToCheck);
		// isOrMode=false
		assertThat(ImageInfos2ImageInfosDTO.convertList(repository.findByTagsContainingAll(tags3)))
				.isEqualTo(listToCheck);
	}

	@Test
	public void findByTitleAndTagsContaining() {
		ImageInfosDTO iiDTO = new ImageInfosDTO();
		List<String> tags3 = new ArrayList<>();
		tags3.add("test");
		tags3.add("target");
		iiDTO.setTags(tags3);
		String title = "title";
		iiDTO.setTitle(title);
		iiDTO.setFlickrId("uniqueIdTest3");
		List<ImageInfosDTO> listToCheck = new ArrayList<>();
		listToCheck.add(iiDTO);
		// isOrMode=true
		assertThat(ImageInfos2ImageInfosDTO.convertList(repository.findByTitleContainingAndTagsIn(title, tags3)))
				.isEqualTo(listToCheck);
		// isOrMode=false
		assertThat(ImageInfos2ImageInfosDTO
				.convertList(repository.findByTitleContainingAndTagsContainingAll(title, tags3)))
						.isEqualTo(listToCheck);
	}

}
