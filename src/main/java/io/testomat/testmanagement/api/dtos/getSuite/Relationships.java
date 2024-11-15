package io.testomat.testmanagement.api.dtos.getSuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Relationships{

	@JsonProperty("children")
	private Children children;

	@JsonProperty("branch")
	private Branch branch;
}
