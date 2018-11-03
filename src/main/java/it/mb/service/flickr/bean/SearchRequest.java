package it.mb.service.flickr.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

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
}
