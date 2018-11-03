package it.mb.service.flickr.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * Request Bean To search image from local Storage|MongoDB
 *
 */
@JsonInclude
public class SearchRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> tags;

	// when true tags are filtered using OR operator
	private boolean tagsInORMode;

	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isTagsInORMode() {
		return tagsInORMode;
	}

	public void setTagsInORMode(boolean tagsInORMode) {
		this.tagsInORMode = tagsInORMode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + (tagsInORMode ? 1231 : 1237);
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
		SearchRequest other = (SearchRequest) obj;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (tagsInORMode != other.tagsInORMode)
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
		return "SearchRequest [tags=" + String.join(",", tags) + ", tagsInORMode=" + tagsInORMode + ", title=" + title
				+ "]";
	}

}
