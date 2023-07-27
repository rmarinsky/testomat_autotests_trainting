package io.testomat.api.dtos.getSuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attributes{

	@JsonProperty("code")
	private Object code;

	@JsonProperty("notes")
	private Object notes;

	@JsonProperty("emoji")
	private Object emoji;

	@JsonProperty("public-title")
	private String publicTitle;

	@JsonProperty("assigned-to")
	private Object assignedTo;

	@JsonProperty("description")
	private String description;

	@JsonProperty("is-branched")
	private Boolean isBranched;

	@JsonProperty("title")
	private String title;

	@JsonProperty("issues")
	private List<Object> issues;

	@JsonProperty("is-detail")
	private Boolean isDetail;

	@JsonProperty("path")
	private List<Object> path;

	@JsonProperty("file")
	private Object file;

	@JsonProperty("created-at")
	private String createdAt;

	@JsonProperty("file-type")
	private String fileType;

	@JsonProperty("test-count")
	private Integer testCount;

	@JsonProperty("updated-at")
	private String updatedAt;

	@JsonProperty("sync")
	private Boolean sync;

	@JsonProperty("labels")
	private List<Object> labels;

	@JsonProperty("tags")
	private List<Object> tags;

	@JsonProperty("filtered-tests")
	private Object filteredTests;

	@JsonProperty("jira-issues")
	private List<Object> jiraIssues;

	@JsonProperty("to-url")
	private String toUrl;

	@JsonProperty("position")
	private Integer position;

	@JsonProperty("parent-id")
	private Object parentId;

	@JsonProperty("is-root")
	private Boolean isRoot;
}
