package io.testomat.testmanagement.api.dtos.getSuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("relationships")
    private Relationships relationships;

    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

}
