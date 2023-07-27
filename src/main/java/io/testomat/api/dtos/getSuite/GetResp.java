package io.testomat.api.dtos.getSuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetResp{

	@JsonProperty("data")
	private Data data;

	@JsonProperty("included")
	private List<Object> included;
}
