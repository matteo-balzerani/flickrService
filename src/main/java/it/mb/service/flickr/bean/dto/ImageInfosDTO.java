package it.mb.service.flickr.bean.dto;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * ImageInfosDTO
 *
 */
public class ImageInfosDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String path;

	private String imageUrl;

	private List<String> tags;

	private String desc;

	private String title;

	private String flickrId;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFlickrId() {
		return flickrId;
	}

	public void setFlickrId(String flickrId) {
		this.flickrId = flickrId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((flickrId == null) ? 0 : flickrId.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageInfosDTO other = (ImageInfosDTO) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (flickrId == null) {
			if (other.flickrId != null)
				return false;
		} else if (!flickrId.equals(other.flickrId))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageInfosDTO [path=" + path + ", imageUrl=" + imageUrl + ", tags=" + String.join(",", tags) + ", desc=" + desc
				+ ", title=" + title + ", flickrId=" + flickrId + "]";
	}

	
	
}
