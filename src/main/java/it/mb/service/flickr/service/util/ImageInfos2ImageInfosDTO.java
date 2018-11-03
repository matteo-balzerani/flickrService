package it.mb.service.flickr.service.util;

import java.util.ArrayList;
import java.util.List;

import it.mb.service.flickr.bean.dto.ImageInfosDTO;
import it.mb.service.flickr.domain.ImageInfos;

/**
 * utility to map ImageInfos in ImageInfosDTO
 *
 */
public class ImageInfos2ImageInfosDTO {

	public static ImageInfosDTO convert(ImageInfos ii) {
		if (ii == null)
			return null;
		ImageInfosDTO iid = new ImageInfosDTO();
		iid.setDesc(ii.getDesc());
		iid.setImageUrl(ii.getImageUrl());
		iid.setTags(ii.getTags());
		iid.setPath(ii.getImagePath());
		iid.setTitle(ii.getTitle());
		iid.setFlickrId(ii.getFlickrId());
		return iid;
	}

	public static List<ImageInfosDTO> convertList(List<ImageInfos> li) {
		List<ImageInfosDTO> response = new ArrayList<ImageInfosDTO>();
		if (li == null || li.isEmpty())
			return response;
		for (ImageInfos ii : li) {
			response.add(convert(ii));
		}
		return response;
	}

}
