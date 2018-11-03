package it.mb.service.flickr.service.util;

import java.util.ArrayList;
import java.util.List;

import it.mb.service.flickr.bean.dto.ImagesInfoDTO;
import it.mb.service.flickr.domain.ImagesInfo;

public class ImagesInfo2ImagesInfoDTO {

	public static ImagesInfoDTO convert(ImagesInfo ii) {
		ImagesInfoDTO iid = new ImagesInfoDTO();
		iid.setDesc(ii.getDesc());
		iid.setImageUrl(ii.getImageUrl());
		iid.setTags(ii.getTags());
		iid.setPath(ii.getImagePath());
		iid.setTitle(ii.getTitle());
		iid.setFlickrId(ii.getFlickrId());
		return iid;
	}

	public static List<ImagesInfoDTO> convertList(List<ImagesInfo> li) {
		List<ImagesInfoDTO> response = new ArrayList<ImagesInfoDTO>();
		if (li == null || li.isEmpty())
			return response;
		for (ImagesInfo ii : li) {
			response.add(convert(ii));
		}
		return response;
	}

}
