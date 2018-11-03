package it.mb.service.flickr.service.util;

import java.util.ArrayList;

import com.flickr4java.flickr.photos.Photo;

import it.mb.service.flickr.domain.ImagesInfo;

public class Photo2ImagesInfo {

	public static ImagesInfo convert(Photo p) {
		ImagesInfo img = new ImagesInfo();
		img.setDesc(p.getDescription());
		img.setFlickrId(p.getId());
		img.setGeoData(p.getGeoData() != null ? p.getGeoData().toString() : null);
		img.setImageUrl(p.getUrl());
		img.setLocality(p.getLocality() != null ? p.getLocality().toString() : null);
		img.setTags(new ArrayList<String>());
		p.getTags().forEach(t -> {
			img.getTags().add(t.getValue());
		});
		img.setTitle(p.getTitle());
		return img;
	}

}
