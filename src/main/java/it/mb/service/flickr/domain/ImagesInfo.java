package it.mb.service.flickr.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ImagesInfo {

	@Id
	private String id;

	@Indexed
	private String flickrId;

	private String imagePath;

	private String imageUrl;

	private List<String> tags;

	private String desc;

	private String title;

	private String locality;

	private String geoData;

	public String getFlickrId() {
		return flickrId;
	}

	public void setFlickrId(String flickrId) {
		this.flickrId = flickrId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getGeoData() {
		return geoData;
	}

	public void setGeoData(String geoData) {
		this.geoData = geoData;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
