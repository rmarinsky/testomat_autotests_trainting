package io.testomat.api.dtos.postSuite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuitesRequest {

    @JsonProperty("data")
    private DataDetail datas;

    @Data
    @Builder
    public static class DataDetail {

        @JsonProperty("attributes")
        private Attributes attributes;

        @JsonProperty("type")
        private String type ;

        @Data
        @Builder
        public static class Attributes {

            @JsonProperty("description")
            private String description;

            @JsonProperty("title")
            private String title;

        }

    }

}
